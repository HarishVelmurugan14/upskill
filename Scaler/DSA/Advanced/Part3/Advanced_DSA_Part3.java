package DSA.Advanced.Part3;

@SuppressWarnings({"ExtractMethodRecommender", "DataFlowIssue", "SpellCheckingInspection", "AccessStaticViaInstance"})
public class Advanced_DSA_Part3 {
    public void implementations() {
        Searching();
        BackTracking();
        DoublyLinkedList();
    }

    private void Searching() {
        /* ------------------------- Day 33 - DSA : Combinatorics Basics & Prime Number ------------------------------*/
        d33_Maths_CombinatoricsAndPrimeNumber d33_maths_combinatoricsAndPrimeNumber = new d33_Maths_CombinatoricsAndPrimeNumber();

        d33_maths_combinatoricsAndPrimeNumber.allPrimes(40); // Q2


        /* ------------------------- Day 35 - DSA : Maths & 2 Pointers ------------------------------*/
        d35_Maths_2Pointers d35Maths2Pointers = new d35_Maths_2Pointers();

        d35Maths2Pointers.contiguousSubArrayWithGivenSum(null, 2); //Q4

        /* ------------------------- Day 38 - DSA : Linked List Sorting & Problems ------------------------------*/
        d39_LinkedList_SortingAndProblems d39_linkedList_sortingAndProblems = new d39_LinkedList_SortingAndProblems();

        d39_linkedList_sortingAndProblems.mergeSortLinkedList(null); // Q1 //LC148
        d39_linkedList_sortingAndProblems.mergeTwoSortedLinkedList(null, null); //Q2 // LC21
        d39_linkedList_sortingAndProblems.isPalindromeLinkedList(null); //Q3 //LC234
        d39_linkedList_sortingAndProblems.middleNodeOfALinkedList(null); //Q4 //LC876

        d39_linkedList_sortingAndProblems.swapPairs(null); //AQ1 //LC24
        d39_linkedList_sortingAndProblems.addTwoNumbersInReverseOrderAndReturnAsLinkedList(null, null); //AQ2 //LC02
        //AQ3

    }

    private void BackTracking() {

        /* ------------------------- Day 36 - DSA : Backtracking ------------------------------*/
        d36_Backtracking d36_backtracking = new d36_Backtracking();

        d36_backtracking.generatePermutations_yahnit(new int[]{1, 2, 3}); // LC 46 // Q1
        d36_backtracking.generatePermutations("", "abc");
        d36_backtracking.generatePermutations_listVersion(null, null);
        d36_backtracking.generateParenthesis(3); // LC 22 // Q2
        d36_backtracking.generateSubsets_yahnit(new int[]{1, 2, 3}); // LC 78 // Q2
        d36_backtracking.generateSubSequence("", "abc");
        d36_backtracking.generateSubSequence_listVersion(null, null, null);

        d36_backtracking.generateOneZeroSpecialPattern(3,0);  // AQ2
    }

    private void DoublyLinkedList(){
        /* ------------------------- Day 40 - DSA : Doubly Linked List ------------------------------*/
        d39_DoublyLinkedList d39_doublyLinkedList = new d39_DoublyLinkedList();

        d39_doublyLinkedList.lruCacheImplementation(); // Q2 // LC146
        d39_doublyLinkedList.isThereALoopInLinkedList(null);
        d39_doublyLinkedList.detectStartNodeOfTheLoop(null);
        d39_doublyLinkedList.detectStartNodeOfTheLoopAndBreak(null); // Q3 // LC142
    }

    private void Trees(){
        /* ------------------------- Day 41 - DSA : Morris Inorder Traversal & LCA ------------------------------*/
    }
}
