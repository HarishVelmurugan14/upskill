package DSA.Advanced.Part2;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings({"ExtractMethodRecommender", "DataFlowIssue", "SpellCheckingInspection", "AccessStaticViaInstance"})
public class Advanced_DSA_Part2 {
    public void implementations() {
        Searching();
        LinkedList();
        Stacks();
        Queues();
        Trees();
    }

    private void Searching() {
        /* ------------------------- Day 21 - DSA : Searching 1: Binary Search on Array ------------------------------*/
        d21_Searching_BinarySearchOnArray d21SearchingBinarySearchOnArray = new d21_Searching_BinarySearchOnArray();

        d21SearchingBinarySearchOnArray.searchForStartAndEndIndexOfAnElementInAnArray(); // Q1
        d21SearchingBinarySearchOnArray.positionWhereTargetIsPresentOrShouldBeInserted(); // Q2
        d21SearchingBinarySearchOnArray.singleElementInASortedArray(); // Q3
        d21SearchingBinarySearchOnArray.peakElement(); // Q4

        d21SearchingBinarySearchOnArray.searhAMatrix(); // AQ1
        d21SearchingBinarySearchOnArray.minimumCostToBuildAnArray(3, 2, new int[][]{{7, 3}, {2, 1}, {4, 9}}); // AQ2
        d21SearchingBinarySearchOnArray.maximumHeightOfAStaircase(20); // AQ3


        /* ------------------------- Day 22 - DSA: Searching 2: Binary Search Problems ------------------------------*/
        d22_Searching2_BinarySearchProblems d22Searching2BinarySearchProblems = new d22_Searching2_BinarySearchProblems();

        d22Searching2BinarySearchProblems.squareRootOfANumber(4); // Q1 // LC69
        d22Searching2BinarySearchProblems.rotatedSortedArraySearch(); // Q2
        d22Searching2BinarySearchProblems.rotatedSortedArraySearch_myversion(new int[]{4, 5, 6, 7, 0, 1, 2}, 0); // Q2 alt
        d22Searching2BinarySearchProblems.findMedianInSortedArrays(new int[]{1, 2, 7, 9, 11, 13}, new int[]{1, 3, 4, 5, 8, 10}); // Q3

        d22Searching2BinarySearchProblems.findMatrixMedian(null); // AQ1
        d22Searching2BinarySearchProblems.addOrNot(null, 0); // AQ2
        d22Searching2BinarySearchProblems.athMagicalNumber(0, 0, 0); // AQ3
        d22Searching2BinarySearchProblems.BthSmallestTripletSum(null, 0); // AQ4

        d22Searching2BinarySearchProblems.rotationFactor(new int[]{9, 10, 3, 5, 6, 8}); // utility

        /* ------------------------- Day 23 - DSA: Searching 3: Binary Search on Answer ------------------------------*/

        d23_Searching_BinarySearchOnAnswerSpace d23SearchingBinarySearchOnAnswerSpace = new d23_Searching_BinarySearchOnAnswerSpace();

        d23SearchingBinarySearchOnAnswerSpace.paintersPartitionMinimumTimeToPaint(3, 2, new int[]{3, 5, 1, 7, 8, 2, 5, 3}); // Q1
        d23SearchingBinarySearchOnAnswerSpace.aggressiveCowsLargestMinDistance(new int[]{2, 6, 11, 14, 19, 25, 30, 39, 43}, 4); // Q2

        d23SearchingBinarySearchOnAnswerSpace.minimumDifferenceBetweenBooksAllotted(new int[]{12, 34, 67, 90}, 2); // AQ1
        d23SearchingBinarySearchOnAnswerSpace.maxSubArrayFactorLessThanB(new int[]{5, 17, 100, 11}, 130); // AQ2
    }

    private void LinkedList() {
        /* ------------------------- Day 24 - DSA: Classes, Objects & Linked Lists -----------------------------------*/

        d24_ClassesObjects_LinkedLists d24_classesObjects_linkedLists = new d24_ClassesObjects_LinkedLists();

        ListNode list = d24_classesObjects_linkedLists.createList(new int[]{3, 5, 1, 7, 8, 2, 5, 3});
        d24_ClassesObjects_LinkedLists.printList(list);
        d24_ClassesObjects_LinkedLists.insertAtLast(list);
        d24_ClassesObjects_LinkedLists.insertAtIndex(list, 3, 100); // insertAtIndex
        d24_ClassesObjects_LinkedLists.deleteAtIndex(list, 1);      // deleteAtIndex

        /* ------------------------- Day 25 - DSA: Linked List Problems ----------------------------------------------*/

        d25_LinkedList_Problems d25_linkedList_problems = new d25_LinkedList_Problems();

        ListNode head = d25_linkedList_problems.insertAtHead(null, 6);
        head = d25_linkedList_problems.insertAtHead(head, 3);
        head = d25_linkedList_problems.insertAtHead(head, 3);
        head = d25_linkedList_problems.insertAtHead(head, 6);

        d25_linkedList_problems.printALinkedList(head);

        d25_linkedList_problems.reverseALinkedListInPlaceInOneIteration(head); // Q1 // LC206
        d25_linkedList_problems.copyRandomList(null);                          // Q2 // LC138
        d25_linkedList_problems.insertAtPosition(head, 3, 5);                  // Q3
        d25_linkedList_problems.deleteAtPosition(head, 5);                     // Q4

        d25_linkedList_problems.removeElements(head, 7);    // LC203
        d25_linkedList_problems.deleteNode(head);            // LC237
        d25_linkedList_problems.deleteDuplicates(head);      // AQ1 // LC83
        d25_linkedList_problems.removeNthFromEnd(head, 4);   // AQ2 // LC19
        d25_linkedList_problems.reverseBetween(head, 2, 4);  // AQ3 // LC92
        d25_linkedList_problems.reverseKGroup(head, 2);      // AQ4 // LC25

    }

    private void Stacks() {
        /* ------------------------- Day 26 - DSA: Stacks 1 : Implementations & Basic Problems -----------------------*/

        d26_Stacks1_BasicProblems d26_Stacks1_BasicProblems = new d26_Stacks1_BasicProblems();

        d26_Stacks1_BasicProblems.postFixExpression(new ArrayList<>(Arrays.asList("2", "1", "+", "3", "*"))); // Q1 // LC150
        d26_Stacks1_BasicProblems.balancedParenthesis("{()()()}({");                                           // Q2 // LC20
        d26_Stacks1_BasicProblems.doubleCharacterTrouble("abccbc");                                            // Q3 // LC1047
        d26_Stacks1_BasicProblems.passingGameOfBall(10, 48, new int[]{4, 0, 30, 0, 41, 28, 50, 2, 47, 39}); // Q4 // ~LC1472

        d26_Stacks1_BasicProblems.minStackInit();                                      // AQ1 // LC155
        d26_Stacks1_BasicProblems.redundantBracesUnecessaryBraces("(a+(a+b))");       // AQ2
        d26_Stacks1_BasicProblems.checkTwoBracketExpressions("-(a+b+c)", "-a-b-c"); // AQ3
        d26_Stacks1_BasicProblems.infixToPostfix("a+b*(c^d-e)^(f+g*h)-i");          // AQ4

        d26_Stacks1_BasicProblems.minAddToMakeParanthesisValid("((("); // LC921

        d27_Stack2_NearestSmallGreatElements d27_Stack2_NearestSmallGreatElements = new d27_Stack2_NearestSmallGreatElements();

        int[] hist = {8, 2, 4, 9, 7, 5, 3, 10};
        d27_Stack2_NearestSmallGreatElements.nearestSmallerElementsToTheLeft(hist);  // util
        d27_Stack2_NearestSmallGreatElements.nearestSmallerElementsToTheRight(hist); // util
        d27_Stack2_NearestSmallGreatElements.nearestGreaterElementsToTheRight(hist); // util

        d27_Stack2_NearestSmallGreatElements.largestRectangleInAHistogram(new int[]{2, 1, 5, 6, 2, 3});       // Q1 // LC84
        d27_Stack2_NearestSmallGreatElements.prevSmallerValuesInAnArray(new int[]{4, 5, 2, 10, 8});            // Q2 // ~LC901
        d27_Stack2_NearestSmallGreatElements.identifyMaximumMinusMinimumInAllPossibleSubArrays(new int[]{1, 3, 3}); // Q3

        d27_Stack2_NearestSmallGreatElements.nextGreaterValues(new int[]{4, 5, 2, 10, 8});                    // AQ2 // LC739 LC503

        d27_Stack2_NearestSmallGreatElements.nextGreaterElementForSubsetQueriesInADistinctArrays
                (new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}); // LC496

        d27_Stack2_NearestSmallGreatElements.sortStackUsingAnotherStack(); // AQ3
    }

    private void Queues() {
        /* -------------------------  Day 28 - DSA:Queues : Implementations & Problems -------------------------------*/

        d28_Queue_ImplementationAndProblems d28_queue_implementationAndProblems = new d28_Queue_ImplementationAndProblems();

        d28_queue_implementationAndProblems.implementQueuesUsingStack(); // Q1
        d28_queue_implementationAndProblems.parkingIceCreamTruck(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3); // Q2
        d28_queue_implementationAndProblems.nIntegersContaining123(8); // AQ1
        d28_queue_implementationAndProblems.uniqueLetterInGrowthOfAString("ababdc"); // AQ2 // Similar to LC1429
        d28_queue_implementationAndProblems.sumOfMinAndMax(new int[]{2, 1, 5, 3}, 2); // AQ3 // LC1438

        d28_queue_implementationAndProblems.maximumInAFixedSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3); // LC239
        d28_queue_implementationAndProblems.firstUniqueCharacterInAString("leetcode"); // LC387

    }

    private void Trees() {
        /* -------------------------  Day 29 - DSA:Trees 1: Structure & Traversal ------------------------------------*/

        d29_Trees1_StructureAndTraversal d29_trees1_structureAndTraversal = new d29_Trees1_StructureAndTraversal();

        d29_trees1_structureAndTraversal.inorderTraversal(null); // Q1 // LC94
        d29_trees1_structureAndTraversal.preOrderTraversal(null); // Q2 // LC144
        d29_trees1_structureAndTraversal.hasPathSum(null, 22, "Pre"); // Q3 // LC112
        d29_trees1_structureAndTraversal.equalTreePartition(null); // Q4 //LC663 Premium

        d29_trees1_structureAndTraversal.postOrderTraversal(null); //AQ1 // LC145
        d29_trees1_structureAndTraversal.sumBinaryTreeOrNotMain(null); // AQ2
        d29_trees1_structureAndTraversal.getSize(null, 0);

        /* -------------------------  Day 30 - DSA:Trees 2: Views & Types --------------------------------------------*/

        d30_Trees2_ViewsAndTypes d30_trees2_viewsAndTypes = new d30_Trees2_ViewsAndTypes();

        d30_trees2_viewsAndTypes.levelOrderTraversal(null); // Q1 // LC102
        d30_trees2_viewsAndTypes.buildTreeWithInOrderAndPostOrder(null, null); // Q2
        d30_trees2_viewsAndTypes.isBalanced(null); // Q3 // LC110
        d30_trees2_viewsAndTypes.leftViewOfABinaryTree(null); // Q4

        d30_trees2_viewsAndTypes.buildTreeWithInOrderAndPreOrder(null, null); // AQ1 //LC105

        /* -------------------------  Day 31 - DSA:Trees 3: BST --------------------------------------------*/

        d31_Trees3_BST d31_trees3_bst = new d31_Trees3_BST();

        d31_trees3_bst.isValidBST(null); // Q1 // LC98
        d31_trees3_bst.sortedArrayToBST(new int[]{1,3,5,6,7,8}); // Q2 // LC108
        d31_trees3_bst.searchInABST(null, 3); // Q4 // LC700

        d31_trees3_bst.maxDepth(null); //LC104

    }
}
