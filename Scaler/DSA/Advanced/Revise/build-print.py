#!/usr/bin/env python3
"""Generate a self-contained, print-ready revision copy.

Reads the DSA revisor JSON data and emits one static HTML file with everything
inlined, tuned for Cmd+P -> Save as PDF. Paginated in-browser by Paged.js
(page numbers, running topic header, contents with per-topic start pages).

Two build profiles:
  section1 (Section 0+1): opener = key patterns + formulas; each problem shows
    plain english, how-to-crack-it, per-solution approach steps, watch-out
    pitfalls, and the optimal Java code.
  section2 (Section 2): opener = how-to-think-about-topic + patterns + formulas;
    each problem shows plain english, HOW TO THINK, minimal pseudocode, a compact
    complexity line, watch-out pitfalls, and the optimal Java code.

Run:  python3 build-print.py [section1|section2]   (default: section2)
"""

import html
import json
import os
import sys

HERE = os.path.dirname(os.path.abspath(__file__))
DATA = os.path.join(HERE, "data")
GENERATED = "2026-07-15"

PROFILES = {
    "section1": {
        "sections": {"Section 0", "Section 1"},
        "out": "section1.html",
        "layout": "crack",
        "subtitle": "Section 0 &amp; 1",
    },
    "section2": {
        "sections": {"Section 2"},
        "out": "section2.html",
        "layout": "think",
        "subtitle": "Section 2",
    },
}
PROFILE = PROFILES[sys.argv[1] if len(sys.argv) > 1 else "section2"]
OUT = os.path.join(HERE, PROFILE["out"])
SECTIONS = PROFILE["sections"]
LAYOUT = PROFILE["layout"]


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


def render_thoughtprocess(notes):
    """The topic-level 'how to think about this topic' block (Section 2)."""
    tp = (notes or {}).get("thoughtProcess") or []
    if not tp:
        return ""
    rows = []
    for item in tp:
        rows.append(
            f'<div class="tp-item">'
            f'<div class="tp-q">{esc(item.get("title"))}</div>'
            f'<p class="tp-b">{esc(item.get("body"))}</p>'
            f"</div>"
        )
    return (
        '<div class="block-label">how to think about this topic</div>'
        + "".join(rows)
    )


def topic_anchor(topic):
    return "topic-" + topic["file"].replace(".json", "")


def render_opener(topic, notes, formulas, n_problems):
    patterns = (notes or {}).get("keyPatterns") or []
    tp = (notes or {}).get("thoughtProcess") or []
    think = LAYOUT == "think"
    head_title = "How to approach" if think else "Key patterns"
    if not think and formulas:
        head_title = "Key patterns &amp; formulas"
    counts = []
    if think and tp:
        counts.append(f"{len(tp)} thinking cues")
    if patterns:
        counts.append(f"{len(patterns)} patterns")
    if formulas:
        counts.append(f"{len(formulas)} formulas")
    counts.append(f"{n_problems} problems")
    bits = [
        f'<section class="opener" id="{topic_anchor(topic)}" data-topic="{esc(topic["name"])}">',
        '<div class="topic-head">',
        f'<div class="kicker">{esc(topic["section"]).upper()} · {esc(topic["name"]).upper()}</div>',
        f"<h1>{head_title}</h1>",
        f'<div class="counts">{" · ".join(counts)}</div>',
        "</div>",
        render_thoughtprocess(notes) if think else "",
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


def render_howtothink(problem):
    """Per-problem reasoning bridge (Section 2): bullets from `howToThink`."""
    bullets = problem.get("howToThink") or []
    if not bullets:
        # fall back to the story crux so the block never renders empty
        story = problem.get("story")
        if not story:
            return ""
        bullets = [story]
    items = "".join(f"<li>{esc(b)}</li>" for b in bullets)
    return (
        f'<div class="crack">'
        f'<div class="block-label">how to think</div>'
        f"<ul>{items}</ul>"
        f"</div>"
    )


def render_pseudocode(problem):
    """Minimal pseudocode for the optimal solution (Section 2)."""
    opt = optimal_solution(problem)
    ps = (opt or {}).get("pseudocode")
    if not ps:
        return ""
    comp = (opt or {}).get("complexity") or {}
    cx = (
        f'<span class="cx">time {esc(comp.get("time"))} · space {esc(comp.get("space"))}</span>'
        if comp else ""
    )
    return (
        f'<div class="proceed">'
        f'<div class="proceed-head"><span class="block-label">how to proceed — pseudocode</span>{cx}</div>'
        f'<pre class="code">{esc_pre(ps)}</pre>'
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


def render_complexity_line(problem):
    """Compact brute -> optimal complexity summary (Section 2)."""
    sols = problem.get("solutions") or []
    if not sols:
        return ""
    opt = optimal_solution(problem)
    parts = []
    for s in sols:
        comp = s.get("complexity") or {}
        tag = f'{esc(s.get("label"))} {esc(comp.get("time"))}'
        if s is opt:
            tag = f"<b>{tag}</b>"
        parts.append(tag)
    return f'<div class="cxline">{" &nbsp;→&nbsp; ".join(parts)}</div>'


def render_problem(topic, problem):
    tags = "".join(f'<span class="tag">{esc(t)}</span>' for t in (problem.get("tags") or []))
    head = (
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
    )
    if LAYOUT == "think":
        middle = (
            f"{render_howtothink(problem)}"
            f"{render_pseudocode(problem)}"
            f"{render_complexity_line(problem)}"
        )
    else:
        opt = optimal_solution(problem)
        sols_html = "".join(
            render_solution(s, s is opt) for s in (problem.get("solutions") or [])
        )
        middle = f"{render_crack(problem)}<div class=\"solutions\">{sols_html}</div>"
    return (
        head
        + middle
        + f"{render_pitfalls(problem)}"
        + f"{render_answer(problem)}"
        + "</section>"
    )


CSS = """
/* ==== monochrome / xerox palette: pure black text, gray fills, solid borders ==== */
* { box-sizing: border-box; }
body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
  color: #000; line-height: 1.42; margin: 0; font-size: 12px; background: #d9d9d9; }
.page-wrap { margin: 0; padding: 0; }
h1, h2 { color: #000; }
h1 { font-size: 22px; font-weight: 700; margin: 2px 0; }
h2 { font-size: 18px; font-weight: 700; margin: 2px 0; }
.kicker { font-size: 11px; letter-spacing: .05em; color: #000; text-transform: uppercase; font-weight: 600; }
pre.code { font-family: "SF Mono", ui-monospace, Menlo, Consolas, monospace; font-size: 10.5px;
  line-height: 1.4; background: #ededed; border: 1px solid #555; border-radius: 5px;
  padding: 7px 10px; white-space: pre-wrap; overflow-wrap: anywhere; margin: 4px 0; color: #000; }
.block-label { font-size: 11px; letter-spacing: .05em; color: #000; margin: 0 0 5px; font-weight: 700;
  text-transform: uppercase; }
.block-label.accent, .block-label.warn, .block-label.danger, .block-label.good { color: #000; }
.lead { font-weight: 700; }

/* cover */
.cover { display: flex; flex-direction: column; justify-content: center; height: 100%; }
.cover h1 { font-size: 32px; } .cover .sub { color: #222; margin: 6px 0 22px; }
.toc-title { font-size: 12px; letter-spacing: .05em; text-transform: uppercase; color: #000; margin-bottom: 6px; font-weight: 700; }
.toc { border-top: 2px solid #000; padding-top: 12px; }
.toc-row { display: flex; align-items: baseline; gap: 10px; padding: 6px 0;
  border-bottom: 1px solid #999; }
.toc-row .tname { font-weight: 700; color: #000; } .toc-row .sec { color: #333; font-size: 11px; }
.toc-row .tcount { color: #333; font-size: 11px; }
.toc-row .pageno { margin-left: auto; color: #000; text-decoration: none; font-weight: 700;
  font-variant-numeric: tabular-nums; }
.toc-row .pageno::after { content: "p. " target-counter(attr(href url), page); }

/* opener */
.opener .topic-head { border-bottom: 3px solid #000; padding-bottom: 8px; margin-bottom: 14px; }
.counts { color: #222; font-size: 12px; }
.pattern, .formula { margin-bottom: 14px; break-inside: avoid; }
.pattern-name, .formula-name { font-weight: 700; font-size: 14px; color: #000; }
.when { color: #111; font-size: 12.5px; margin: 2px 0; } .when .lead { color: #000; }
.when-list { margin: 4px 0 0; padding-left: 18px; color: #111; font-size: 12px; }
.example { font-size: 12.5px; color: #000; margin: 5px 0; }
.note { font-size: 12px; color: #333; font-style: italic; margin: 4px 0 0; }
.formula-body .var { color: #000; font-weight: 700; } .formula-body .op { color: #000; font-weight: 700; }
.formula-body .com { color: #555; font-style: italic; } .formula-body .num { color: #000; }

/* problem card */
.problem { }
.p-head { display: flex; justify-content: space-between; align-items: baseline;
  border-bottom: 3px solid #000; padding-bottom: 6px; margin-bottom: 9px; }
.subtopic { font-size: 12px; color: #222; text-align: right; max-width: 40%; }
.tags { margin-bottom: 12px; } .tag { display: inline-block; font-size: 10.5px; background: #e6e6e6;
  color: #000; padding: 2px 9px; border-radius: 10px; margin: 0 4px 4px 0; border: 1px solid #666; }
.desc { white-space: normal; color: #000; }
.io { display: grid; grid-template-columns: 1fr 1fr; gap: 10px; margin: 12px 0; break-inside: avoid; }
.io-box { background: #ededed; border: 1px solid #666; border-radius: 5px; padding: 7px 11px; }
.io-label { font-size: 10.5px; color: #000; margin-bottom: 3px; font-weight: 700; text-transform: uppercase; letter-spacing: .04em; }
.io-box pre { margin: 0; font-family: "SF Mono", ui-monospace, Menlo, monospace; font-size: 11.5px; white-space: pre-wrap; color: #000; }
.plain { border-left: 4px solid #000; padding-left: 11px; margin: 8px 0; }
.plain p { margin: 0; color: #000; }
.crack { background: #eaeaea; border: 1px solid #888; border-left: 4px solid #000; border-radius: 0 6px 6px 0; padding: 8px 11px; margin: 8px 0; break-inside: avoid; }
.crack .insight { margin: 0 0 6px; font-size: 12px; color: #000; }
.crack ul { margin: 0; padding-left: 18px; color: #000; }
.crack ul li { margin: 2px 0; }
/* section-2 thought-process opener */
.tp-item { margin-bottom: 10px; break-inside: avoid; }
.tp-q { font-weight: 700; font-size: 13px; color: #000; }
.tp-b { margin: 2px 0 0; color: #111; font-size: 12.5px; }
/* section-2 pseudocode block + complexity line */
.proceed { margin: 8px 0; break-inside: avoid; }
.proceed-head { display: flex; justify-content: space-between; align-items: baseline; }
.cxline { font-size: 12px; color: #111; margin: 8px 0; }
.cxline b { color: #000; }
.leap { display: flex; align-items: center; gap: 8px; flex-wrap: wrap; font-size: 11.5px; }
.leap .chip { background: #fff; border: 1px solid #666; border-radius: 5px; padding: 2px 8px; color: #000; }
.leap .chip.good { color: #000; border-color: #000; font-weight: 600; } .leap .arrow { color: #000; }
.sol { border-top: 1px solid #999; padding-top: 7px; margin-top: 7px; break-inside: avoid; }
.sol-head { display: flex; justify-content: space-between; align-items: baseline; }
.sol-label { font-weight: 700; font-size: 12.5px; color: #000; } .sol.optimal .sol-label { color: #000; }
.star { color: #000; } .cx { font-family: "SF Mono", monospace; font-size: 11px; color: #222; }
.steps { margin: 4px 0; padding-left: 20px; color: #000; }
.watch { background: #e3e3e3; border: 1.5px solid #000; border-radius: 6px; padding: 8px 11px; margin: 8px 0; break-inside: avoid; }
.watch ul { margin: 0; padding-left: 18px; color: #000; }
.answer { margin-top: 8px; }
.answer pre.code { background: #ededed; break-inside: avoid; }

/* ---- paged media (Paged.js): page numbers + running topic header ---- */
@page {
  size: A4;
  margin: 15mm 14mm 16mm;
  @bottom-center {
    content: counter(page);
    font-family: -apple-system, BlinkMacSystemFont, sans-serif;
    font-size: 10px; color: #000; font-weight: 600;
  }
  @top-right {
    content: string(topictitle);
    font-family: -apple-system, BlinkMacSystemFont, sans-serif;
    font-size: 9px; color: #333; letter-spacing: .04em;
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
        f'<div class="sub">Scaler Advanced · {PROFILE["subtitle"]} · {total} problems · '
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
