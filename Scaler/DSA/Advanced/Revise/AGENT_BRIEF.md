# DSA Revisor — Agent Brief

**Purpose:** the user references this file before asking me to add DSA problems to the revisor site. Read it top-to-bottom every time before generating problems.

---

## 1. Project location

- **Repo root:** `/Users/harish-10327/Privatespace/Code/Github/upskill`
- **Live page:** `dsa-revisor.html` (URL: `upskill/dsa-revisor.html`)
- **Data folder:** `Scaler/DSA/Advanced/Revise/data/`
- **Visualizer engine:** `Scaler/DSA/Advanced/Revise/viz/engine.js` + `viz/viz-kinds.js`
- **Source code (the user's Java solutions):** `Scaler/DSA/Advanced/Part0/` … `Part4/` — sub-folders by day (`d21_…`, `d22_…`, etc.)

---

## 2. Topic convention

- The user sets the topic via a line like: **`Current Topic: Arrays`**.
- That topic stays active for all subsequent problem requests until overridden.
- If no topic is set and one isn't obvious from context — **ask**.
- Topic slug rule: lowercase + hyphens. `Arrays` → `arrays`, `Binary Tree` → `binary-tree`.

For each topic two files exist:
- `data/<slug>.json` — array of problems
- `data/<slug>.notes.json` — topic-level notes (summary, thought process, patterns, mistakes, edge cases)

Both must be registered in `data/index.json`:
```json
{
  "name": "<Display Name>",
  "file": "<slug>.json",
  "notesFile": "<slug>.notes.json"
}
```

If a topic doesn't exist yet, create both files and register them.

---

## 3. Problem JSON schema (mandatory fields)

```json
{
  "id": "<topic-slug>-<short-kebab-name>",
  "title": "Human-readable title",
  "subTopic": "Sub-category (one line)",
  "tags": ["Tag1", "Tag2", "Tag3"],
  "description": "Problem statement. Multi-line OK (\\n in JSON).",
  "sampleInput": "A = [...]\\nB = 5",
  "sampleOutput": "...",
  "story": "ONE memorable analogy / hook (1-3 sentences). This is the user's mental hook for the problem.",
  "solutions": [
    {
      "label": "Brute" | "Better" | "Optimal" | "Recursive" | "Iterative" | "Alternative — XOR" | etc,
      "complexity": { "time": "O(...)", "space": "O(...)" },
      "pseudocode": "compact, multi-line, plain English steps",
      "story": "one-line intuition for THIS solution",
      "code": "<Java code in user's style — see §4>"
    }
  ],
  "viz": { "kind": "<viz-kind>", "data": { ... } }
}
```

### Rules

- **Always include Optimal.** Add Brute/Better only if they teach something distinct.
- **`code` is Java only.** No `import`, no `class` wrapper — just the method(s) plus tiny private helpers.
- **`story` and per-solution `story`** are non-negotiable. They are why the user can re-derive the problem cold.

---

## 4. User's Java coding style (match this)

- Method name = verbose, describes the problem (e.g. `paintersPartitionMinimumTimeToPaint`, `searchForStartAndEndIndexOfAnElementInAnArray`).
- Block-comment headers (`// Complexity : Time : ...` / `// Complexity : Space : ...`) **NOT inside `code` strings** — complexity lives in the structured `complexity` field. Skip comment headers inside code blocks.
- Edge cases checked up front (length 0, null, etc.).
- **`int mid = low + (high - low) / 2;`** (overflow-safe).
- **`while (low <= high)`** — inclusive bounds.
- **`long`** when products / sums can overflow int.
- Greedy / feasibility predicates extracted into `private boolean isXyzPossible(...)` (examples in repo: `canPlaceCows`, `canBeAllotted`, `isPaintingCompletionPossible`).
- 4-space indentation. Braces on same line. No tabs.

---

## 5. Source the user's Java

Before writing new code from scratch, **search the `Scaler/DSA/Advanced/` package first**. It contains the user's verbatim Scaler solutions, organized as:

```
Scaler/DSA/Advanced/
  Part0/    # foundations: arrays, hashing, sorting, recursion basics
  Part1/    # bit manipulation, math, recursion, backtracking
  Part2/    # binary search, two pointers, sliding window, linked list, stacks/queues
  Part3/    # trees, BST, tries, heaps, greedy
  Part4/    # graphs, DP
```

Each `Part*/` folder contains day-files named `d<NN>_<Topic>_<Subtopic>.java` (e.g. `d21_Searching_BinarySearchOnArray.java`, `d25_LinkedList_Problems.java`). Each file holds multiple problem methods.

**Workflow for sourcing code:**

1. Use `grep_search` or `file_search` across `Scaler/DSA/Advanced/Part*/**/*.java` for the problem name, a unique sample I/O value, or a likely method name.
2. If found → copy the user's solution verbatim (lightly polish only if needed for the schema).
3. If not found → write from scratch matching §4 style.

Aim ~80% sourced, 20% written. **Never invent code without searching first.**

---

## 6. Visualizer (`viz.kind`)

Existing kinds (in `viz/viz-kinds.js`):

| Kind | Use for | Required `data` |
|---|---|---|
| `bs-bound` | first/last occurrence | `A`, `B`, `mode: 'both'` |
| `bs-lower-bound` | lower_bound / insert position | `A`, `B` |
| `bs-peak` | peak in unimodal array | `A` |
| `bs-single-element` | sorted-with-loner | `A` |
| `bs-matrix` | 2D sorted matrix search | `A` (2D), `B` |
| `bs-on-answer` | minimize/maximize answer | `low, high, target, label, predicate` |
| `bs-rotated` | rotated sorted array | `A`, `B` |
| `bs-partition` | median of two sorted | `A, B` |
| `sliding-window` | window of size K | `A, K, B?` |
| `ll-traverse` | walk a list | `values, label?` |
| `ll-reverse` | full or K-group reversal | `values, from?, to?, groupSize?` |
| `ll-insert` | insert at position | `values, insertValue, insertAt` |
| `ll-delete` | delete at position | `values, deleteAt` |
| `ll-two-pointer` | N-from-end / slow-fast | `values, gap` |

**If a topic needs a brand-new viz kind** (e.g. `tree-traverse`, `graph-bfs`, `dp-table`), add it to `viz/viz-kinds.js` using the engine helpers (`E.cell`, `E.arrayRow`, `E.pointerRow`, `E.numberLine`, `E.llRow`, etc.) — same pattern as existing kinds: `{ makeSteps(data), render(canvas, step, data), footer?(data) }`. Then register it in the `VIZ_KINDS` map at the bottom of the file.

If no viz fits and writing one is out of scope, set `viz: { "kind": "ll-traverse", "data": { "values": [...], "label": "fallback" } }` rather than leaving it broken.

---

## 7. Topic notes schema (`<slug>.notes.json`)

```json
{
  "topic": "Display Name",
  "summary": "One-paragraph summary of the whole topic.",
  "thoughtProcess": [
    { "title": "1. ...", "body": "..." },
    /* 4–6 items */
  ],
  "commonMistakes": [
    { "title": "...", "wrong": "...code...", "right": "...code...", "why": "..." },
    /* 6–8 items */
  ],
  "keyPatterns": [
    { "name": "...", "when": "...", "template": "...code...", "examples": ["...", "..."] },
    /* 6–8 items */
  ],
  "edgeCases": [ "string", "string", /* 8–10 items */ ]
}
```

When the user adds the FIRST problem to a new topic, also create the notes file. Don't keep deferring it.

---

## 8. Workflow checklist (do this every time)

1. Confirm topic (from `Current Topic:` line or ask).
2. Determine target file: `data/<slug>.json`. Create if missing.
3. Update `data/index.json` if registering a new topic.
4. For each problem requested:
   - Look up the user's Java in `Scaler/DSA/Advanced/Part*/`.
   - Build the problem object per §3 + §4.
   - Pick the right `viz.kind` per §6 (or add a new one).
5. If a new topic: create `<slug>.notes.json` per §7.
6. Validate: `node -e "JSON.parse(require('fs').readFileSync('Scaler/DSA/Advanced/Revise/data/<slug>.json'))"`.
7. Report file paths + count summary back to the user. Don't dump JSON in chat.

---

## 9. Style guardrails

- **No emojis in code** (only in UI text / `msg` / story).
- **No external libraries.** Pure HTML/CSS/vanilla JS.
- **GitHub-Dark palette** already defined in CSS vars (`--blue --green --red --amber --purple --orange --teal`). Use them.
- **Mobile-first** — visualizer rows scroll horizontally if needed.
- Existing files are GitHub Pages friendly (relative paths, `fetch()` over HTTPS). Don't break that.

---

## 10. When in doubt

- Read an existing topic file end-to-end (e.g. `data/binary-search.json`) before generating a new one. It's the source of truth for shape, tone, density.
- Read `viz/viz-kinds.js` end-to-end before inventing a new viz kind.
- If a request is ambiguous (no topic, vague problem name), **ask one question** instead of guessing.

---

## 11. Gold-standard worked example

Use this as the calibration anchor for tone, density, and code style. Every new problem should *feel* like this.

```json
{
  "id": "bs-peak-element",
  "title": "Find a Peak Element",
  "subTopic": "BS on Unimodal",
  "tags": ["Binary Search", "Peak", "Unimodal", "Gradient"],
  "description": "Find any element ≥ both neighbours. Corners count if they beat their one neighbour. Guaranteed a single peak. Solve in O(log N).",
  "sampleInput": "A = [5, 17, 100, 11]",
  "sampleOutput": "100",
  "story": "If you're standing on a slope, walk uphill. That direction must contain a peak. Binary search just walks uphill twice as fast.",
  "solutions": [
    {
      "label": "Brute",
      "complexity": { "time": "O(N)", "space": "O(1)" },
      "pseudocode": "scan; return A[i] when both neighbours are smaller",
      "story": "Just look. Linear and obvious.",
      "code": "public int linearScan(int[] A) {\n    int N = A.length;\n    if (N == 1) return A[0];\n    if (A[0] >= A[1]) return A[0];\n    if (A[N-1] >= A[N-2]) return A[N-1];\n    for (int i = 1; i < N - 1; i++) {\n        if (A[i] >= A[i-1] && A[i] >= A[i+1]) return A[i];\n    }\n    return -1;\n}"
    },
    {
      "label": "Optimal",
      "complexity": { "time": "O(log N)", "space": "O(1)" },
      "pseudocode": "Pre-check both boundaries.\nLoop low=1, high=N-2:\n  if A[mid] beats both neighbours → return A[mid]\n  else if A[mid] > A[mid-1]      → climbing, go right\n  else                            → descending, go left",
      "story": "Don't think 'find target'. Think 'walk toward higher ground'. A peak must exist in the direction of increase.",
      "code": "public int peakElement(int[] A) {\n    int N = A.length;\n    if (N == 1) return A[0];\n    if (A[0]     >= A[1])     return A[0];\n    if (A[N - 1] >= A[N - 2]) return A[N - 1];\n\n    int low = 1, high = N - 2;\n    while (low <= high) {\n        int mid = low + ((high - low) / 2);\n        if (A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) return A[mid];\n        else if (A[mid] > A[mid - 1]) low  = mid + 1;\n        else                          high = mid - 1;\n    }\n    return -1;\n}"
    }
  ],
  "viz": { "kind": "bs-peak", "data": { "A": [5, 17, 100, 11] } }
}
```

### What this example locks in

- **`story` length** — 1–3 sentences with a concrete physical analogy ("walk uphill").
- **Per-solution `story`** — one punchy line that is the *idea*, not a restatement.
- **`pseudocode`** — short, indented, plain-English with `→` for branches. Not Java.
- **`code` style** — exact: edge cases up front, `low + ((high - low) / 2)`, `while (low <= high)`, descriptive method name, no comments, no class wrapper.
- **`tags`** — 3–5 items mixing the algorithm family + specific technique + problem trait.
- **`subTopic`** — one short phrase (used as the chip under the title).
- **Brute is included when it's instructive** (here it shows what "walk uphill faster" improves on). Skip it for problems where Brute = Optimal.

### Anti-patterns to avoid

- ❌ Generic stories ("This is a binary search problem"). The story must be re-derivable from the analogy alone.
- ❌ Java comments inside `code` strings (`// Complexity: …`). Complexity lives in the `complexity` field.
- ❌ Pseudocode written in pseudo-Java with semicolons and braces. Keep it human.
- ❌ More than 4 solutions. Brute + Better + Optimal + maybe one Alternative is the ceiling.
- ❌ A `viz.kind` that doesn't exist in `viz/viz-kinds.js`. Either reuse or add one.
