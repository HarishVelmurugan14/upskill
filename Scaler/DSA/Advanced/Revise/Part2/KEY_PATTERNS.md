# Key Patterns — Part 2 (Days 21–31)

## Day 21 — Binary Search on Array (`d21_Searching_BinarySearchOnArray`)
- **Start & End Index**: two separate binary searches — one for first occurrence, one for last
- **Insert Position**: standard binary search, return low when not found
- **Single Element in Sorted Array**: pairs at even/odd indices; binary search on index parity
- **Peak Element**: compare mid with mid+1; move toward the ascending side
- **Search a Matrix**: treat 2D as 1D array, binary search with row=mid/cols, col=mid%cols
- **Max Height Staircase**: binary search on answer — N*(N+1)/2 ≤ A
- **Min Cost to Build Array**: sort each row → for each element in row i, use lower_bound on row i+1 → check both lb and lb-1 for closest match → track global minimum absolute diff

## Day 22 — Binary Search Problems (`d22_Searching2_BinarySearchProblems`)
- **Square Root**: binary search on [0, A], check mid² ≤ A
- **Rotated Sorted Array Search**: find pivot, then binary search in correct half
- **Rotation Factor**: binary search for minimum element index
- **Median of Two Sorted Arrays**: binary search on the smaller array, partition both

## Day 23 — Binary Search on Answer Space (`d23_Searching_BinarySearchOnAnswerSpace`)
- **Painter's Partition**: binary search on max time; check if painting possible with A painters
- **Aggressive Cows**: binary search on minimum distance; check if B cows placeable

## Day 24/25 — Linked Lists (`d24`, `d25_LinkedList_Problems`)
- Insert at head / at position, delete at position
- Remove elements by value: iterate and relink

## Day 26 — Stacks Basics (`d26_Stacks1_BasicProblems`)
- **Postfix Expression**: stack-based eval — push numbers, pop two on operator
- **Balanced Parenthesis**: push open, pop and match on close
- **Double Character Trouble**: stack — pop if top == current, else push
- **Passing Game of Ball**: stack-based simulation
- **Redundant Braces**: stack — if popping to '(' finds no operator, redundant
- **Min Add for Valid Parentheses**: stack — count unmatched open + unmatched close

## Day 27 — Stacks: Nearest Smaller/Greater (`d27_Stack2_NearestSmallGreatElements`)
- **Monotonic Stack** pattern: maintain stack in increasing/decreasing order
- **Largest Rectangle in Histogram**: for each bar, find prev smaller + next smaller → width × height
- **Max−Min across all subarrays**: prev/next smaller + prev/next greater for contribution
- **Next Greater Element**: monotonic decreasing stack, scan right to left

## Day 28 — Queues (`d28_Queue_ImplementationAndProblems`)
- **Sliding Window Maximum**: deque maintaining decreasing order, remove expired indices
- **First Unique Character**: queue of indices + frequency array
- **Unique Letter in Growth of String**: queue + frequency tracking on growing prefix
- **Queue using Two Stacks**: push to stack1, pop by transferring to stack2

## Day 29 — Trees: Structure & Traversal (`d29_Trees1_StructureAndTraversal`)
- **Inorder** (left→root→right), **Preorder** (root→left→right), **Postorder** (left→right→root)
- **Path Sum**: recursively subtract node value, check if leaf with sum == 0
- **Equal Tree Partition**: store subtree sums in map, check if totalSum/2 exists
- **Sum Binary Tree**: validate each node == sum of children

## Day 30 — Trees: Views & Types (`d30_Trees2_ViewsAndTypes`)
- **Level Order Traversal**: BFS with queue, process level by level
- **Build Tree from Inorder + Preorder/Postorder**: recursive split using inorder index
- **Balanced Tree Check**: return -2 sentinel for unbalanced, else height
- **Left View**: BFS — first node at each level

## Day 31 — Trees: BST (`d31_Trees3_BST`)
- **Validate BST**: recursion with min/max bounds (use long to handle Integer edges)
- **Sorted Array to BST**: pick middle as root, recurse on left/right halves
- **Search BST**: go left if target < node, right if target > node
- **Max Depth**: recursion — 1 + max(leftDepth, rightDepth)
