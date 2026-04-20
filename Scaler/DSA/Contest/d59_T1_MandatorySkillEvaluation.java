package DSA.Contest;

import java.util.LinkedList;
import java.util.Queue;

public class d59_T1_MandatorySkillEvaluation {

    public static void main(String[] args) {
        callStaircase();
        callZeroes();
        paintersPartition();
        nearestDistanceInAMatrix();
    }
    public static void callStaircase(){
        int[] A = {10, 15, 20, 10, 5, 15}; // cost per stair
        int B = 3; // must step stair

        int result = minCostWithMustStep(A, B);
        System.out.println("Minimum cost to reach top with must-step B: " + result);
    }

    public static int minCostWithMustStep(int[] A, int B) {
        int N = A.length;

        // dp1[i]: min cost to reach stair i starting from 0 or 1
        int[] dp1 = new int[N];
        // Initialize with large value
        for (int i = 0; i < N; i++) dp1[i] = Integer.MAX_VALUE;

        // Starting options
        dp1[0] = A[0];
        if (N > 1) dp1[1] = A[1];

        // Fill dp1 up to B
        for (int i = 2; i <= B; i++) {
            dp1[i] = A[i] + Math.min(dp1[i - 1], dp1[i - 2]);
        }

        // If B is unreachable from start, no valid path
        if (dp1[B] == Integer.MAX_VALUE) return -1;

        // dp2[i]: min cost to reach stair i starting from B
        int[] dp2 = new int[N + 1];  // N is top (beyond last stair)
        for (int i = 0; i <= N; i++) dp2[i] = Integer.MAX_VALUE;

        dp2[B] = 0; // cost of starting at B in this segment is zero (already paid in dp1)

        for (int i = B + 1; i <= N; i++) {
            int cost = (i == N) ? 0 : A[i]; // cost 0 at top stair N
            int c1 = (i - 1 >= B) ? dp2[i - 1] : Integer.MAX_VALUE;
            int c2 = (i - 2 >= B) ? dp2[i - 2] : Integer.MAX_VALUE;
            dp2[i] = cost + Math.min(c1, c2);
        }

        return dp1[B] + dp2[N];
    }

    public static void callZeroes(){
        int[] A = {1, 0, 2, 0, 3, 0, 4};
        moveZerosToEnd(A);

        // Print result
        for (int num : A) {
            System.out.print(num + " ");
        }
    }

    public static void moveZerosToEnd(int[] A) {
        int index = 0; // position to place the next non-zero element

        // Move all non-zero elements forward
        for (int num : A) {
            if (num != 0) {
                A[index++] = num;
            }
        }

        // Fill the rest with zeros
        while (index < A.length) {
            A[index++] = 0;
        }
    }


    static final int MOD = 10000003;

    public static int paint(int A, int B, int[] C) {
        long low = 0, high = 0;
        for (int val : C) {
            high += val;
            low = Math.max(low, val);
        }

        long result = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (canPaint(C, A, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return (int) ((result % MOD) * (B % MOD) % MOD);
    }

    private static boolean canPaint(int[] boards, int painters, long maxWork) {
        int requiredPainters = 1;
        long currentSum = 0;
        for (int len : boards) {
            if (len > maxWork) return false;
            if (currentSum + len > maxWork) {
                requiredPainters++;
                currentSum = len;
                if (requiredPainters > painters) return false;
            } else {
                currentSum += len;
            }
        }
        return true;
    }

    public static void paintersPartition() {
        int A = 3;
        int B = 10;
        int[] C = {185, 186, 558, 655, 461, 441, 234, 902, 681};

        System.out.println(paint(A, B, C)); // Outputs: 18670
    }


    public static int[][] updateMatrix(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] result = new int[n][m];

        // Queue to hold all positions with 1s
        Queue<int[]> queue = new LinkedList<>();

        // Initialize result matrix and queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) {
                    result[i][j] = 0;        // Distance to nearest 1 is 0
                    queue.add(new int[]{i, j});
                } else {
                    result[i][j] = -1;       // Mark as unvisited
                }
            }
        }

        // 4 directions (up, down, left, right)
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // BFS from all 1s simultaneously
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                // Check bounds and unvisited
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && result[nx][ny] == -1) {
                    result[nx][ny] = result[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return result;
    }

    // Utility to print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }
    }

    public static void nearestDistanceInAMatrix() {
        int[][] A = {
                {0, 0, 1},
                {0, 0, 0},
                {1, 0, 0}
        };

        int[][] result = updateMatrix(A);
        printMatrix(result);
    }
}
