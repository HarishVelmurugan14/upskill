# Complexities — Part 1

> Only complexities from source `// Complexity` comments. Empty brackets `[  ]` → "not stated".

## d08_Arrays_OneDimensional

| Method | Time | Space |
|---|---|---|
| `bruteForce` (max sum subarray) | O(N³) | O(N) |
| `prefixSumApproach` | O(N²) | O(N) |
| `carryForwardApproach` | O(N³) | O(N) |
| `kadanesAlgorithm` | O(N) | O(1) |
| `kadanesAlgorithm_withEndPoints` | O(N) | O(1) |
| `rainWaterTrapped_brute` (private) | O(N²) | O(1) |
| `rainWaterTrapped_optimized` (private) | O(3N) | O(2N) |
| `beggarProfit_brute` (private) | O(N²) | O(N) |
| `beggarProfit_optimized` (private) | O(N) | O(2N) |
| `addOneToNumberProvidedAsArray` | O(2N) | O(3N) |

## d09_Arrays_TwoDimensional

| Method | Time | Space |
|---|---|---|
| `searchInARowWiseAndColumnWiseSortedMatrix` | not stated | not stated |

## d9_Arrays_2dMatrix

| Method | Time | Space |
|---|---|---|
| `bruteForce90Degree` | O(N×M) | O(1) |
| `printRowWiseSum` (private) | O(n×m) | O(1) |
| `printMainDiagonalElements` (private) | O(n) | O(1) |
| `printAntiDiagonalElements` (private) | O(n) | O(1) |
| `printAllAntiDiagonals` (private) | O(n×m) | O(1) |
| `matrixTransposeInPlace` (private) | O(n×m) | O(1) |

## d10_Arrays_InterviewProblems

| Method | Time | Space |
|---|---|---|
| `bruteForce_MissingNaturalNumber` | O(N²) | O(1) |
| `approach2_MissingNaturalNumber` | O(N+N) | O(1) |
| `approach3_MissingNaturalNumber` | O(N+N) | O(1) |
| `approach4_MissingNaturalNumber` | O(N+N+N) | O(1) |
| `mergeIntervalAndProvidePointsMerged` | O(N) | O(1) |
| `nextPermutation` | O(N) | O(1) |
| `insertANewIntervalAndMergeIfPossible` | O(N) | O(N) |

## d11_BitWiseManipulations1

| Method | Time | Space |
|---|---|---|
| `bruteForce` | not stated | not stated |
| `optimal` | not stated | not stated |
| `countNumberOfSetBits` | O(logN) | O(1) |

## d12_BitWiseManipulations2

| Method | Time | Space |
|---|---|---|
| `tripleTrouble_approach3` (private) | O(N) | O(N) |
| `tripleTrouble_approach4` (private) | O(N) | O(1) |
| `twoUniqueElements` | O(N) | O(1) |
| `numberOfSubArraysWithOr0` (private) | O(N) | O(1) |
| `sumOfOR_brute` (private) | O(N) | O(1) |
| `findMinXor_Optimal` | O(N) | O(1) |
| `sumAfterBitwiseOROperatorOnAllSubArraysOfAnArray` | O(27N) | O(1) |
| `strangeEqualityWithFormula_optimal` (private) | O(logN) | O(1) |
| `missingTwoNumbers_Brute` (private) | O(N² + 2N) → O(N²) | O(1) |
| `missingTwoNumbers_XOR` (private) | O(4N + 4) → O(N) | O(1) |

## d13_Recursions1

| Method | Time | Space |
|---|---|---|
| `sumOfNNaturalNumbers` (private) | O(N) | O(1) |
| `factorial` | O(N) | O(N) |
| `printAllNumbersInIncreasingOrder` | O(N) | O(N) |
| `printAllNumbersInDecreasingOrder` | O(N) | O(N) |
| `printNthFibonacciNumber` | O(Refer Notes) | O(Refer Notes) |

## d14_Recursions2

| Method | Time | Space |
|---|---|---|
| `towerOfHanoi` (private) | O(2^N) | O(N) |
| `powerOfANumber_brute` (private) | O(N) | O(N) |
| `powerOfANumber_approach2` (private) | O(logN) | O(logN) |
| `printArrayUsingRecursion` | O(N) | O(N) |
| `allIndicesOfATarget_Harish` (private) | O(N) | O(N) |
| `allIndicesOfATarget_Yahnit` | O(N/2) | O(N/2) |

## d15_ModularArithmeticAndGCD

> No `// Complexity` comments found in source.

## d16_Hashing1_Introduction

> No `// Complexity` comments found in source.

## d17_Hashing2_Problems

| Method | Time | Space |
|---|---|---|
| `findThePairWithSumAsK` | not stated | not stated |
| `countThePairWithSumAsK` | not stated | not stated |
| `distinctNumbersInWindow_approach` (private) | O(N) | O(1) |
| `distinctNumbersInWindow_brute` (private) | O(N²) | O(N) |
| `subArrayWithSumAsZero_prefixSum` (private) | O(N) | O(1) |
| `subArrayWithSumAsZero_carryForward` (private) | O(N) | O(1) |
| `firstNonRepeatingElementInAnArray` | O(2N) | O(N) |

## d18_Sort_CountMergeSort

| Method | Time | Space |
|---|---|---|
| `mergeSort` (private) | O(NlogN) | O(N) |
| `mergeContiguousSortedSubArrays` (private) | O(high−low+1) | O(high−low+1) |
| `mergeTwoSortedArrays` (private) | O(N+M) | O(N+M) |
| `findTheSmallestNumberByRearrangingTheDigits` | O(N) | O(1) |

## d19_Sort_QuickSortAndComparator

| Method | Time | Space |
|---|---|---|
| `quickSort` | not stated | not stated |
| `pivotPartition` | not stated | not stated |
| `sortByColor` | not stated | not stated |
