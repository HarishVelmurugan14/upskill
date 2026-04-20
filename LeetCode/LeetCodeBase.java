
import Topics.ArraysAndHashing;
import Topics.BackTracking;
import Topics.Graphs;
import Topics.Heaps;
import Topics.LinkedList;
import Topics.Stacks;
import Topics.Strings;
import Topics.Threads;

@SuppressWarnings("SpellCheckingInspection")
public class LeetCodeBase {

    public static void main(String[] args) {
        Strings();
        Stacks();
        ArraysAndHashing();
        LinkedList();
        BackTracking();
        Heaps();
        Threads();
    }

    private static void Strings() {
        Strings strings = new Strings();
        // ---------------------------------------- Easy -----------------------------------------
        strings.maxDifferenceBetweenOddFreqCharacterAndEvenFreqCharacter("abcabcab"); //LC3442
        strings.maxAdjacentDistanceInACircularArray(null); //LC3423

        // ---------------------------------------- Medium -----------------------------------------
        strings.lexicalOrder(1); //LC386
        strings.smallestEquivalentString("leetcode", "programs", "sourcecode"); //LC1061
        strings.robotWithString("bdda"); //LC2434

        // ---------------------------------------- Hard -----------------------------------------
        strings.maxDifferenceBetweenOddAndEvenfrequency("21312312", 2); //LC 3445

    }

    private static void Stacks() {
        Stacks stacks = new Stacks();
        // ---------------------------------------- Easy -----------------------------------------

        // ---------------------------------------- Medium -----------------------------------------
        stacks.robotWithString("bdda"); //LC2434

        // ---------------------------------------- Hard -----------------------------------------

    }

    @SuppressWarnings("DataFlowIssue")
    private static void ArraysAndHashing() {
        ArraysAndHashing arraysAndHashing = new ArraysAndHashing();

        // ---------------------------------------- Easy -----------------------------------------

        arraysAndHashing.twoSum(null, 7); // LC1
        arraysAndHashing.plusOneToAndIntegerArray(null); // LC66
        arraysAndHashing.containsDuplicate(null); // LC217
        arraysAndHashing.isAnagram("racecar", "carrace"); // LC242
        arraysAndHashing.rowWithMaximumNumberOfOnes(null); // LC1428 // Locked // Left column with least one

        // ---------------------------------------- Medium ---------------------------------------

        arraysAndHashing.containerWithMostWater(null); //LC11
        arraysAndHashing.combinationSum(null, 8); //LC39
        arraysAndHashing.groupAnagrams(null); // LC49
        arraysAndHashing.maxSumSubArray(null); // LC53
        arraysAndHashing.topKFrequentElements(null, 3); // LC347
        arraysAndHashing.profitOfBeggarsSittingContiguoslyGettingRandomDonation(1, null); // LC370 // Locked // Range Addition

        // ---------------------------------------- Hard -----------------------------------------

        arraysAndHashing.rainWaterTrapped(null); // LC42

    }

    private static void LinkedList() {
        LinkedList linkedList = new LinkedList();

        // ---------------------------------------- Easy -----------------------------------------
        linkedList.mergeTwoSortedLinkedList(null, null); // LC21
        linkedList.detectionOfCycles(null); // LC141
        linkedList.detectStartOfTheCycle(null); // LC142
        linkedList.isPalindrome(null); // LC234
        linkedList.middleNodeOfALinkedList(null); // LC876

        // ---------------------------------------- Medium -----------------------------------------
        linkedList.addTwoNumbersInReverseOrderAndReturnAsLinkedList(null, null); // LC02
        linkedList.swapPairs(null); // LC24
        linkedList.mergeSortLinkedList(null); // LC148

        // ---------------------------------------- Hard -----------------------------------------
    }

    private static void BackTracking() {
        BackTracking backTracking = new BackTracking();

        // ---------------------------------------- Easy -----------------------------------------


        // ---------------------------------------- Medium -----------------------------------------
        backTracking.generateParenthesis(3); // LC22
        backTracking.generatePermutations_old(new int[]{1, 2, 3}); // LC46
        backTracking.generateSubsets(new int[]{1, 2, 3}); // LC78
        backTracking.generateSubSequence("", "abc"); // LC78

        // ---------------------------------------- Hard -----------------------------------------
    }

    private static void Heaps() {
        Heaps heaps = new Heaps();

        // ---------------------------------------- Easy ------------------------------------------
        heaps.kThLargestElement(); //LC703
        heaps.containsNearbyDuplicate(null, 3); //LC219

        // ---------------------------------------- Medium -----------------------------------------
        heaps.minimumMeetingRoomsRequired(3, null); //LC253

        // ---------------------------------------- Hard -------------------------------------------
        heaps.mergeKLists(null); //LC23
        heaps.minWindowSubstring("", ""); //LC76
        heaps.distributeCandys(null); //LC135
        heaps.medianRunningArray(); //LC295
    }

    private static void Graphs() {
        Graphs graphs = new Graphs();

        // ---------------------------------------- Easy ------------------------------------------

        // ---------------------------------------- Medium -----------------------------------------
        graphs.numberOfIslandsPresent(null); //LC200
        graphs.mazeMinimumDistance(null, null, null); //LC490 //LC499 //LC505
        graphs.networkDelayTime(null, 1, 4); //LC743
        graphs.rottenOranges(null); //LC994
        graphs.connectingBridges(3, null); //LC1135
        graphs.minCostConnectPoints(null); //LC1584

        // ---------------------------------------- Hard -------------------------------------------

    }

    private static void Threads() {
        Threads threads = new Threads();
        // ---------------------------------------- Medium -----------------------------------------

        threads.ZeroEvenOdd(); // LC1116
        threads.H20(); // LC1117
    }

}

