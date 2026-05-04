package DSA.Advanced.Part4;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Harish Velmurugan
 * @last-modified 20-05-2025
 * @since 20-05-2025
 */
public class d53_Graphs_Introduction {


    public static int InputWithCycleNodes = 4;
    public static int[][] InputWithCycle = {{1, 2}, {2, 3}, {3, 4}, {4, 2}};
    public static int InputWithoutCycleNodes = 4;
    public static int[][] InputWithoutCycle = {{1, 2}, {2, 3}, {3, 4}};
    private final PrintHelper printHelper = new PrintHelper();
    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */
    public int MaxLevel = 0;

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d53_Graphs_Introduction d53GraphsIntroduction = new d53_Graphs_Introduction();

//        System.out.println(d53GraphsIntroduction.cycleDetectionInAGraph(InputWithCycleNodes, InputWithCycle));
//        System.out.println(d53GraphsIntroduction.cycleDetectionInAGraph(InputWithoutCycleNodes, InputWithoutCycle));

        d53GraphsIntroduction.maximumDepth(5, new int[]{1, 4, 3, 1}, new int[]{5, 2, 4, 4}, new int[]{7, 38, 27, 37, 1}, new int[]{1, 1, 2}, new int[]{32, 18, 26});
    }

    public int[] maximumDepth(int A, int[] B, int[] C, int[] D, int[] E, int[] F) {
        Map<Integer, List<Integer>> adj = buildAdjacencyMatrixMaxDepth(A, B, C);
        boolean[] visited = new boolean[A + 1];
        Map<Integer, List<Integer>> levelList = new HashMap<>();

        dfs(1, adj, visited, 0, levelList);
//        for (int i = 0; i <= 5; i++) {
//            if (levelList.containsKey(i)) {
//                System.out.println("LEVEL ->" + i);
//                System.out.println(levelList.get(i).toString());
//            }
//        }
        int[] result = new int[E.length];
        for (int i = 0; i < E.length; i++) {
            int res = -1;
            int level = E[i] % (MaxLevel + 1);
            int threshold = F[i];
            for (int node : levelList.get(level)) {
                if (D[node] >= threshold) {
                    res = Math.max(res, D[node]);
                }
            }
            result[i] = res;
        }
        return result;
    }

    public void dfs(int node, Map<Integer, List<Integer>> adj, boolean[] visited, int level, Map<Integer, List<Integer>> levelList) {
        visited[node] = true;
        levelList.computeIfAbsent(level, x -> new ArrayList<>()).add(node);

        for (int neighbour : adj.get(node)) {
            if (!visited[neighbour]) {
                MaxLevel = Math.max(MaxLevel, level + 1);
                dfs(neighbour, adj, visited, level + 1, levelList);
            }
        }
    }

    public Map<Integer, List<Integer>> buildAdjacencyMatrixMaxDepth(int A, int[] B, int[] C) {

        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < A - 1; i++) {
            int u = B[i];
            int v = C[i];
            adj.computeIfAbsent(u, x -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, x -> new ArrayList<>()).add(u);
        }
        return adj;
    }

    public int firstDepthFirstSearch(int[] A, final int B, final int C) {
        // Complexity : Time : [ O(V) ]
        // Complexity : Space : [ O(V) ]
        List<List<Integer>> adj = buildAdjacencyMatrixTownsProblem(A);
        boolean[] visited = new boolean[A.length + 1];

        return dfsTownsProblem(C, adj, visited, B) ? 1 : 0;

    }

    public boolean dfsTownsProblem(int node, List<List<Integer>> adj, boolean[] visited, int destination) {
        if (node == destination) {
            return true;
        }
        visited[node] = true;

        for (int neighbour : adj.get(node)) {
            if (!visited[neighbour]) {
                if (dfsTownsProblem(neighbour, adj, visited, destination)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public List<List<Integer>> buildAdjacencyMatrixTownsProblem(int[] A) {
        List<List<Integer>> adj = new ArrayList<>();

//        NOTE : BEST WAY **
//        Map<Integer, List<Integer>> adj = new HashMap<>();
//        adj.computeIfAbsent(from, k -> new ArrayList<>()).add(to);

        for (int i = 0; i < A.length + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < A.length; i++) {
            int u = A[i];
            int v = i + 1;
            adj.get(u).add(v);
        }
        return adj;
    }


    public int pathInADirectedGraph(int A, int[][] B) {
        List<List<Integer>> adj = buildAdjacencyListForDirectedGraph(A, B);

        boolean[] visited = new boolean[A + 1];

        int sourceNode = 1;

        return dfsForPathInADirectedGraph(sourceNode, A, adj, visited);
    }

    public int dfsForPathInADirectedGraph(int node, int targetNode, List<List<Integer>> adj, boolean[] visited) {
        if (node == targetNode) {
            return 1;
        }
        visited[node] = true;

        for (int neighbour : adj.get(node)) {
            if (!visited[neighbour]) {
                // if directly returned other neighbours will not be visited
                if (dfsForPathInADirectedGraph(neighbour, targetNode, adj, visited) == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public List<List<Integer>> buildAdjacencyListForDirectedGraph(int numberOfNodes, int[][] connections) {
        // Complexity : Time : [ O(V+E) ]
        // Complexity : Space : [ O(V+E) ]
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= numberOfNodes; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < connections.length; i++) {
            int u = connections[i][0]; // source
            int v = connections[i][1]; // dest
            adj.get(u).add(v);
        }
        return adj;
    }

    public int cycleDetectionInAGraph(int A, int[][] B) {
//        Time Complexity: O(A + M) — for building the adjacency list and DFS traversal. M : Number of edges
//        Space Complexity: O(A + M) — for the adjacency list, visited arrays, and recursion stack.
//        in General O(V+E)

        List<List<Integer>> adj = buildAdjacencyListForDirectedGraph(A, B);

        // 1 based indexing; so added A+1
        boolean[] visited = new boolean[A + 1];
        boolean[] recStack = new boolean[A + 1];

        for (int i = 0; i <= A; i++) {
            if (!visited[i]) {
                if (dfsForCycleDetection(i, adj, visited, recStack)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean dfsForCycleDetection(int node, List<List<Integer>> adj, boolean[] visited, boolean[] recStack) {
        visited[node] = true;
        recStack[node] = true;

        for (int neighbour : adj.get(node)) {
            if (!visited[neighbour]) {
                if (dfsForCycleDetection(neighbour, adj, visited, recStack)) {
                    return true;
                }
            } else if (recStack[neighbour]) {
                return true; // parent node of the same stack is called again
            }
        }
        recStack[node] = false;
        return false;
    }

    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /**/
    }

    private void links() {
        /**/
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
