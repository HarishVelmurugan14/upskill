package Problems.Arrays;

import java.util.ArrayList;
import java.util.List;

public class HardArrayProblems {

    public static void main(String[] args) {
        HardArrayProblems hardArrayProblems = new HardArrayProblems();
        hardArrayProblems.pascalsTriangleList(7);
        System.out.println();
        hardArrayProblems.pascalsTriangleMatrix(7);
        System.out.println();
        hardArrayProblems.variation1PascalTriangle(7, 5);
    }

    public void optimalSolutions() {

    }

    public void definitions() {
        /*
         * Pascals Triangle
         * */
    }

    public List<List<Integer>> pascalsTriangleList(int numRows) {
        List<List<Integer>> returnList = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> subList = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                int currentNum = variation1PascalTriangle(i, j);
                subList.add(currentNum);
            }
            returnList.add(subList);
            System.out.println();
        }

        return returnList;
    }

    public int[][] pascalsTriangleMatrix(int numRows) {
        int[][] returnMatrix = new int[numRows][numRows];
        for (int i = 1; i <= numRows; i++) {
            for (int j = 1; j <= i; j++) {
                returnMatrix[i-1][j-1] = variation1PascalTriangle(i, j);
            }
        }
        return returnMatrix;
    }

    private int variation1PascalTriangle(int row, int column) {
        // given row and column (normal numbers and not indexes). Identify r,c th element
        // Use (self optimized) combination nCr logic
        // Formulas.txt
        int n = row - 1;
        int r = column - 1;
        int j = r;
        double test = 1;
        for (int i = n; i > n - r; i--) {
            test = (test * i) / j;
            j--;
        }
        System.out.print(Math.round(test) + "-");
        return (int) Math.round(test);
    }


}
