package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 14-01-2025
 * @since 14-01-2025
 */
public class d9_Arrays_2dMatrix {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d9_Arrays_2dMatrix twoDMatrices = new d9_Arrays_2dMatrix();
//        int[][] matrixArray = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
//        int[][] matrixArray = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}, {17, 18, 19, 20}};
        int[][] matrixArray = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int n = matrixArray.length;
        int m = matrixArray[0].length;

        twoDMatrices.printRowWiseSum(matrixArray);
        twoDMatrices.printMainDiagonalElementsForSquareMatrix(matrixArray);
        twoDMatrices.printAntiDiagonalElementsForSquareMatrix(matrixArray);
        twoDMatrices.printAllAntiDiagonalElementsOfAMatrix(matrixArray);
        twoDMatrices.matrixTranspose(matrixArray, n, m);
        twoDMatrices.matrixTransposeInPlace(matrixArray, n, m);
        twoDMatrices.rotateMatrix90Degree(matrixArray);
    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void bruteForce90Degree(int[][] matrix) {
        // Complexity : Time : [ O(N*M) ]
        // Complexity : Space : [ O(1) ]

        int n = matrix.length;
        int m = matrix[0].length;
        matrixTransposeInPlace(matrix, n, m);
        // New Matrix
        print("", reverse(matrix, n, m));
        print("", reverseInPlace(matrix, n, m));


    }
    /* Section : ------------------------------- [ Problems ] ----------------------------------------- */

    private void printRowWiseSum(int[][] matrix) {
        // Complexity : Time : [ O(n*m) ]
        // Complexity : Space : [ O(1) ]
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                System.out.println(i + "," + j + " is " + matrix[i][j]);
                sum = sum + matrix[i][j];
            }
            System.out.println("ROW : " + i + " : SUM : " + sum);
        }
    }

    private void printMainDiagonalElementsForSquareMatrix(int[][] matrix) {
        // Complexity : Time : [ O(n) ]
        // Complexity : Space : [ O(1) ]
        int n = matrix.length;
        // square matrix has both n and m as same
        for (int i = 0; i < n; i++) {
            System.out.println(i + "," + i + " is " + matrix[i][i]);
        }
    }

    private void printAntiDiagonalElementsForSquareMatrix(int[][] matrix) {
        // Complexity : Time : [ O(n) ]
        // Complexity : Space : [ O(1) ]
        int n = matrix.length;
        int m = matrix[0].length;
        // square matrix has both n and m as same
        int columnDimension = m - 1;
        int rowDimension = 0;
        while (rowDimension < n && columnDimension >= 0) {

            System.out.println(rowDimension + "," + columnDimension + " is " + matrix[rowDimension][columnDimension]);
            columnDimension--;
            rowDimension++;
        }
    }

    private void printAllAntiDiagonalElementsOfAMatrix(int[][] A) {
        // Complexity : Time : [ O(n*m) ]
        // Complexity : Space : [ O(1) ]
        int n = A.length;
        int m = A[0].length;

        int[][] returnMatrix = new int[n + m - 1][m];
        int rowPrinter = 0;

        for (int j = 0; j < m; j++) {
            int columnDimension = j;
            int rowDimension = 0;
            int colPrinter = 0;
            while (rowDimension < n && columnDimension >= 0) {
//                System.out.println(rowDimension + "," + columnDimension + " is " + A[rowDimension][columnDimension]);
                returnMatrix[rowPrinter][colPrinter] = A[rowDimension][columnDimension];
                columnDimension--;
                rowDimension++;
                colPrinter++;
            }
            print("", returnMatrix);
            System.out.println();
            rowPrinter++;
        }
        // Reason for i>=1 as a cell will be printed twice

        for (int i = 1; i < n; i++) {
            int columnDimension = m - 1;
            int rowDimension = i;
            int colPrinter = 0;
            while (rowDimension < n && columnDimension >= 0) {
//                System.out.println(rowDimension + "," + columnDimension + " is " + A[rowDimension][columnDimension]);
                returnMatrix[rowPrinter][colPrinter] = A[rowDimension][columnDimension];
                columnDimension--;
                rowDimension++;
                colPrinter++;
            }
            print("", returnMatrix);
            System.out.println();
            rowPrinter++;
        }


    }


    private void matrixTransposeInPlace(int[][] A, int n, int m) {
        // Complexity : Time : [ O(n*m) ]
        // Complexity : Space : [ O(1) ]


        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                int temp = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = temp;
            }
        }
        print("", A);
    }

    private void matrixTranspose(int[][] A, int n, int m) {
        int[][] newMatrix = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMatrix[j][i] = A[i][j];
            }
        }
        print("", A);
        print("", newMatrix);

    }

    private void rotateMatrix90Degree(int[][] matrix) {
        bruteForce90Degree(matrix);
    }

    public int[] columnWiseSum(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[] returnArray = new int[m];

        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum = sum + A[i][j];
            }
            returnArray[j] = sum;
        }

        return returnArray;
    }


    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    private int[][] reverse(int[][] matrix, int n, int m) {
        int[][] returnMatrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            int columnRef = m - 1;
            for (int j = 0; j < m; j++) {
                returnMatrix[i][j] = matrix[i][columnRef];
                columnRef--;
            }
        }
        return returnMatrix;
    }

    private int[][] reverseInPlace(int[][] matrix, int n, int m) {
        int colRef = m - 1;
        for (int j = 0; j < m / 2; j++) {
            for (int i = 0; i < n; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][colRef];
                matrix[i][colRef] = temp;
            }
            colRef--;
        }
        return matrix;
    }

    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int[][] matrix) {
        printHelper.print(message, matrix);
    }

    private void print(String message, int[] array) {
        printHelper.print(message, array);
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         * Notebook_06012025 : Page No : 18
         *
         * */
        int[][] matrix = new int[4][6];
        int rowLength = matrix.length; // 4
        int columnLength = matrix[0].length; // 6
    }

    private void links() {
        /*
         * /academy/mentee-dashboard/class/348479/session?joinSession=1
         * /academy/mentee-dashboard/class/348479/assignment/problems?navref=cl_tb_br
         * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
