# Common Mistakes — Part 2

## Source Code Issues
- **d21**: Method name typo: `searhAMatrix` — missing 'c' in "search"

## Edge Cases from Source

### d21 — Binary Search
- `if (N == 0)` — empty array guard for searchForStartAndEndIndex
- `if (N == 1)` — single element array guard for singleElement and peakElement
- Peak element: check `A[0] >= A[1]` and `A[N-1] >= A[N-2]` — peak could be at boundaries
- `minimumCostToBuildAnArray`: check both `lb` (first ≥ val) **and** `lb-1` (last < val) — closest element can be on either side of the lower_bound index
- `minimumCostToBuildAnArray`: guard `lb != B` before accessing `C[i+1][lb]` (lb == B means no element ≥ val exists)

### d22 — Square Root
- `if (A == 0)` — zero input guard

### d23 — Binary Search on Answer Space
- `if (C.length == 0) return 0` — empty boards array
- `if (A > C.length) A = C.length` — more painters than boards
- `if (totalSum % 2 == 1)` — odd total sum edge case (aggressive cows)

### d26 — Balanced Parenthesis
- Uses `(N & 1) != 0` for odd-length check — odd-length strings are always unbalanced

### d28 — Unique Letter in Growth
- Tagged `// Similar to LC1429` — NOT an exact LC match

### d29 — Sum Binary Tree
- `if (leftSum == -1 || rightSum == -1) return -1` — propagate failure sentinel through recursion
- `if (A.left == null && A.right == null && B == A.val)` — leaf node check for path sum

### d30 — Build Tree
- LST element count formula: `r = index - in_s + post_s` (postorder) or `r = index - in_s + pre_s` (preorder)
- These index calculations are critical and easy to get wrong

### d31 — Validate BST
- Uses `long min, long max` instead of `int` — handles Integer.MIN_VALUE/MAX_VALUE edge cases
- `if (A.val >= min && A.val <= max)` — inclusive check with long bounds

### d27 — Sliding Window Max (Deque)
- `while (!deque.isEmpty() && A[i] >= A[deque.peekLast()])` — remove smaller elements from back before adding new
