# Approach Picker — Part 4

| When you see... | Think... | Source |
|---|---|---|
| Combine items at minimum total cost | **Min Heap**: always combine two smallest | d44 |
| Running median of stream | **Max heap (lower) + Min heap (upper)**: balance sizes | d45 |
| K-th largest in stream / array | **Min heap of size K**: top = Kth largest | d46 |
| Merge K sorted lists | **Min heap of K heads**: pop min, push its next | d46 |
| Minimum window containing all chars | **Two-pointer + frequency maps**: expand right, shrink left | d48 |
| Sort nearly-sorted array (K places) | **Min heap of size K** | d48 |
| Minimum meeting rooms / overlapping intervals | **Min heap of end times**: pop if compatible | d48 |
| Fibonacci / Climb stairs variations | **1D DP**: dp[i] = dp[i-1] + dp[i-2] | d49 |
| Min squares summing to N | **1D DP**: dp[n] = 1 + min(dp[n-i²]) | d49 |
| Count BSTs with N nodes (Catalan) | **2D DP**: dp[n] = Σ dp[i-1]×dp[n-i] | d50 |
| N-digit numbers with digit sum B | **2D DP**: (position, remaining sum) | d50 |
| Grid paths with obstacles | **Grid DP**: blocked = 0, else dp[i][j] = dp[i-1][j] + dp[i][j-1] | d50 |
| Select items with weight limit | **0/1 Knapsack DP**: include (if fits) or exclude | d51 |
| Select items with unlimited supply | **Unbounded Knapsack**: re-include same item | d51 |
| Number of ways to make change | **Coin Change (unbounded knapsack variant)** | d52 |
| Max profit cutting rod | **Rod Cutting DP** (unbounded knapsack variant) | d52 |
| Find path / reachability in graph | **DFS** with visited array | d53 |
| Detect cycle in directed graph | **DFS + recursion stack** (recStack[]) | d53 |
| Multi-source spread (rotten oranges) | **Multi-source BFS**: start all sources in queue | d54 |
| Minimum spanning tree | **Prim's**: min heap of edges, grow MST greedily | d54 |
| Shortest path (weighted, no negatives) | **Dijkstra**: min heap of (dist, node), relax edges | d55 |
| Task dependency ordering | **Topological Sort**: Kahn's BFS with in-degree | d55 |
| Shortest path (unweighted) | **BFS** level-by-level | d55 |
| Maze / grid shortest path | **BFS on grid** with valid-cell checks | d56 |
| Count connected components in grid | **DFS flood fill**: mark visited, count starts | d56 |
