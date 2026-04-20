# Approach Picker — Part 3

| When you see... | Think... | Source |
|---|---|---|
| Find all primes up to N | **Sieve of Eratosthenes**: mark multiples | d33 |
| Is N prime? | **Check divisibility up to √N** | d33 |
| Contiguous subarray with exact sum | **Two pointers**: expand right, shrink left | d35 |
| Generate all permutations | **Backtracking**: pick element → recurse → undo | d36 |
| Generate all subsets/subsequences | **Backtracking**: include/exclude each element → O(2^N) | d36 |
| Generate valid parentheses combinations | **Backtracking**: open < n → add '(', close < open → add ')' | d36 |
| Sort a linked list | **Merge Sort on LL**: find middle → split → sort → merge | d39 |
| Check linked list is palindrome | **Middle → reverse 2nd half → compare** | d39 |
| Merge two sorted linked lists | **Two-pointer merge**: link smaller node each step | d39 |
| Find middle of linked list | **Slow/fast pointers**: slow+1, fast+2 | d39 |
| Add two numbers as linked lists | **Traverse both + carry**: build result node by node | d39 |
| LRU Cache | **HashMap + Doubly Linked List**: O(1) get/put | d39_DLL |
| Detect cycle start in linked list | **Floyd's**: fast/slow meet → reset → both +1 → meet at start | d39_DLL |
