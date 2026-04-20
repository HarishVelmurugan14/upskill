# Key Patterns — Part 0 (Days 3–7)

## Day 03 — Prefix Sum (`d03_Arrays_PrefixSum`)
- Pre-compute prefix array → answer range-sum queries in O(1) per query
- Even/Odd prefix sums for **Special Indices**: removing index i swaps even↔odd positions after it
- **Equilibrium Index**: find index where left sum == right sum using total sum − running left sum

## Day 04 — Carry Forward (`d04_Arrays_CarryForward`)
- Scan from one direction, carry a running aggregate to avoid re-scanning
- Count AG pairs: carry count of 'a' seen so far; at each 'g', add to answer
- Max from N edge elements: prefix sum from left + suffix sum from right
- Leader Elements: scan right→left carrying max seen so far

## Day 04 — SubArrays (`d04_Arrays_SubArraysWithCarryForward`)
- Total subarrays of array length N = N×(N+1)/2
- Smallest subarray containing both min & max: scan right→left, track last positions of current min and max
- **Max Profit (Buy & Sell one transaction)**: carry max from right, profit = maxRight − A[i]

## Day 04/05 — Contribution Technique (`ContributionTechnique_4`)
- Each A[i] appears in **(i+1) × (N−i)** subarrays → sum of all subarrays in O(N)
- Source comment: "asked in google, meta"

## Day 05 — Sliding Window (`SlidingWindowTechnique_5`)
- Fixed-size window of length K: slide by adding A[i] and removing A[i−K]
- Applications: max-sum subarray of size K, check if subarray with length B has sum C, least-average subarray

## Day 07 — Memory Management (`d7_MemoryManagement`)
- Stack memory: primitives + reference variables; Heap memory: actual object data (arrays, objects)
- Pass-by-value (primitives) vs pass-by-reference (arrays/objects)
- Re-initialization inside a method creates new heap object — caller unaffected unless returned

> **Note**: The driver file (`Advanced_DSA_Part0.java`) only wires `d03` and `d04` files. `ContributionTechnique_4`, `SlidingWindowTechnique_5`, and `d7_MemoryManagement` exist in Part0 directory but are NOT called from the driver's `implementations()` method.
