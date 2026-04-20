# Common Mistakes — Part 4

## Source Annotation Errors
- **`climbStairs_bottomUp`** tagged `//LC40` in driver — LC40 is "Combination Sum II". Correct LC is **LC70** (Climbing Stairs).
- **`minimumCostRoads`** tagged `//1584` — **no LC prefix** in source. Should be LC1584.
- **`fractionalKnapsack`** tagged `// GEMINI CODE NOT PRACTISED` — auto-generated code, not manually verified or practiced.

## Critical Source Comments

### TLE Warning (d45)
> `expectedDeliveryTime_tle` — marked `// TLE`
- This is the brute-force version that times out — use the priority queue version instead.

### DP Minimum Squares (d50)
> "In case of 12 there are 3 set of possibilities for minimum square, so only the minimum one need to be stored in dp not all"
- Don't store all possibilities — only track the minimum count.

### Floating Point Epsilon (d52)
> "Add a small epsilon to counter floating-point inaccuracies before flooring"
> "A common epsilon value is 1e-9 or 1e-10"
- In `fractionalKnapsack` calculations, add epsilon before floor/cast to avoid off-by-one from floating-point.

### Graph DFS — Check All Neighbors (d53)
> "if directly returned other neighbours will not be visited"
- In DFS for path finding, do NOT return immediately on finding target — must check via return value and continue checking other neighbors.

### Cycle Detection — Recursion Stack (d53)
> "parent node of the same stack is called again"
- Back edge to a node in the current recursion stack = cycle. Distinguish from cross edges to previously-visited-but-finished nodes.

### Adjacency List Best Practice (d53)
> "NOTE : BEST WAY — `adj.computeIfAbsent(from, k -> new ArrayList<>()).add(to)`"
- Cleanest way to build adjacency lists in Java.

## Heap Properties Reminder (d44)
From source comments:
- "Binary Tree in the hide"
- "all levels should have 2 except last"
- "all child should be larger for min heap"
- `left = 2i+1`, `right = 2i+2`, `parent = (i-1)/2`

## HK Codes Quick Reference
| HK # | Problem | File |
|---|---|---|
| HK1001 | Climb Stairs | d49 |
| HK1002 | Fibonacci | d49 |
| HK1003 | Max Sum (Grid) | d50 |
| HK1004 | N-Digit Numbers | d50 |
| HK1005 | Catalan / Num BSTs | d50 |
| HK1006 | 0/1 Knapsack | d51 |
| HK1007 | Unbounded Knapsack | d51 |
| HK1009 | Rod Cutting | d52 |
| HK1010 | Coin Change 2 | d52 |
