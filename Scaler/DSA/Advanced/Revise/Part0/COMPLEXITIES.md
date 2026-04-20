# Complexities — Part 0

> Only complexities explicitly stated in source code `// Complexity` comments are listed.

## d03_Arrays_PrefixSum

| Method | Time | Space |
|---|---|---|
| `bruteForce` | O(n×q) | O(1) |
| `optimal` | O(n) | O(n+q) |

## d04_Arrays_CarryForward

| Method | Time | Space |
|---|---|---|
| `bruteForce` (AG pairs) | O(N²) | O(1) |
| `optimal` (AG pairs) | O(n) | O(1) |
| `numberOfAGPairsInAString` | O(n) | O(1) |

## d04_Arrays_SubArraysWithCarryForward

| Method | Time | Space |
|---|---|---|
| `bruteForce` (smallest subarray min+max) | O(N³) | O(1) |
| `optimal` (smallest subarray min+max) | O(N) | O(1) |
| `storeAllSubArraysInA2DMatrix` | O(N³) | O(1) |
| `maxProfitOnOnlyOneTransaction` | O(N) | O(1) |
| `printAllSubArrays` | O(N³) | O(1) |

## ContributionTechnique_4

| Method | Time | Space |
|---|---|---|
| `bruteForce` (sum all subarrays) | O(N³) | O(1) |
| `approach1` (prefix sum) | O(N^12) ⚠️ source typo — likely O(N²) | O(N) |
| `approach2` (carry forward) | O(N²) | O(1) |
| `approach3` (contribution formula) | O(N) | O(1) |
| `countSubArraysWithSumLessThanB` | O(N²) | O(1) |
| `findCountOfGoodSubArrays` | O(N²) | O(1) |

## SlidingWindowTechnique_5

| Method | Time | Space |
|---|---|---|
| `approach1` (brute) | O(N²) | O(1) |
| `optimal` (sliding window) | O(N) | O(1) |
| `checkIfSubArrayWithLengthBHasSumC` (private) | O(N) | O(1) |
| `indexOfSubArrayWithLeastAverage` (private) | O(N) | O(1) |
