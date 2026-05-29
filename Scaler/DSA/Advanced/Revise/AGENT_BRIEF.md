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

  "story": "[legacy 1-liner — keep for backward compat, mirror the plain version]",
  "plain": "PLAIN ENGLISH explanation of the problem. 3-6 sentences. Direct, like explaining to a smart friend. No analogies, no jargon. Used in 'In Plain English' card.",
  "tale": "STORYBOOK version of the problem. 4-8 sentences in narrative voice. A vivid scene with a character, a stake, and a moment of insight that maps 1:1 to the algorithm's central idea. This is what the user re-reads to remember the problem cold. Used in 'Once Upon an Algorithm' card with a drop cap.",

  "solutions": [
    {
      "label": "Brute" | "Better" | "Optimal" | "Recursive" | "Iterative" | "Alternative — XOR" | etc,
      "complexity": { "time": "O(...)", "space": "O(...)" },

      "plain":   "PLAIN ENGLISH explanation of THIS approach. 3-5 sentences. What you do, in order, without code.",
      "tale":    "STORYBOOK version of THIS approach. 3-6 sentences. Use the same characters/world as the problem-level tale when possible — continuity helps memory.",
      "approach": [
        "Step 1 in plain imperative English — one sentence, no code.",
        "Step 2 — the action and its purpose.",
        "Step 3 — keep going, 5 to 9 steps typical.",
        "..."
      ],

      "pseudocode": "compact multi-line plain-English / symbolic steps (shown in monospace)",
      "story":      "[legacy 1-liner — keep for backward compat, mirror plain]",
      "code":       "<Java in user's style — see §4>"
    }
  ],
  "viz": { "kind": "<viz-kind>", "data": { ... } }
}
```

### Rules

- **Always include Optimal.** Add Brute/Better only if they teach something distinct.
- **`plain`, `tale`, and `approach`** are MANDATORY on every solution. They drive the parchment cards the user actually reads.
- **`plain` voice**: 6th-grade English. Short sentences. No metaphors. Describe the mechanism.
- **`tale` voice**: childhood-storybook narrator. Concrete character (hiker, watchman, blindfolded farmer, three friends Past/Present/Future, etc). Specific verbs. Italic serif on screen — write so it reads aloud well.
- **`approach`**: ordered array of strings. Each entry = one atomic step the user could execute on paper. 5-9 entries typical. No code, no symbols beyond array notation.
- **`code` is Java only.** No `import`, no `class` wrapper — just the method(s) plus tiny private helpers.
- **Keep `story`** (legacy field) so old viewers don't break — mirror the first sentence of `plain` if you don't have a separate one.
- See `bs-peak-element`, `bs-aggressive-cows`, and `ll-reverse` as the **gold-standard worked examples** of the dual-story + approach schema.

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

**Gold-standard kind (use this for ALL new problems):** `scripted-array`.

Each step in the script supports:
- `label` — short ALL-CAPS banner (`"SETUP"`, `"ITERATION 2 — PICK MID"`, `"DONE"`).
- `msg` — HTML narration shown in the dark band. Wrap key values in `<strong>`. Tie it to the actual sample input.
- `marks` — `{ "0": "c-mid", "3": "c-eliminated" }` per-index cell class (array mode only).
- `ptrs` — `{ "low": 0, "mid": 2, "high": 3, "ans": 2 }` adds L/M/H/★ markers (array mode only).
- `overlay` — yellow callout HTML after the visual. Use for rules / formulas with `<code>` tags.
- `html` — escape hatch: raw HTML using the `v-*` CSS vocabulary (cells, arrows, rows, labels, number-lines, stats). Use for matrices, linked lists, trees, custom layouts.

**Quality bar (mandatory, no exceptions):**
- **8 to 12 steps minimum** per problem. Setup + every meaningful iteration + Done.
- **3+ overlays** per problem.
- **Every step has a `label`**.
- **Every value in `msg` is tied to the actual sample input** and wrapped in `<strong>`.
- **Every step visually changes** vs the previous (a pointer moves, a cell flips colour, an arrow flips).
- **Reference these three as the bar:** `bs-peak-element`, `bs-aggressive-cows`, `ll-reverse` in `binary-search.json` and `linked-list.json`.

**`v-*` CSS vocabulary** (already defined in `dsa-revisor.html`, do NOT add CSS):

| Class | Purpose |
|---|---|
| `v-row` | flex row container, gap 4px, wraps |
| `v-cell` | dark cell, base |
| `v-cell v-active` | orange (being inspected) |
| `v-cell v-low` | blue (left boundary) |
| `v-cell v-high` | purple (right boundary) |
| `v-cell v-mid` | red (probe) |
| `v-cell v-result` | green (answer) |
| `v-cell v-eliminated` | greyed strikethrough |
| `v-cell v-peak` | orange glow |
| `v-cell v-current` | purple glow (LL curr) |
| `v-cell v-new` | green glow (newly added) |
| `v-cell v-removed` | red strikethrough |
| `v-cell v-null` | dashed outline (NULL/∅) |
| `v-arrow` | neutral grey → |
| `v-arrow v-rev` | orange ← (reversed) |
| `v-arrow v-new` | green → (new link) |
| `v-arrow v-cut` | red strikethrough |
| `v-label` | small uppercase blue caption |
| `v-matrix` | matrix container (column flex) |
| `v-numline` + `v-numline-track` + `v-numline-active` + `v-numline-marker v-low/v-high/v-mid/v-ans` | number-line viz |
| `v-stat` | pill-style live readout (`<span class='v-stat'>painters=<strong>3</strong></span>`) |

**Legend mapping** (`data.legend` accepts only `c-*` names; map `v-*` semantics back to nearest):
- `c-low` `c-high` `c-mid` `c-active` `c-eliminated` `c-result` `c-peak` `c-input`

**Legacy kinds** (still registered, do not use for new work): `bs-bound`, `bs-lower-bound`, `bs-peak`, `bs-single-element`, `bs-matrix`, `bs-on-answer`, `bs-rotated`, `bs-partition`, `sliding-window`, `ll-traverse`, `ll-reverse`, `ll-insert`, `ll-delete`, `ll-two-pointer`. Convert these to `scripted-array` whenever revisiting a problem.

---

## 6a. Problem images

User screenshots live at: `Scaler/DSA/Advanced/Revise/data/images/<topic-slug>/<topic-slug>-NN.png` (zero-padded, ordered by capture time = problem order).

In each problem's JSON, include a top-level field:
```json
"image": "images/<topic-slug>/<topic-slug>-NN.png"
```
The page renders it as a faint reference thumbnail under the problem description.

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
