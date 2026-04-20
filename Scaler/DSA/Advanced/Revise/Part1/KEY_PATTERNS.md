# Key Patterns — Part 1 (Days 8–19)

## Day 08 — Arrays 1D (`d08_Arrays_OneDimensional`)
- **Kadane's Algorithm**: max contiguous subarray sum in O(N) — track currentSum, reset to 0 if negative
- **Rain Water Trap**: water at i = min(maxLeft[i], maxRight[i]) − height[i]; prefix/suffix max arrays
- **Difference Array (Beggars)**: range [L,R] add val → diff[L] += val, diff[R+1] −= val, then prefix sum
- **Add One to Number**: carry propagation from least significant digit

## Day 09 — Arrays 2D (`d09_Arrays_TwoDimensional`, `d9_Arrays_2dMatrix`)
- **Sub-matrix sum**: contribution technique in 2D — each A[i][j] contributes (i+1)×(j+1)×(N−i)×(M−j) times
- **Spiral Matrix Generation**: 4-pointer approach (top, bottom, left, right), shrink boundaries per layer
- **Staircase Search**: in row+col sorted matrix, start top-right; go left if target < current, down if >
- **Row with max 1s**: binary search in binary sorted matrix, O(rows + cols)
- **90° Rotation**: transpose in-place + reverse each row

## Day 10 — Interview Problems (`d10_Arrays_InterviewProblems`)
- **First Missing Natural Number**: in-place sign marking — for valid A[i] in [1,N], negate A[A[i]−1]
- **Merge Intervals**: sort by start, merge overlapping in O(N)
- **Insert Interval + Merge**: insert then merge in single pass
- **Next Permutation**: find rightmost ascent, swap with next larger to its right, reverse suffix

## Day 11 — Bit Manipulation 1 (`d11_BitWiseManipulations1`)
- Set/Unset/Toggle/Check ith bit using mask `1 << i`
- **Count set bits**: `N & (N−1)` strips rightmost set bit → O(logN)
- **Single unique** in array of pairs: XOR all elements
- **Nth Magic Number**: binary representation of N maps to powers of 5

## Day 12 — Bit Manipulation 2 (`d12_BitWiseManipulations2`)
- **Triple Trouble** (1 unique, rest 3×): count bits at each of 32 positions mod 3
- **Two Unique Elements**: XOR all → rightmost set bit → partition into two groups → XOR each
- **Min XOR**: sort array, XOR adjacent pairs, take minimum
- **Sum of OR on all subarrays**: per bit position, count contributing subarrays via contribution technique
- **Strange Equality**: find smallest Y > A where A & Y == 0, compute A XOR Y
- **Missing 2 Numbers in [1..N+2]**: XOR-based splitting by set bit position

## Day 13 — Recursion 1 (`d13_Recursions1`)
- Base case + recursive call: sum, factorial, fibonacci, print ascending/descending
- **Skip character/string**: build result recursively, skip matching target

## Day 14 — Recursion 2 (`d14_Recursions2`)
- **Magic Number**: recursively sum digits until single digit, check if == 1
- **Tower of Hanoi**: move N−1 to helper → move Nth to dest → move N−1 from helper → O(2^N)
- **Fast/Binary Exponentiation**: A^N = (A^(N/2))² if even, A × A^(N−1) if odd → O(logN)

## Day 15 — Modular Arithmetic & GCD (`d15_ModularArithmeticAndGCD`)
- **Binary Exponentiation with mod**: (A^N) % C, handle negative remainders in Java
- **Euclidean GCD**: gcd(a,b) = gcd(b, a%b) — works for negative and positive
- **Pair sum divisible by M**: remainder frequency array, pair remainder r with M−r

## Day 16 — Hashing 1 (`d16_Hashing1_Introduction`)
- HashSet for distinct elements, first repeating
- **Subarray sum = 0**: prefix sum in HashSet — duplicate prefix sum means zero-sum subarray between
- Common elements via frequency map intersection

## Day 17 — Hashing 2 (`d17_Hashing2_Problems`)
- **Pair sum/diff = K**: HashMap lookup for complement (K − A[i]) or (A[i] ± K)
- **Count subarrays with sum K**: prefix sum + HashMap of prefix-sum frequencies
- **Distinct in window K**: sliding HashMap with frequency tracking
- **Longest subarray sum 0/K**: prefix sum + first-occurrence HashMap

## Day 18 — Merge Sort (`d18_Sort_CountMergeSort`)
- **Merge Sort**: divide + merge → O(NlogN) time, O(N) space
- **Inversion Count**: count during merge — when right element picked, inversions += remaining left
- **Merge two sorted arrays**: two-pointer merge

## Day 19 — Quick Sort & Comparator (`d19_Sort_QuickSortAndComparator`)
- **Quick Sort**: pick pivot, partition, recurse on halves
- **Dutch National Flag (Sort by Color)**: 3-pointer for 0s/1s/2s
- **Largest Number**: custom comparator — compare a+b vs b+a as strings
