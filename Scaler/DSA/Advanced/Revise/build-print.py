#!/usr/bin/env python3
"""Generate a self-contained, print-ready revision copy (print.html).

Reads the DSA revisor JSON data for Section 0 + Section 1 and emits one static
HTML file with everything inlined, tuned for Cmd+P -> Save as PDF.

Layout: cover page, then per topic an opener page (key patterns + templates and
that topic's formulas) followed by one card per problem (statement, sample I/O,
plain english, how-to-crack-it, approach steps per solution, watch-out pitfalls,
and the optimal solution's Java code as the answer).

Run:  python3 build-print.py
"""

import html
import json
import os

HERE = os.path.dirname(os.path.abspath(__file__))
DATA = os.path.join(HERE, "data")
OUT = os.path.join(HERE, "section1.html")
GENERATED = "2026-07-15"
SECTIONS = {"Section 0", "Section 1"}


def load(name):
    with open(os.path.join(DATA, name), encoding="utf-8") as f:
        return json.load(f)


def esc(text):
    """HTML-escape plain text and turn newlines into <br>."""
    return html.escape(str(text or "")).replace("\n", "<br>")


# Markers after which the description is just boilerplate already shown in the
# sample-I/O boxes. Keep the problem statement + "Problem Constraints"; drop the
# rest so each card stays compact.
_CUT_MARKERS = ("\nInput Format", "\nExample Input", "\nExample:", "\nExample\n")


def condense_description(desc):
    text = str(desc or "")
    cut = len(text)
    for m in _CUT_MARKERS:
        i = text.find(m)
        if i != -1:
            cut = min(cut, i)
    return text[:cut].rstrip()


def esc_pre(text):
    """HTML-escape text destined for a <pre> (keep newlines literal)."""
    return html.escape(str(text or ""))


def optimal_solution(problem):
    sols = problem.get("solutions") or []
    if not sols:
        return None
    for s in sols:
        if "optimal" in str(s.get("label", "")).lower():
            return s
    return sols[-1]


def render_patterns(notes):
    patterns = (notes or {}).get("keyPatterns") or []
    if not patterns:
        return ""
    rows = []
    for i, p in enumerate(patterns, 1):
        rows.append(
            f'<div class="pattern">'
            f'<div class="pattern-name">{i}. {esc(p.get("name"))}</div>'
            f'<div class="when"><span class="lead">when:</span> {esc(p.get("when"))}</div>'
            f'<pre class="code">{esc_pre(p.get("template"))}</pre>'
            f"</div>"
        )
    return (
        '<div class="block-label accent">key patterns</div>'
        + "".join(rows)
    )


def render_formulas(formulas):
    if not formulas:
        return ""
    rows = []
    for f in formulas:
        when = "".join(f"<li>{w}</li>" for w in (f.get("whenToUse") or []))
        when_html = f'<ul class="when-list">{when}</ul>' if when else ""
        note = f'<p class="note">note: {f.get("note")}</p>' if f.get("note") else ""
        # formula / example / note contain pre-baked HTML — inject as-is.
        rows.append(
            f'<div class="formula">'
            f'<div class="formula-name">{esc(f.get("name"))}</div>'
            f'<pre class="code formula-body">{f.get("formula", "")}</pre>'
            f'<p class="example">{f.get("example", "")}</p>'
            f'{when_html}{note}'
            f"</div>"
        )
    return (
        '<div class="block-label warn">formulas</div>'
        + "".join(rows)
    )


def topic_anchor(topic):
    return "topic-" + topic["file"].replace(".json", "")


def render_opener(topic, notes, formulas, n_problems):
    patterns = (notes or {}).get("keyPatterns") or []
    bits = [
        f'<section class="opener" id="{topic_anchor(topic)}" data-topic="{esc(topic["name"])}">',
        '<div class="topic-head">',
        f'<div class="kicker">{esc(topic["section"]).upper()} · {esc(topic["name"]).upper()}</div>',
        f'<h1>Key patterns{" &amp; formulas" if formulas else ""}</h1>',
        f'<div class="counts">{len(patterns)} patterns'
        + (f" · {len(formulas)} formulas" if formulas else "")
        + f" · {n_problems} problems</div>",
        "</div>",
        render_patterns(notes),
        render_formulas(formulas),
        "</section>",
    ]
    return "".join(bits)


def render_solution(sol, is_optimal):
    label = esc(sol.get("label"))
    comp = sol.get("complexity") or {}
    comp_html = (
        f'<span class="cx">time {esc(comp.get("time"))} · space {esc(comp.get("space"))}</span>'
        if comp
        else ""
    )
    star = '<span class="star">★</span> ' if is_optimal else ""
    steps = "".join(f"<li>{esc(a)}</li>" for a in (sol.get("approach") or []))
    cls = "sol optimal" if is_optimal else "sol"
    return (
        f'<div class="{cls}">'
        f'<div class="sol-head"><span class="sol-label">{star}{label}</span>{comp_html}</div>'
        f'<ol class="steps">{steps}</ol>'
        f"</div>"
    )


def render_crack(problem):
    story = problem.get("story")
    sols = problem.get("solutions") or []
    leap = ""
    if len(sols) >= 2:
        first = sols[0]
        opt = optimal_solution(problem)
        first_s = first.get("story") or first.get("label")
        opt_s = opt.get("story") or opt.get("label")
        leap = (
            f'<div class="leap">'
            f'<span class="chip">{esc(first_s)}</span>'
            f'<span class="arrow">→</span>'
            f'<span class="chip good">{esc(opt_s)}</span>'
            f"</div>"
        )
    if not story and not leap:
        return ""
    insight = (
        f'<p class="insight"><span class="lead">key insight:</span> {esc(story)}</p>'
        if story
        else ""
    )
    return (
        f'<div class="crack">'
        f'<div class="block-label accent">how to crack it</div>'
        f"{insight}{leap}"
        f"</div>"
    )


def render_pitfalls(problem):
    pits = problem.get("pitfalls") or []
    if not pits:
        return ""
    items = "".join(f"<li>{esc(p)}</li>" for p in pits)
    return (
        f'<div class="watch">'
        f'<div class="block-label danger">watch out</div>'
        f"<ul>{items}</ul>"
        f"</div>"
    )


def render_answer(problem):
    opt = optimal_solution(problem)
    if not opt or not opt.get("code"):
        return ""
    return (
        f'<div class="answer">'
        f'<div class="block-label good">answer — optimal code (java)</div>'
        f'<pre class="code">{esc_pre(opt.get("code"))}</pre>'
        f"</div>"
    )


def render_problem(topic, problem):
    tags = "".join(f'<span class="tag">{esc(t)}</span>' for t in (problem.get("tags") or []))
    opt = optimal_solution(problem)
    sols_html = "".join(
        render_solution(s, s is opt) for s in (problem.get("solutions") or [])
    )
    return (
        f'<section class="problem" data-topic="{esc(topic["name"])}">'
        '<div class="p-head">'
        f'<div><div class="kicker">{esc(topic["name"])} · #{problem.get("num", "")}</div>'
        f'<h2>{esc(problem.get("title"))}</h2></div>'
        f'<div class="subtopic">{esc(problem.get("subTopic"))}</div>'
        "</div>"
        f'<div class="tags">{tags}</div>'
        f'<p class="desc">{esc(condense_description(problem.get("description")))}</p>'
        '<div class="io">'
        f'<div class="io-box"><div class="io-label">sample input</div><pre>{esc_pre(problem.get("sampleInput"))}</pre></div>'
        f'<div class="io-box"><div class="io-label">sample output</div><pre>{esc_pre(problem.get("sampleOutput"))}</pre></div>'
        "</div>"
        f'<div class="plain"><div class="block-label">in plain english</div><p>{esc(problem.get("plain"))}</p></div>'
        f"{render_crack(problem)}"
        f'<div class="solutions">{sols_html}</div>'
        f"{render_pitfalls(problem)}"
        f"{render_answer(problem)}"
        "</section>"
    )


CSS = """
* { box-sizing: border-box; }
body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
  color: #1a1a1a; line-height: 1.42; margin: 0; font-size: 12px; background: #e9e9e6; }
.page-wrap { margin: 0; padding: 0; }
h1 { font-size: 22px; font-weight: 600; margin: 2px 0; }
h2 { font-size: 18px; font-weight: 600; margin: 2px 0; }
.kicker { font-size: 11px; letter-spacing: .05em; color: #888; text-transform: uppercase; }
pre.code { font-family: "SF Mono", ui-monospace, Menlo, Consolas, monospace; font-size: 10.5px;
  line-height: 1.4; background: #f6f6f4; border: .5px solid #ddd; border-radius: 6px;
  padding: 7px 10px; white-space: pre-wrap; overflow-wrap: anywhere; margin: 4px 0; }
.block-label { font-size: 11px; letter-spacing: .04em; color: #777; margin: 0 0 5px; }
.block-label.accent { color: #185fa5; } .block-label.warn { color: #854f0b; }
.block-label.danger { color: #a32d2d; } .block-label.good { color: #0f6e56; }
.lead { font-weight: 600; }

/* cover */
.cover { display: flex; flex-direction: column; justify-content: center; height: 100%; }
.cover h1 { font-size: 32px; } .cover .sub { color: #666; margin: 6px 0 22px; }
.toc-title { font-size: 12px; letter-spacing: .05em; text-transform: uppercase; color: #888; margin-bottom: 6px; }
.toc { border-top: .5px solid #ccc; padding-top: 12px; }
.toc-row { display: flex; align-items: baseline; gap: 10px; padding: 6px 0;
  border-bottom: .5px solid #eee; }
.toc-row .tname { font-weight: 500; } .toc-row .sec { color: #999; font-size: 11px; }
.toc-row .tcount { color: #999; font-size: 11px; }
.toc-row .pageno { margin-left: auto; color: #444; text-decoration: none;
  font-variant-numeric: tabular-nums; }
.toc-row .pageno::after { content: "p. " target-counter(attr(href url), page); }

/* opener */
.opener .topic-head { border-bottom: 2px solid #999; padding-bottom: 8px; margin-bottom: 14px; }
.counts { color: #666; font-size: 12px; }
.pattern, .formula { margin-bottom: 14px; break-inside: avoid; }
.pattern-name, .formula-name { font-weight: 600; font-size: 14px; }
.when { color: #555; font-size: 12.5px; margin: 2px 0; } .when .lead { color: #185fa5; }
.when-list { margin: 4px 0 0; padding-left: 18px; color: #555; font-size: 12px; }
.example { font-size: 12.5px; color: #444; margin: 5px 0; }
.note { font-size: 12px; color: #777; font-style: italic; margin: 4px 0 0; }
.formula-body .var { color: #185fa5; } .formula-body .op { color: #a32d2d; }
.formula-body .com { color: #888; font-style: italic; } .formula-body .num { color: #0f6e56; }

/* problem card */
.problem { }
.p-head { display: flex; justify-content: space-between; align-items: baseline;
  border-bottom: 2px solid #999; padding-bottom: 6px; margin-bottom: 9px; }
.subtopic { font-size: 12px; color: #666; text-align: right; max-width: 40%; }
.tags { margin-bottom: 12px; } .tag { display: inline-block; font-size: 10.5px; background: #e6f1fb;
  color: #0c447c; padding: 2px 9px; border-radius: 10px; margin: 0 4px 4px 0; }
.desc { white-space: normal; }
.io { display: grid; grid-template-columns: 1fr 1fr; gap: 10px; margin: 12px 0; break-inside: avoid; }
.io-box { background: #f6f6f4; border-radius: 6px; padding: 7px 11px; }
.io-label { font-size: 10.5px; color: #888; margin-bottom: 3px; }
.io-box pre { margin: 0; font-family: "SF Mono", ui-monospace, Menlo, monospace; font-size: 11.5px; white-space: pre-wrap; }
.plain { border-left: 3px solid #85b7eb; padding-left: 11px; margin: 8px 0; }
.plain p { margin: 0; color: #444; }
.crack { background: #e6f1fb; border-radius: 8px; padding: 8px 11px; margin: 8px 0; break-inside: avoid; }
.crack .insight { margin: 0 0 6px; font-size: 12px; }
.leap { display: flex; align-items: center; gap: 8px; flex-wrap: wrap; font-size: 11.5px; }
.leap .chip { background: #fff; border-radius: 5px; padding: 2px 8px; color: #555; }
.leap .chip.good { color: #0f6e56; } .leap .arrow { color: #888; }
.sol { border-top: .5px solid #eee; padding-top: 7px; margin-top: 7px; break-inside: avoid; }
.sol-head { display: flex; justify-content: space-between; align-items: baseline; }
.sol-label { font-weight: 600; font-size: 12.5px; } .sol.optimal .sol-label { color: #0f6e56; }
.star { color: #0f6e56; } .cx { font-family: "SF Mono", monospace; font-size: 11px; color: #666; }
.steps { margin: 4px 0; padding-left: 20px; color: #444; }
.watch { background: #fcebeb; border-radius: 8px; padding: 8px 11px; margin: 8px 0; break-inside: avoid; }
.watch ul { margin: 0; padding-left: 18px; color: #7a2020; }
.answer { margin-top: 8px; }
.answer pre.code { background: #f6f6f4; break-inside: avoid; }

/* ---- paged media (Paged.js): page numbers + running topic header ---- */
@page {
  size: A4;
  margin: 15mm 14mm 16mm;
  @bottom-center {
    content: counter(page);
    font-family: -apple-system, BlinkMacSystemFont, sans-serif;
    font-size: 9px; color: #999;
  }
  @top-right {
    content: string(topictitle);
    font-family: -apple-system, BlinkMacSystemFont, sans-serif;
    font-size: 9px; color: #bbb; letter-spacing: .04em;
  }
}
@page :first { @bottom-center { content: none; } @top-right { content: none; } }
.opener, .problem { string-set: topictitle attr(data-topic); }
.cover, .opener, .problem { break-after: page; }
.cover { break-before: avoid; }

/* on-screen: show discrete sheets; print: flat */
.pagedjs_page { background: #fff; box-shadow: 0 1px 5px rgba(0,0,0,.18); margin: 10px auto; }
* { -webkit-print-color-adjust: exact; print-color-adjust: exact; }
@media print { body { background: #fff; } .pagedjs_page { box-shadow: none; margin: 0; } }
"""


def main():
    index = load("index.json")
    formulas_all = load("formulas.json").get("formulas", [])
    topics = [t for t in index["topics"] if t.get("section") in SECTIONS]

    parts = []
    total = 0
    toc = []
    body = []

    for topic in topics:
        problems = sorted(load(topic["file"]), key=lambda p: p.get("num", 0))
        notes = load(topic["notesFile"]) if topic.get("notesFile") else {}
        topic_formulas = [f for f in formulas_all if f.get("topic") == topic["name"]]
        total += len(problems)
        toc.append((topic, len(problems)))

        body.append(render_opener(topic, notes, topic_formulas, len(problems)))
        for p in problems:
            body.append(render_problem(topic, p))

    toc_rows = "".join(
        f'<div class="toc-row">'
        f'<span class="tname">{esc(t["name"])}</span>'
        f'<span class="sec">{esc(t["section"])}</span>'
        f'<span class="tcount">{n} problems</span>'
        f'<a class="pageno" href="#{topic_anchor(t)}"></a>'
        "</div>"
        for t, n in toc
    )
    cover = (
        '<section class="cover">'
        "<h1>DSA revision — physical copy</h1>"
        f'<div class="sub">Scaler Advanced · Section 0 &amp; 1 · {total} problems · '
        f"generated {GENERATED}</div>"
        '<div class="toc-title">Contents</div>'
        f'<div class="toc">{toc_rows}</div>'
        "</section>"
    )

    # Inline the Paged.js polyfill so the file paginates itself (page numbers,
    # running topic header, contents page numbers) with no network at view time.
    with open(os.path.join(HERE, "vendor", "paged.polyfill.min.js"), encoding="utf-8") as f:
        polyfill = f.read()

    doc = (
        "<!doctype html><html lang=\"en\"><head><meta charset=\"utf-8\">"
        '<meta name="viewport" content="width=device-width, initial-scale=1">'
        "<title>DSA revision — physical copy</title>"
        f"<style>{CSS}</style></head><body>"
        f'<div class="page-wrap">{cover}{"".join(body)}</div>'
        f"<script>{polyfill}</script>"
        "</body></html>"
    )

    with open(OUT, "w", encoding="utf-8") as f:
        f.write(doc)

    print(f"Wrote {OUT}")
    print(f"{total} problems, {len(topics)} topics")


if __name__ == "__main__":
    main()
