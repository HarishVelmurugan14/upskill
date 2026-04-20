package DSA.Advanced.Part2;


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
        // AQ2
        d21SearchingBinarySearchOnArray.maximumHeightOfAStaircase(20); // AQ3


        /* ------------------------- Day 22 - DSA: Searching 2: Binary Search Problems ------------------------------*/
        d22_Searching2_BinarySearchProblems d22Searching2BinarySearchProblems = new d22_Searching2_BinarySearchProblems();

        d22Searching2BinarySearchProblems.squareRootOfANumber(4); // Q1 // LC69
        d22Searching2BinarySearchProblems.rotatedSortedArraySearch(); // Q2
        d22Searching2BinarySearchProblems.findMedianInSortedArrays(new int[]{}, new int[]{}); // Q3

        // AQ1
        // AQ2
        // AQ3
        // AQ4

        d22Searching2BinarySearchProblems.rotationFactor(new int[]{});

        /* ------------------------- Day 23 - DSA: Searching 3: Binary Search on Answer ------------------------------*/

        d23_Searching_BinarySearchOnAnswerSpace d23SearchingBinarySearchOnAnswerSpace = new d23_Searching_BinarySearchOnAnswerSpace();

        d23SearchingBinarySearchOnAnswerSpace.paintersPartitionMinimumTimeToPaint(3, 2, null); //Q1
        d23SearchingBinarySearchOnAnswerSpace.aggressiveCowsLargestMinDistance(null, 4); //Q2

        // AQ1
        // AQ2
    }

    private void LinkedList() {
        /* ------------------------- Day 24 - DSA: Classes, Objects & Linked Lists -----------------------------------*/


        /* ------------------------- Day 25 - DSA: Linked List Problems ----------------------------------------------*/

        d25_LinkedList_Problems d25_linkedList_problems = new d25_LinkedList_Problems();

        d25_linkedList_problems.printALinkedList(null); // Prev Q1
        // Q1
        // Q2
        d25_linkedList_problems.insertAtPosition(null, 3, 7); // Q3
        d25_linkedList_problems.deleteAtPosition(null, 5); // Q4

        d25_linkedList_problems.removeElements(null, 7); // LC203

    }

    private void Stacks() {
        /* ------------------------- Day 26 - DSA: Stacks 1 : Implementations & Basic Problems -----------------------*/

        d26_Stacks1_BasicProblems d26_Stacks1_BasicProblems = new d26_Stacks1_BasicProblems();

        d26_Stacks1_BasicProblems.postFixExpression(null); // Q1
        d26_Stacks1_BasicProblems.balancedParenthesis("{()()()}({"); // Q2
        d26_Stacks1_BasicProblems.doubleCharacterTrouble("abccbc"); // Q3
        d26_Stacks1_BasicProblems.passingGameOfBall(10, 48, new int[]{4, 0, 30, 0, 41, 28, 50, 2, 47, 39}); // Q4

        // AQ1
        d26_Stacks1_BasicProblems.redundantBracesUnecessaryBraces("(a+(a+b))"); // AQ2
        // AQ3
        // AQ4

        d26_Stacks1_BasicProblems.minAddToMakeParanthesisValid("((("); //LC921

        d27_Stack2_NearestSmallGreatElements d27_Stack2_NearestSmallGreatElements = new d27_Stack2_NearestSmallGreatElements();

        d27_Stack2_NearestSmallGreatElements.largestRectangleInAHistogram(null); // Q1 // LC84
        d27_Stack2_NearestSmallGreatElements.prevSmallerValuesInAnArray(null); // Q2
        d27_Stack2_NearestSmallGreatElements.identifyMaximumMinusMinimumInAllPossibleSubArrays(new int[]{4, 7, 3, 8}); // Q3

        d27_Stack2_NearestSmallGreatElements.nextGreaterValues(null); // AQ2

        d27_Stack2_NearestSmallGreatElements.nextGreaterElementForSubsetQueriesInADistinctArrays
                (new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}); // LC496

//        Dequeue
    }

    private void Queues() {
        /* -------------------------  Day 28 - DSA:Queues : Implementations & Problems -------------------------------*/

        d28_Queue_ImplementationAndProblems d28_queue_implementationAndProblems = new d28_Queue_ImplementationAndProblems();

        d28_queue_implementationAndProblems.implementQueuesUsingStack(); // Q1
        d28_queue_implementationAndProblems.parkingIceCreamTruck(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3); // Q2
        d28_queue_implementationAndProblems.uniqueLetterInGrowthOfAString("ababdc"); // AQ2 // Similar to LC1429

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
