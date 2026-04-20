package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 06-02-2025
 * @since 06-02-2025
 */

@SuppressWarnings("UnusedReturnValue")
public class d09_Arrays_TwoDimensional {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d09_Arrays_TwoDimensional twoDimensionalArray1 = new d09_Arrays_TwoDimensional();
        twoDimensionalArray1.searchInARowWiseAndColumnWiseSortedMatrix();
        int[][] A = twoDimensionalArray1.generateSquareMatrixInSpiralOrder(2);
        int x = twoDimensionalArray1.sumOfAllPossibleSubMatrices(A);
        System.out.println(x);
    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */

    public int sumOfAllPossibleSubMatrices(int[][] A) {
        // Contribution Technique
        int N = A.length;
        int M = A[0].length;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int elementContribution = (i + 1) * (N - i) * (j + 1) * (M - j);
                sum += elementContribution * A[i][j];
            }
        }
        return sum;
    }

    public int[][] generateSquareMatrixInSpiralOrder(int A) {
        int[][] returnMatrix = new int[A][A];
        int i = 0;
        int j = 0;
        int count = 1;

        while (A > 1) {
            for (int k = 0; k < A - 1; k++) {
                returnMatrix[i][j] = count;
                j++;
                count++;
            }

            for (int k = 0; k < A - 1; k++) {
                returnMatrix[i][j] = count;
                i++;
                count++;
            }

            for (int k = 0; k < A - 1; k++) {
                returnMatrix[i][j] = count;
                j--;
                count++;
            }

            for (int k = 0; k < A - 1; k++) {
                returnMatrix[i][j] = count;
                i--;
                count++;
            }

            i++;
            j++;
            A = A - 2;

        }

        if (A == 1) {
            returnMatrix[i][j] = count;
        }


        print("", returnMatrix);

        return returnMatrix;
    }


    public void searchInARowWiseAndColumnWiseSortedMatrix() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]
        int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int B = 2;
        int N = A.length;
        int M = A[0].length;
        int ans = Integer.MAX_VALUE;
        boolean isPresent = false;
        int row = 0;
        int col = M - 1;
        while (row < N && col >= 0) {
            int target = A[row][col];
            if (target == B) {
                int currentAns = ((row + 1) * 1009) + col + 1;
                System.out.println(currentAns);
                if (currentAns < ans) {
                    isPresent = true;
                    ans = currentAns;
                }
                col--;
            } else if (target > B) {
                col--;
            } else {
                row++;
            }
        }
        System.out.println();
        System.out.println(ans);
    }

    public int rowWithMaximumNumberOfOnes(int[][] A) {
       /* QUESTION
        Given a binary sorted matrix A of size N x N. Find the row with the maximum number of 1.

        If two rows have the maximum number of 1 then return the row which has a lower index.
        Rows are numbered from top to bottom and columns are numbered from left to right.
                Assume 0-based indexing.
                Assume each row to be sorted by values.
                Expected time complexity is O(rows + columns).
                */

        int N = A.length;
        int M = A[0].length;
        int maxNumberOfOneInARow = 0;
        int rowWithMaxOne = 0;

        for(int i = 0; i < N; i++){
            int testIndex = M-1;
            int currentRowOnes = 0;
            // System.out.println("Row : "+ i + " - testIndex : "+testIndex + " - M :"+ maxNumberOfOneInARow + " - R : "+rowWithMaxOne );
            while(testIndex >=0 && A[i][testIndex] == 1){
                // System.out.println(testIndex);
                currentRowOnes++;
                testIndex--;
            }
            if(currentRowOnes > maxNumberOfOneInARow){
                rowWithMaxOne = i;
                maxNumberOfOneInARow = currentRowOnes;
            }
            // System.out.println(" RRR : "+rowWithMaxOne);
        }
        return rowWithMaxOne;
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
    }

    private void print(String message, int[][] matrix) {
        printHelper.print(message, matrix);
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
