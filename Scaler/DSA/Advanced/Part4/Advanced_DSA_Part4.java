package DSA.Advanced.Part4;

@SuppressWarnings({"AccessStaticViaInstance", "DataFlowIssue"})
public class Advanced_DSA_Part4 {
    public void implementations() {
        Heaps();
        DynamicProgramming();
        Graphs();
    }

    private void Heaps() {
        /* ------------------------- Day 44 - DSA : Heaps | Introduction ------------------------------*/
        d44_Heaps_Introduction d44HeapsIntroduction = new d44_Heaps_Introduction();
        d44HeapsIntroduction.connectingRopesPQ(new int[]{1, 2, 3, 4, 5}); //Q1 //LC1167
        d44HeapsIntroduction.connectingRopesManual(new int[]{1, 2, 3, 4, 5});
        d44HeapsIntroduction.buildMinHeap(null); //Q2
        d44HeapsIntroduction.solveQueries(null); //Q3

        /* ------------------------- Day 45 - DSA : Heaps | Greedy ------------------------------*/
        d45_Heaps_SortAndGreedy d45HeapsSortAndGreedy = new d45_Heaps_SortAndGreedy();
        d45HeapsSortAndGreedy.expectedDeliveryTime(null); //Q1
        d45HeapsSortAndGreedy.medianRunningArray(); //LC295
        d45HeapsSortAndGreedy.maxJobs(new int[]{1, 5, 7, 1}, new int[]{7, 8, 8, 8}); //Q2

        /* ------------------------- Day 46 - DSA : Heaps | Lab Sessions ------------------------------*/
        d46_Heaps_LabSessions d46HeapsLabSessions = new d46_Heaps_LabSessions();
        d46HeapsLabSessions.distributeCandys(null); //Q1 //LC135
        d46HeapsLabSessions.mergeKLists(null); //Q2 //LC23
        d46HeapsLabSessions.aThLargestElement(1, null); //Q3
        d46HeapsLabSessions.flipkartInventoryManagement(null, null); //Q4

        /* ------------------------- Day 46 - DSA : Heaps | Interview Problems ------------------------------*/
        d48_Heaps_InterviewProblems d48HeapsInterviewProblems = new d48_Heaps_InterviewProblems();
        d48HeapsInterviewProblems.shaggySpecialIndexMinimumDistance(null); //Q1
        d48HeapsInterviewProblems.containsNearbyDuplicate(null, 3); //LC219
        d48HeapsInterviewProblems.kPlacesApart(null, 3); //Q2
        d48HeapsInterviewProblems.minimumMeetingRoomsRequired(3, null); //Q3 //LC253
        d48HeapsInterviewProblems.minWindow("", ""); //Q4 //LC76
    }

    private void DynamicProgramming() {
        /* ------------------------- Day 49 - DSA : DP 1 | One Dimensional ------------------------------*/
        d49_DP_OneDimensional d49_DP_OneDimensional = new d49_DP_OneDimensional();

        d49_DP_OneDimensional.climbStairs_topDown(7);
        d49_DP_OneDimensional.climbStairs_bottomUp(7); //Q1 //Optimal //LC40
        d49_DP_OneDimensional.fibonacci_topdown(); //Q3 //LC509
        d49_DP_OneDimensional.minimumSquares(13); //Q2

        /* ------------------------- Day 50 - DSA : DP 2 | Two Dimensional ------------------------------*/
        d50_DP_TwoDimensional d50DpTwoDimensional = new d50_DP_TwoDimensional();

        d50DpTwoDimensional.uniquePathsWithObstacles(null); //Q1
        d50DpTwoDimensional.numBSTrees(3); //Q2 //LC96
        d50DpTwoDimensional.maxSum(null); //Q3
        d50DpTwoDimensional.nDigitNumbers(2, 2); //Q4

        /* ------------------------- Day 51 - DSA : DP 3 | Knapsack ------------------------------*/
        d51_DP_Knapsack d51DpKnapsack = new d51_DP_Knapsack();

        d51DpKnapsack.maxValueForKnapsack(null, null, 50); //Q1
        d51DpKnapsack.unboundedKnapsack(1, null, null); //Q2 //LC322
        d51DpKnapsack.fractionalKnapsack(null, null, 1); //Q3 // GEMINI CODE NOT PRACTISED
        d51DpKnapsack.sendingAlienSignal(5); //AQ2

        /* ------------------------- Day 52 - DSA : DP 4 | Knapsack Interview Problems ------------------------------*/
        d52_DP_KnapsackApplications d52DpKnapsackApplications = new d52_DP_KnapsackApplications();

        d52DpKnapsackApplications.maxProfitFromRodCutting(null); //Q1
        d52DpKnapsackApplications.coinchange2(null, 4); //Q2 //LC518
        d52DpKnapsackApplications.knapsack01BottomUp(null, null, 2); //Q3
    }

    private void Graphs() {
        /* ------------------------- Day 53 - DSA : Graphs 1 | Intro & Cycle Detetection ------------------------------*/
        d53_Graphs_Introduction d53GraphsIntroduction = new d53_Graphs_Introduction();

        d53GraphsIntroduction.cycleDetectionInAGraph(d53GraphsIntroduction.InputWithCycleNodes, d53GraphsIntroduction.InputWithCycle); //Q1
        d53GraphsIntroduction.pathInADirectedGraph(0, null); //LC 1971 //Q2

        d53GraphsIntroduction.firstDepthFirstSearch(new int[]{1, 1, 2}, 2, 1); //AQ1
        d53GraphsIntroduction.maximumDepth(5, new int[]{1, 4, 3, 1}, new int[]{5, 2, 4, 4}, new int[]{7, 38, 27, 37, 1}, new int[]{1, 1, 2}, new int[]{32, 18, 26}); //AQ2

        /* ------------------------- Day 54 - DSA : Graphs 2 | BFS & MST ------------------------------*/
        d54_Graphs_BST d54GraphsBst = new d54_Graphs_BST();

        d54GraphsBst.connectingBridges(3, null); //Q1 //LC1135
        d54GraphsBst.rottenOranges(null); //Q2 //LC994
        d54GraphsBst.minimumCostRoads(3, null); //Q3 //1584

        /* ------------------------- Day 55 - DSA : Graphs 3 | Dijkstra & TopSort ------------------------------*/
        d55_Graphs_Dijkstra_TopSort d55GraphsDijkstraTopSort = new d55_Graphs_Dijkstra_TopSort();

        d55GraphsDijkstraTopSort.courseScheduler(1, null, null); //Q1
        d55GraphsDijkstraTopSort.minimumDistanceForEachNodeUsingDijkstra(3, null, 4); //Q2 //LC743
        d55GraphsDijkstraTopSort.anotherBfs(1, null, 1, 1); //Q3
        d55GraphsDijkstraTopSort.topologicalSort(1, null); //Q4

        /* ------------------------- Day 56 - DSA : Graphs 4 | Interview Problems ------------------------------*/
        d56_Graph_InterviewProblems d56GraphInterviewProblems = new d56_Graph_InterviewProblems();

        d56GraphInterviewProblems.mazeMinimumDistance(null, new int[]{1, 1}, new int[]{2, 1}); //Q2 //LC490 //LC499 //LC505
        d56GraphInterviewProblems.numberOfIslandsPresent(null); //Q3

    }
}
