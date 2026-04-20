# Key Patterns — Part 3 (Days 33–40)

## Day 33 — Combinatorics & Prime Numbers (`d33_Maths_CombinatoricsAndPrimeNumber`)
- **Sieve of Eratosthenes** (`allPrimes`): mark all multiples of each prime as composite → all primes up to N
- **Prime Check** (`isPrimeNumber`): iterate up to √N

## Day 35 — Two Pointers (`d35_Maths_2Pointers`)
- **Contiguous Subarray with Given Sum**: two pointers — expand right to increase sum, shrink left to decrease

## Day 36 — Backtracking (`d36_Backtracking`)
- **Generate Permutations**: try each remaining element at current position, recurse, undo
- **Generate Subsets**: include/exclude each element, recurse → O(2^N)
- **Generate Parentheses**: track open/close counts, add '(' if open < n, add ')' if close < open → O(2^2N)
- **Subsequences**: include/exclude pattern (same as subsets for strings)
- **Kth Grammar / Special Pattern**: recursive halving — relationship to parent row

## Day 39 — Linked List Sorting & Problems (`d39_LinkedList_SortingAndProblems`)
- **Merge Sort on Linked List**: find middle (slow/fast pointer) → split → sort halves → merge
- **Merge Two Sorted Lists**: two-pointer merge, link smaller node each time
- **Palindrome Check**: find middle → reverse second half → compare with first half
- **Middle Node**: slow/fast pointer (slow moves 1, fast moves 2)
- **Swap Pairs**: swap adjacent nodes recursively
- **Add Two Numbers (Reverse)**: traverse both lists, sum digits + carry, build result list
- **Reverse Linked List**: prev/curr/next pointer manipulation

## Day 39 — Doubly Linked List (`d39_DoublyLinkedList`)
- **LRU Cache**: HashMap for O(1) lookup + Doubly Linked List for O(1) insert/remove
- **Detect Cycle Start (Floyd's)**: fast/slow meet → reset one to head → both advance by 1 → meet at cycle start
- **Break Cycle**: detect start node, traverse to node before it, set next = null

## Day 40 — Morris Inorder & LCA (`d40_MorrisInorder_LCA`)
- Only static tree-builder utility methods present — no problem implementations in source

> **Note**: `d44_Heaps_Introduction.java` exists in Part3 directory but is an **empty stub** (only main method). Real Heaps content is in Part4.
> **Note**: `d40_MorrisInorder_LCA` is NOT wired into the driver's `implementations()`. The driver's `Trees()` method is defined but empty.
