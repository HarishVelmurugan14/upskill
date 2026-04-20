# Key Patterns — Part 4 (Days 44–56)

## Day 44 — Heaps Introduction (`d44_Heaps_Introduction`)
- **Min Heap properties**: complete binary tree, parent ≤ children; left=2i+1, right=2i+2, parent=(i-1)/2
- **Connecting Ropes**: always combine two smallest → min heap, pop two, push sum → O(NlogN)
- **Build Min Heap**: heapify bottom-up

## Day 45 — Heaps: Sort & Greedy (`d45_Heaps_SortAndGreedy`)
- **Running Median**: max heap (lower half) + min heap (upper half); balance sizes after each insert
- **Expected Delivery Time**: priority queue based scheduling
- **Max Jobs**: greedy — sort by deadline, use min heap to track profits

## Day 46 — Heaps: Lab Sessions (`d46_Heaps_LabSessions`)
- **Kth Largest Element**: min heap of size K — top is always Kth largest
- **Distribute Candies**: greedy approach
- **Merge K Sorted Lists**: min heap of K list heads — pop smallest, push its next

## Day 48 — Heaps: Interview Problems (`d48_Heaps_InterviewProblems`)
- **Minimum Window Substring**: two-pointer sliding window + character frequency maps
- **K Places Apart**: min heap of size K — sort nearly-sorted array
- **Minimum Meeting Rooms**: sort by start, min heap of end times — pop if meeting can reuse room
- **Contains Nearby Duplicate**: sliding window HashSet of size K
- **Shaggy Special Index**: minimum distance between special indices

## Day 49 — DP: 1D (`d49_DP_OneDimensional`)
- **Climb Stairs**: dp[i] = dp[i-1] + dp[i-2] (top-down or bottom-up)
- **Fibonacci**: top-down with memoization
- **Minimum Squares**: dp[n] = 1 + min(dp[n-i²]) for all valid i

## Day 50 — DP: 2D (`d50_DP_TwoDimensional`)
- **Number of BSTs (Catalan)**: dp[n] = Σ dp[i-1] × dp[n-i] for i=1..n → O(n²)
- **N-Digit Numbers with sum B**: dp with position and remaining sum → O(A×B)
- **Max Sum in Grid**: dp traversal column by column → O(N)
- **Unique Paths with Obstacles**: grid DP — blocked cells = 0

## Day 51 — DP: Knapsack (`d51_DP_Knapsack`)
- **0/1 Knapsack**: for each item, include (if fits) or exclude; memoize on (index, capacity)
- **Unbounded Knapsack**: same as 0/1 but can re-include same item
- **Fractional Knapsack**: greedy — sort by value/weight ratio (⚠️ source marked as GEMINI CODE NOT PRACTISED)
- **Sending Alien Signal**: DP counting problem

## Day 52 — DP: Knapsack Applications (`d52_DP_KnapsackApplications`)
- **Coin Change 2** (count ways): unbounded knapsack variant — dp on (index, remaining)
- **Rod Cutting** (max profit): similar to unbounded knapsack — cut at each length
- **0/1 Knapsack Bottom-Up**: iterative table fill

## Day 53 — Graphs: Introduction (`d53_Graphs_Introduction`)
- **DFS**: recursive traversal with visited array
- **Path in Directed Graph**: DFS from source to target
- **Cycle Detection**: DFS with recursion stack (recStack[]) — back edge = cycle
- **Adjacency List**: `computeIfAbsent(from, k -> new ArrayList<>()).add(to)` — noted as "BEST WAY"

## Day 54 — Graphs: BFS & MST (`d54_Graphs_BST`)
- **Rotten Oranges**: multi-source BFS — start from all rotten, spread layer by layer
- **Minimum Cost Roads (Prim's MST)**: min heap of edges, grow MST greedily
- **Connecting Bridges**: MST variant

## Day 55 — Graphs: Dijkstra & TopSort (`d55_Graphs_Dijkstra_TopSort`)
- **Topological Sort**: Kahn's BFS — maintain in-degree, process zero-degree nodes
- **Dijkstra**: min heap of (distance, node); relax neighbors
- **Course Scheduler**: topological sort dependency ordering
- **Shortest Path (unweighted BFS)**: standard BFS level traversal

## Day 56 — Graphs: Interview Problems (`d56_Graph_InterviewProblems`)
- **Maze Minimum Distance**: BFS on grid — ball rolls until wall
- **Number of Islands**: DFS flood fill on grid — mark visited, count components
