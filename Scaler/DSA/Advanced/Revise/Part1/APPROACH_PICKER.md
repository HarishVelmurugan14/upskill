# Approach Picker — Part 1

| When you see... | Think... | Source |
|---|---|---|
| Max sum contiguous subarray | **Kadane's**: track maxEndingHere, reset to 0 if < 0 | d08 |
| Water trapped between bars | **Prefix-max left + Suffix-max right** → min(L,R) − h[i] | d08 |
| Range update [L,R] add val (multiple queries) | **Difference Array**: diff[L]+=val, diff[R+1]−=val, prefix sum | d08 |
| Add 1 to number as array of digits | **Carry propagation** from right to left | d08 |
| Sum of all sub-matrices | **2D Contribution**: each cell contributes (i+1)×(j+1)×(N−i)×(M−j) | d09 |
| Search in row-sorted + col-sorted matrix | **Staircase search** from top-right corner | d09 |
| Generate spiral matrix | **4-pointer** (top/bottom/left/right) shrinking | d09 |
| First missing positive integer | **In-place sign marking**: negate A[A[i]−1] for valid values | d10 |
| Merge overlapping intervals | **Sort by start**, merge adjacent if overlap | d10 |
| Next larger permutation of array | **Next Permutation**: rightmost ascent → swap → reverse suffix | d10 |
| Single element among pairs | **XOR all** elements | d11 |
| 1 unique among elements appearing 3× | **Bit count mod 3** at each of 32 positions | d12 |
| 2 unique among elements appearing 2× | **XOR all → rightmost set bit → split + XOR groups** | d12 |
| Minimum XOR pair in array | **Sort → XOR adjacent** → take min | d12 |
| A^N mod M efficiently | **Fast/Binary Exponentiation**: square-and-multiply O(logN) | d14, d15 |
| GCD of two numbers | **Euclidean**: gcd(a,b) = gcd(b, a%b) | d15 |
| Pairs with sum divisible by M | **Remainder frequency array**: pair r with M−r | d15 |
| Subarray with sum = 0 | **Prefix sum + HashSet**: duplicate prefix = zero-sum subarray | d16, d17 |
| Count subarrays with sum = K | **Prefix sum + HashMap** of prefix-sum frequencies | d17 |
| Distinct elements in sliding window | **HashMap frequency** + slide: add new, decrement outgoing | d17 |
| Count inversions in array | **Merge Sort**: count when right < left during merge | d18 |
| Sort array of 0s, 1s, 2s | **Dutch National Flag**: 3-pointer (low, mid, high) | d19 |
| Largest number from array elements | **Custom comparator**: compare a+b vs b+a as strings | d19 |
