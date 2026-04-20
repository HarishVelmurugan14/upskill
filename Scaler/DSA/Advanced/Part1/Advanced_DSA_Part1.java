package DSA.Advanced.Part1;

import java.util.ArrayList;

@SuppressWarnings({"DataFlowIssue", "ExtractMethodRecommender"})
public class Advanced_DSA_Part1 {
    public void implementations() {
        Arrays();
        BitManipulations();
        Recursions();
        ModularArithmeticAndGCD();
        Hashing();
    }

    private void Arrays() {

        /* ------------------------- Day 08 - DSA: Arrays 1 : 1Dimensional Array -------------------------------------*/
        d08_Arrays_OneDimensional d08_arrays_oneDimensional = new d08_Arrays_OneDimensional();

        d08_arrays_oneDimensional.maximumSumContiguosSubArray(new int[]{1, 1}); //Q1 //LC53
        d08_arrays_oneDimensional.profitOfBeggarsSittingContiguoslyGettingRandomDonation(); // Q2 // LC370
        d08_arrays_oneDimensional.rainWaterTrappedBetween2Buildings(); // Q3 //LC42

        d08_arrays_oneDimensional.addOneToNumberProvidedAsArray(); // AQ1 // LC66
        // AQ2

        /* ------------------------- Day 09 - DSA: Arrays 2 : 2Dimensional Array -------------------------------------*/
        d09_Arrays_TwoDimensional d09_Arrays_TwoDimensional = new d09_Arrays_TwoDimensional();

        d09_Arrays_TwoDimensional.generateSquareMatrixInSpiralOrder(4); // Q1
        d09_Arrays_TwoDimensional.searchInARowWiseAndColumnWiseSortedMatrix(); // Q2
        d09_Arrays_TwoDimensional.sumOfAllPossibleSubMatrices(new int[][]{}); // Q3

        // AQ1
        d09_Arrays_TwoDimensional.rowWithMaximumNumberOfOnes(null); // AQ2 //LC1428

        /* ------------------------- Day 10 - DSA: Arrays 3 : Interview Problems -------------------------------------*/
        d10_Arrays_InterviewProblems d10_Arrays_InterviewProblems = new d10_Arrays_InterviewProblems();

        d10_Arrays_InterviewProblems.firstMissingNaturalNumber(); // Q1
        d10_Arrays_InterviewProblems.mergeIntervalAndProvidePointsMerged(); // Q2

        d10_Arrays_InterviewProblems.insertANewIntervalAndMergeIfPossible(); // AQ1
        d10_Arrays_InterviewProblems.nextPermutation();
    }

    private void BitManipulations() {

        /* ------------------------- Day 11 - DSA: Bit Manipulation 1 ------------------------------------------------*/
        d11_BitWiseManipulations1 d11_BitWiseManipulations1 = new d11_BitWiseManipulations1();

        d11_BitWiseManipulations1.setTheIthBitInNumberN(5, 0);
        d11_BitWiseManipulations1.unSetTheIthBitInNumberN(5, 0);
        d11_BitWiseManipulations1.toggleBitIInNumberN(5, 0);
        d11_BitWiseManipulations1.checkIfIthBitIsSetOrUnset(5, 0);
        d11_BitWiseManipulations1.countNumberOfSetBits(5);
        d11_BitWiseManipulations1.arrayContainingOnlyOneDistintNumber(new int[]{});

        d11_BitWiseManipulations1.findNthMagicNumber(5); // AQ1
        d11_BitWiseManipulations1.helpFromSam(5); // AQ2
        d11_BitWiseManipulations1.findingGoodDaysForAlexCat(); // AQ3

        /* ------------------------- Day 12 - DSA: Bit Manipulation 2 ------------------------------------------------*/
        d12_BitWiseManipulations2 d12_BitWiseManipulations2 = new d12_BitWiseManipulations2();

        d12_BitWiseManipulations2.tripleTrouble(); // Q1
        d12_BitWiseManipulations2.twoUniqueElements(); // Q2
        d12_BitWiseManipulations2.numberOfSubArraysWithOr1(null); // Q3

        d12_BitWiseManipulations2.findMinXor(); // AQ1
        d12_BitWiseManipulations2.strangeEqualityWithFormula(5); // AQ2
        d12_BitWiseManipulations2.sumAfterBitwiseOROperatorOnAllSubArraysOfAnArray(); // AQ3
        d12_BitWiseManipulations2.missingTwoNumbersInRangeNPlus2(); // AQ4
    }

    private void Recursions() {

        /* ------------------------- Day 13 - DSA: Recursion 1 -------------------------------------------------------*/
        d13_Recursions1 d13_recursions1 = new d13_Recursions1();

        // Q1 - One Word
        d13_recursions1.factorial(5); // Q2
        d13_recursions1.printNthFibonacciNumber(3); // Q3
        d13_recursions1.printAllNumbersInIncreasingOrder(3); // Q4
        d13_recursions1.printAllNumbersInDecreasingOrder(3); // Q5

        // AQ1 - One Word
        d13_recursions1.findSumOfDigitsOfANumber(2); // AQ2
        d13_recursions1.printNNumbersFirstInDescendingThenInAscendingOrder(5); // AQ3

        d13_recursions1.skipACharacter("", "baaccvgdaa", 'a');
        d13_recursions1.skipAString( "baaccvgdapplea", "apple");

        /* ------------------------- Day 14 - DSA: Recursion 2 -------------------------------------------------------*/
        d14_Recursions2 d14_recursions2 = new d14_Recursions2();

        d14_recursions2.checkForPalindrome(null, 0, 0); // Q1
        d14_recursions2.towerOfHanoi_list(4, 1, 2, 3, new ArrayList<>()); // Q2
        d14_recursions2.allIndicesOfATargetInAnArray_Yahnit(null, 0, 3); // Q3
        d14_recursions2.printArrayUsingRecursion(null, 0); // Q4
        d14_recursions2.powerOfANumberUsingRecursion(); // Q5

        d14_recursions2.isMagicNumber(4); // AQ1
        // AQ2
        // AQ3
        // AQ4
    }

    private void ModularArithmeticAndGCD(){

        /* ------------------------- Day 15 - DSA: Modular Arithmetic And GCD ----------------------------------------*/
        d15_ModularArithmeticAndGCD d15_ModularArithmeticAndGCD = new d15_ModularArithmeticAndGCD();

        d15_ModularArithmeticAndGCD.power(-1, 1, 20); // Q1
        d15_ModularArithmeticAndGCD.gcd(130, 50); // Q2
        d15_ModularArithmeticAndGCD.pairSumDivisibleByM_optimal(); // Q3

        // AQ1
        // AQ2
        // AQ3
        // AQ4
        // AQ5

    }

    private void Hashing() {

        /* ------------------------- Day 16 - DSA: Hashing 1 ---------------------------------------------------------*/
        d16_Hashing1_Introduction d16_Hashing1_Introduction = new d16_Hashing1_Introduction();

        d16_Hashing1_Introduction.frequencyCounter(new int[]{6, 3, 3, 6, 7, 8, 7, 3, 7}, new int[]{10, 9, 8}); // Q1
        d16_Hashing1_Introduction.distinctElements(); // Q2
        d16_Hashing1_Introduction.firstRepeatingElement(); // Q3
        d16_Hashing1_Introduction.subArraySumAsZero(); // Q4
        d16_Hashing1_Introduction.commonElements(); // Q5

        // AQ1
        // AQ2

        d16_Hashing1_Introduction.countSubArraySumAsZero(); // Ext

        /* ------------------------- Day 17 - DSA: Hashing 2 ---------------------------------------------------------*/
        d17_Hashing2_Problems d17_Hashing2_Problems = new d17_Hashing2_Problems();

        d17_Hashing2_Problems.findThePairWithSumAsK(); // Q1
        d17_Hashing2_Problems.countOfPairsWithDifferenceEqualsK(); // Q2
        d17_Hashing2_Problems.countOfSubArrayWithSumEqualsK(); // Q3
        d17_Hashing2_Problems.distinctNumbersInWindow(); // Q4
        d17_Hashing2_Problems.longestSubArrayWithSumAsZero(); // Q5

        d17_Hashing2_Problems.countThePairWithSumAsK(); // AQ1
        // AQ2

        d17_Hashing2_Problems.longestSubArrayWithSumAsK(); // Ext
        d17_Hashing2_Problems.firstNonRepeatingElementInAnArray(); // Ext
    }

    private void Sorting() {
        /* ------------------------------------------ Quick Sort & Comparator ----------------------------------------*/
        d19_Sort_QuickSortAndComparator sorting2QuickSortCmp = new d19_Sort_QuickSortAndComparator();

        sorting2QuickSortCmp.sortByColor();
        sorting2QuickSortCmp.quickSort(new int[]{}, 0, 10);
    }
}
