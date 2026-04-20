package Problems.Arrays;

import Resources.Utilities.BasicHelper;
import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class MediumArrayProblems {

    PrintHelper printHelper = new PrintHelper();
    BasicHelper basicHelper = new BasicHelper();


    public static void main(String[] args) {
        PrintHelper printHelper = new PrintHelper();
        MediumArrayProblems mediumArrayProblems = new MediumArrayProblems();
        mediumArrayProblems.optimalSolutions();
    }


    public void optimalSolutions() {
        int[] index = twoSumOptimal(new int[]{2, 9, 1, 8}, 9);
        int[] sortColorArray = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(sortColorArray); // Not optimal : 3 pointers is optimal
        int majorityElement = majorityElement(new int[]{2, 1, 3, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1});
        int maxProfit = maxProfitOptimal(new int[]{2, 1, 2, 1, 0, 1, 2});
        int[] alternatedSignsOfAnArray = alternateSignsOfAnArray(new int[]{1, 2, -4, -5});
        int[] nextPermutation = nextPermutation(new int[]{1, 2, 5, 4, 3, 0, 0});
        List<Integer> superiorElements = superiorElements(new int[]{1, 2, 2, 1});
        int longestConsecutiveSequence = longestConsecutiveSequenceInTheArrayInAnyOrder(new int[]{100, 200, 3, 2, 4, 1});
        int[][] matrixAfterRCtoZero = setZeroes(new int[][]{{1, 1, 1, 1}, {0, 0, 1, 1}, {1, 1, 0, 1}, {0, 1, 1, 1}});
        int[][] matrixRotated90Degrees = rotateOptimal(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        int subArrayWithKSumCount = countSubArray(new int[]{1, 2, 1, 2, 1}, 3);
        List<Integer> spiralPrintList = spiralMatrixPrint(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
        printHelper.print("Index with target provided is ", index);
        printHelper.print("Array after in place sorting is ", sortColorArray);
        printHelper.print("Majority element with more than half is ", majorityElement);
        printHelper.print("Maximum profit possible is ", maxProfit);
        printHelper.print("Alternated array is ", alternatedSignsOfAnArray);
        printHelper.print("Next permutation is ", nextPermutation);
        printHelper.print("Superior elements are ", superiorElements);
        printHelper.print("Longest consecutive elements length is ", longestConsecutiveSequence);
        printHelper.print("Matrix after changing to zero", matrixAfterRCtoZero);
        printHelper.print("Matrix after rotating 90 degree", matrixRotated90Degrees);
        printHelper.print("Count of sub arrays with sum k are ", subArrayWithKSumCount);
        printHelper.print("Matrix printed spirally ", spiralPrintList);


    }

    public void definitions() {
        /*
         * twoSum : Store needed value in hashmap (two pointer also possible not checked)
         * sort0s1s2s : in place insertion sort (but optimal is using 3 pointer)
         * maxSumSubArray : iterate 0-> n-1 replace max step by step. at any point replace sum to 0 if < 0
         * maximumProfit in stocks : two pointer no carry over needed (if negative it must be smallest)
         * alternated array signs :  additional result array move +ve and -ve index
         * next Permutation (possible combinations) :
         *                        1. Find the dip from reverse
         *                        2. Identify the element to fill its shoes
         *                        3. Reverse sort the remaining
         * superior elements (greater than all at right) :  reverse traversal
         * longestConsecutiveSequence in any order : move to hash set for checking; identify the initial element for a
         *                                           sequence. No element should be immediately below the start in set
         * matrix set zeroes : iterate normally identify 0 elements and store
         *                     (if aux space - better )(if same array - optimal)
         *                     keep first row and column along with a temp variable for 0th index of column
         *                     convert the middle of the matrix except these 2 storages
         *                     covert the storages
         * matrix 90 degrees : Transpose the matrix (condition : n x n) --> run above leading diagonal
         *                     Reverse the matrix --> run for first half second half sorts automatically
         * count of sub arrays : single traversal prefix sum approach
         *                      calculate prefix sum X we know the sub array sum as K so if any part has
         *                      a sum of X-K then we have one sub array
         * spiral printed list : clockwise 4 loops TopRightBottomLeft
         * */
    }

    public int[] twoSumOptimal(int[] nums, int target) {
        HashMap<Integer, Integer> storeMap = new HashMap<>();
        storeMap.put(target - nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (storeMap.containsKey(nums[i])) {
                return new int[]{storeMap.get(nums[i]), i};
            } else {
                storeMap.put(target - nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    public void sortColors(int[] nums) {
        //Insertion Sort - In place and stable
        for (int i = 1; i <= nums.length - 1; i++) {
            int currentPos = i;
            int changePlace = i - 1;
            while (nums[changePlace] > nums[currentPos]) {
                int swap = nums[currentPos];
                nums[currentPos] = nums[changePlace];
                nums[changePlace] = swap;
                changePlace--;
                currentPos--;
                if (changePlace < 0) {
                    break;
                }
            }
        }
    }


    public int majorityElement(int[] nums) {
        int count = 0;
        int possibleMajorityElement = 0;
        for (int num : nums) {
            if (count == 0) {
                possibleMajorityElement = num;
            }

            if (num == possibleMajorityElement) {
                count++;
            } else {
                count--;
            }
        }
        return possibleMajorityElement;
    }

    //-2, 1, -3, 4, -1, 2, 1, -5, 4

    //-2, 1, -3, 4, -1, 2, 1, -5, 4
    public int[] maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];

            if (sum > maxSum) {
                maxSum = sum;
                end = i + 1;
            }

            if (sum < 0) {
                sum = 0;
                start = i + 1;
                end = i + 1;
            }
        }
        int len = end - start;
        int[] returnArray = new int[len];
        for (int i = 0; i < len; i++) {
            returnArray[i] = nums[start];
            start++;
        }
        return returnArray;
    }

    public int maxSubArraySumOptimal(int[] nums) {
        //Kadane's Algorithm
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
            if (sum > maxSum) {
                maxSum = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return Math.max(maxSum, 0);
    }

    public int[] maxProfitDays(int[] prices) {
        int maxProfit = 0;
        int left = 0;
        int right = 0;
        HashMap<Integer, int[]> resultMap = new HashMap<>();
        while (right < prices.length) {
            int profit = prices[right] - prices[left];
            if (profit > maxProfit) {
                maxProfit = profit;
                resultMap.put(maxProfit, new int[]{left, right});
            }

            if (profit < 0) {
                left = right;
            }
            right++;
        }
        return resultMap.get(maxProfit);
    }

    public int maxProfitOptimal(int[] prices) {
        int maxProfit = 0;
        int left = 0;
        int right = 0;
        while (right < prices.length) {
            int profit = prices[right] - prices[left];
            if (profit > maxProfit) {
                maxProfit = profit;
            }

            if (profit < 0) {
                left = right;
            }
            right++;
        }
        return maxProfit;
    }


    public int[] alternateSignsOfAnArray(int[] arr) {
//        1,2,-4,-5
        int positiveIndex = 0;
        int negativeIndex = 1;
        int[] ans = new int[arr.length];

        for (int j : arr) {

            if (j > 0) {
                ans[positiveIndex] = j;
                positiveIndex += 2;
            } else {
                ans[negativeIndex] = j;
                negativeIndex += 2;
            }
        }
        return ans;
    }

    public int[] nextPermutation(int[] nums) {

        // 12543000
        int violationIndex = -1;
        int n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                violationIndex = i;
                break;
            }
        }

        if (violationIndex == -1) {
            basicHelper.reverse(nums);
            return nums;
        }

        for (int i = n - 1; i > violationIndex; i--) {
            if (nums[i] > nums[violationIndex]) {
                basicHelper.swap(violationIndex, i, nums);
                break;
            }
        }

        basicHelper.reverse(nums, violationIndex + 1, n - 1);

        return nums;
    }


    public List<Integer> nextPermutationListImpl(ArrayList<Integer> A) {

        // 12543000
        int violationIndex = -1;
        int n = A.size();
        for (int i = n - 2; i >= 0; i--) {
            if (A.get(i) < A.get(i + 1)) {
                violationIndex = i;
                break;
            }
        }

        if (violationIndex == -1) {
            basicHelper.reverse(A, 0, n - 1);
            return A;
        }

        for (int i = n - 1; i > violationIndex; i--) {
            if (A.get(i) > A.get(violationIndex)) {
                basicHelper.swap(violationIndex, i, A);
                break;
            }
        }

        basicHelper.reverse(A, violationIndex + 1, n - 1);

        return A;
    }

    public List<Integer> superiorElements(int[] a) {
        ArrayList<Integer> list = new ArrayList<>();
        int n = a.length;
        int maxElementOnTheRight = a[n - 1];
        list.add(a[n - 1]);
        for (int i = n - 2; i >= 0; i--) {

            if (a[i] > maxElementOnTheRight) {
                list.add(a[i]);
                maxElementOnTheRight = a[i];
            }
        }
        return list;
    }


    public int longestConsecutiveSequenceInTheArrayInAnyOrder(int[] nums) {

        HashSet<Integer> hashSet = new HashSet<>();
        for (int a : nums) {
            hashSet.add(a);
        }
        int maxLen = 0;


        for (int i = 0; i < nums.length; i++) {
            if (!hashSet.contains(nums[i] - 1)) {
                int currentSearchTerm = nums[i];
                int cntLen = 1;
                maxLen = Math.max(maxLen, 1);
                while (cntLen >= 1) {
                    currentSearchTerm = currentSearchTerm + 1;
                    if (hashSet.contains(currentSearchTerm)) {
                        cntLen++;
                        maxLen = Math.max(maxLen, cntLen);
                    } else {
                        cntLen = 0;
                    }
                }
            }
        }
        return maxLen;
    }


    public int[][] setZeroes(int[][] matrix) {

        int col0 = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    if (j == 0) {
                        col0 = 0;
                    } else {
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (matrix[0][0] == 0) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0 == 0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        return matrix;
    }

    public String minRemoveToMakeValid(String s) {

        Stack<Character> stack = new Stack<>();

        char[] characters = s.toCharArray();
        ArrayList<Integer> forwardBrace = new ArrayList<>();
        ArrayList<Integer> reverseBrace = new ArrayList<>();

        for (int i = 0; i < characters.length; i++) {

            if (characters[i] == '(') {
                forwardBrace.add(i);
                stack.push('(');
            } else if (characters[i] == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    forwardBrace.remove(forwardBrace.size() - 1);
                    stack.pop();
                } else {
                    reverseBrace.add(i);
                }
            }
        }

        for (int index : forwardBrace) {
            characters[index] = '0';
        }
        for (int index : reverseBrace) {
            characters[index] = '0';
        }
        printHelper.print("F List = ", forwardBrace);
        printHelper.print("R List = ", reverseBrace);

        System.out.println(String.valueOf(characters).replaceAll("0", ""));

        return String.valueOf(characters).replaceAll("0", "");

    }

    public int[][] rotateOptimal(int[][] mat) {

        int n = mat.length;
        //Transpose
//        printHelper.print("Original ", mat);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = x;
            }
        }

//        printHelper.print("Transposed ", mat);
        //Reverse column wise
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int x = mat[i][n - 1 - j];
                mat[i][n - 1 - j] = mat[i][j];
                mat[i][j] = x;
            }
        }
//        printHelper.print("After Reversing ", mat);

        return mat;
    }

    public int countSubArray(int[] arr, int s) {
//        N = 4, array[] = {3, 1, 2, 4}, k = 6

        int count = 0;
        int sum = 0;

        HashMap<Integer, Integer> storage = new HashMap<>();
        storage.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            int rem = sum - s;
            if (storage.containsKey(rem)) {
                count = count + storage.get(rem);
                if (storage.containsKey(sum)) {
                    storage.put(sum, storage.get(sum) + 1);
                } else {
                    storage.put(sum, 1);
                }
            } else {
                if (storage.containsKey(sum)) {
                    storage.put(sum, storage.get(sum) + 1);
                } else {
                    storage.put(sum, 1);
                }
            }
        }

        return count;
    }

    @SuppressWarnings("DataFlowIssue")
    public ArrayList<Integer> spiralMatrixPrint(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int left = 0;
        int top = 0;
        int bottom = n - 1;
        int right = m - 1;
        ArrayList<Integer> arrayList = new ArrayList<>();

        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                arrayList.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                arrayList.add(matrix[i][right]);
            }
            right--;

            for (int i = right; i >= left; i--) {
                arrayList.add(matrix[bottom][i]);
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                arrayList.add(matrix[i][left]);
            }
            left++;
        }
        for (int i = arrayList.size() - 1; i > n * m - 1; i--) {
            arrayList.remove(i);
        }
//        printHelper.print("Arraylist", arrayList);
        return arrayList;
    }

}