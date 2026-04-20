# Common Mistakes — Part 3

## Source Annotation Error
- **d36_Backtracking.java line 29**: `generateSubsets_yahnit` labeled as `// LC22` in local main()
- **Correct**: LC78 (Subsets). Driver file `Advanced_DSA_Part3.java` correctly says `// LC 78 // Q2`
- LC22 is Generate Parentheses, NOT Subsets

## Critical Source Comments

### Backtracking — Copy vs Reference (d36)
> `all.add(new ArrayList<>(currentList)); // VERY IMPORTANT`
- Must create a **new ArrayList copy** when adding to results
- If you add the reference directly, all entries will point to the same (eventually empty) list
- This is the #1 backtracking bug

### Add Two Numbers — Overflow Warning (d39_LinkedList)
> `// do not create util as it may cause overflow`
- Do NOT convert linked list to integer — large numbers will overflow
- Process digit by digit with carry

## Driver Wiring Notes
- `d40_MorrisInorder_LCA` exists in Part3 but is **NOT wired** into `implementations()` — the `Trees()` method in the driver is defined but completely empty
- `d44_Heaps_Introduction.java` in Part3 is an **empty stub** (only has main method) — real Heaps content is in Part4's `d44_Heaps_Introduction.java`
- The driver's `implementations()` calls: `Searching()`, `BackTracking()`, `DoublyLinkedList()` — that's it

## Empty definitions()
- All Part3 source files have empty `definitions()` methods (`/**/`)
