# Approach Picker — Part 2

| When you see... | Think... | Source |
|---|---|---|
| Find element / insert position in sorted array | **Binary search** | d21 |
| Single element in sorted array of pairs | **Binary search on index parity** (even/odd) | d21 |
| Find peak element | **Binary search**: compare mid vs mid+1 | d21 |
| Square root of N | **Binary search on [0,N]**: check mid² ≤ N | d22 |
| Search in rotated sorted array | **Binary search**: find sorted half, check target range | d22 |
| Min time to paint boards / max-min distance | **Binary search on answer space** + feasibility check | d23 |
| Evaluate postfix expression | **Stack**: push numbers, pop 2 on operator | d26 |
| Check balanced parentheses | **Stack**: push open, pop+match on close | d26 |
| Remove consecutive duplicate chars | **Stack**: push if ≠ top, pop if == top | d26 |
| Check redundant braces | **Stack**: pop to '(' — if no operator found, redundant | d26 |
| Nearest smaller/greater element | **Monotonic stack** | d27 |
| Largest rectangle in histogram | **Prev smaller + next smaller** via monotonic stack → width | d27 |
| Max−Min across all subarrays | **Contribution**: prev/next smaller & greater via stack | d27 |
| Maximum in sliding window | **Deque** maintaining decreasing order | d28 |
| First unique character in stream | **Queue** of indices + frequency map | d28 |
| Build tree from inorder + preorder/postorder | **Recursive split**: root from pre/post, partition inorder | d30 |
| Validate BST | **Recursion with long min/max** bounds | d31 |
| Sorted array to balanced BST | **Pick middle as root**, recurse left/right halves | d31 |
