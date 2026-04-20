package DSA.Advanced.Part4;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Harish Velmurugan
 * @last-modified 10-06-2025
 * @since 10-06-2025
 */
public class d56_Graph_InterviewProblems {


    private final PrintHelper printHelper = new PrintHelper();
    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d56_Graph_InterviewProblems d56GraphInterviewProblems = new d56_Graph_InterviewProblems();
        int[][] A = new int[][]{{1, 1, 0, 1}, {0, 0, 0, 1}, {1, 0, 0, 1}, {0, 0, 1, 0}};
        d56GraphInterviewProblems.mazeMinimumDistance(A, new int[]{1, 1}, new int[]{2, 1});


    }

    public int mazeMinimumDistance(int[][] maze, int[] start, int[] dest) {
        int n = maze.length, m = maze[0].length;
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start[0], start[1]));

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int[] d : dirs) {
                int x = cur.x, y = cur.y, count = 0;
                while (isValid(n, m, x + d[0], y + d[1]) && maze[x + d[0]][y + d[1]] == 0) {
                    x += d[0];
                    y += d[1];
                    count++;
                }

                if (dist[cur.x][cur.y] + count < dist[x][y]) {
                    dist[x][y] = dist[cur.x][cur.y] + count;
                    queue.add(new Node(x, y));
                }
            }
        }

        return dist[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : dist[dest[0]][dest[1]];
    }

    public int numberOfIslandsPresent(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        boolean[][] visited = new boolean[n][m];
        int islands = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int current = A[i][j];
                if (current == 1 && !visited[i][j]) {
                    islands++;
                    visited[i][j] = true;
                    dfs(i, j, A, n, m, visited);
                }
            }
        }
        return islands;
    }

    public void dfs(int x, int y, int[][] A, int n, int m, boolean[][] visited) {
        for (int i = 0; i < 8; i++) {
            int xAxis = x + directions[i][0];
            int yAxis = y + directions[i][1];
            if (isValid(n, m, xAxis, yAxis) && !visited[xAxis][yAxis]) {
                visited[xAxis][yAxis] = true;
                if (A[xAxis][yAxis] == 1) {
                    dfs(xAxis, yAxis, A, n, m, visited);
                }
            }
        }
    }

    public boolean isValid(int n, int m, int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    public void bruteForce() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    public void optimal() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    private void print(String message) {
        printHelper.print(message, "");
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void definitions() {
        /**/
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void links() {
        /**/
    }

    class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
