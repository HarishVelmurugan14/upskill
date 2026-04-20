# Approach Picker — Part 0

| When you see... | Think... | Source |
|---|---|---|
| Multiple range-sum queries on an array | **Prefix Sum** → build prefix array, answer each query in O(1) | d03: `optimal` |
| Removing index changes even/odd sums | **Even + Odd prefix sums** → after removed index, even↔odd swap | d03: `specialIndices` |
| Find index where left sum = right sum | **Prefix sum** → leftSum vs totalSum − leftSum − A[i] | d03: `equilibriumIndex` |
| Count pairs where X before Y | **Carry Forward** → carry running count of X from left | d04: `optimal` |
| Pick elements only from left/right edges | **Prefix + Suffix sums** → try all splits | d04: `maxPossibleElementConsideringNEdgeElements` |
| Find elements greater than all to their right | **Carry max from right→left** | d04: `leaderElements` |
| Smallest subarray with both min & max | **Track last positions** of min/max scanning from right | d04_SubArrays: `optimal` |
| Max profit buy low sell high (one transaction) | **Carry max from right** → profit = maxRight[i] − A[i] | d04_SubArrays: `maxProfitOnOnlyOneTransaction` |
| Sum of all subarrays efficiently | **Contribution Technique** → A[i] × (i+1) × (N−i) | Contribution: `approach3` |
| Fixed-size window: sum / average / condition | **Sliding Window** → add incoming, remove outgoing element | Sliding: `optimal` |
