package Problems.Arrays;

import Resources.Utilities.BasicHelper;
import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyArrayProblems {

    BasicHelper basicHelper = new BasicHelper();
    PrintHelper printHelper = new PrintHelper();

    public void currentCheck(int[] arr) {
        int[] a = new int[]{-1, 0, 1, 1, -1, -1, 0};
        int n1 = longestSubArrayWithSumKOptimal(a, 0);
        printHelper.print("max : ", n1);

    }

    public void optimalSolutions() {
        int[] arr = new int[]{1, 3, 4, 5, 7, 8, 34, 26, 6, 0, 1345};
        int[] arr1 = new int[]{1, 2, 2, 3, 3, 3, 4, 4, 5, 5};
        int[] arr2 = new int[]{1, 2, 3, 4, 5};
        int[] arr3 = new int[]{1, 2};
        int[] arr4 = new int[]{0, 0, 0, 0, 3, 4, 0, 0, 5, 0, 0, 0, 2};
        printHelper.print("Original Array : ", arr);

        currentCheck(arr);
        if (true) {
            return;
        }

        int largestElement = largestElementOptimal(arr, arr.length);
        int[] secondOrderArray = getSecondOrderElementsOptimal(arr, arr.length);
        int isSorted = isSortedOptimal(arr, arr.length);
        boolean isSortedRotated = isSortedRotated(arr);
        int uniqueElements = removeDuplicatesOptimal(arr, arr.length);
        int[] leftRotatedArray = leftRotateArrayOneStepOptimal(arr, arr.length);
        int[] leftRotatedArrayKStep = leftRotateArrayKStepOptimal(arr, 3);
        int[] rightRotateKStep = rightRotateArrayKStepOptimal(arr, 3);
        int[] zeroesMoved = moveZerosOptimal(arr, arr.length);
        List<Integer> unionOfTwoArrayList = unionOfTwoArraysOptimal(new int[]{2, 2, 4}, new int[]{1, 2, 3, 3});
        int missingNumber = missingNumberOptimal(new int[]{9, 6, 4, 2, 3, 5, 0, 8, 1});
        int maxConsecutiveOnes = findMaxConsecutiveOnesOptimal(new int[]{1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1});
        int singleNumber = singleNumberOptimal(new int[]{4, 1, 2, 1, 2, 3, 4});
        int positiveLongSubArray = longestSubArrayWithSumKOptimal(new int[]{4, 1, 2, 1, 2, 3, 4}, 4);
        int positiveNegativeLongSubArray = longestSubArrayWithSumKPositiveNegativeOptimal(new int[]{-1, 0, 1, 1, -1, -1, 0}, 3);


        printHelper.print("Largest element : ", largestElement);
        printHelper.print("Second order elements : ", secondOrderArray);
        printHelper.print("Is sorted : ", (isSorted == 1));
        printHelper.print("Is sorted rotated : ", isSortedRotated);
        printHelper.print("Unique Elements : ", uniqueElements);
        printHelper.print("Left Rotated Array One Step: ", leftRotatedArray);
        printHelper.print("Left Rotated Array K Step: ", leftRotatedArrayKStep);
        printHelper.print("Right Rotated Array K Step: ", rightRotateKStep);
        printHelper.print("Zeroes Moved to last maintaining order : ", zeroesMoved);
        printHelper.print("Union of those two lists are : ", unionOfTwoArrayList);
        printHelper.print("Missing number : ", missingNumber);
        printHelper.print("Max consecutive ones : ", maxConsecutiveOnes);
        printHelper.print("Single number is : ", singleNumber);
        printHelper.print("Longest sub array in +ve set : ", positiveLongSubArray);
        printHelper.print("Longest sub array in +ve & -ve set : ", positiveNegativeLongSubArray);


        printHelper.print("Final Array : ", arr);
    }

    public void definitions() {
        /*
         * Large element - single traversal identification (No sorting)
         * second order elements - single traversal with 4 variables no sorting needed
         * check if array is sorted - traversal verifying non decreasing order
         * check if array is sorted after rotating - single traversal with one violation allowed;
         * remove duplicates - single traversal swap on the places of duplicate elements
         * left rotate one place - store temp variable for 0th index, swap all and restore 0 at last
         * left rotate by k place - reversal algorithm : reverse 0 -> k-1 : reverse k -> n : reverse all
         * move zeroes to last - two pointer forward : swap non 0 elements step-by-step starting with 1st 0
         * union of two arrays -  compare a[i] and b[j] mutually increasing and check for equal cases
         * missing number in first n number :  sum of first n number (n(n+1))/2
         * max consecutive ones in an array : two pointer flexible window
         * only single number (not a pair) :  XOR approach
         * longest sub array with sum = k : Two pointer window algo
         * longest sub array with sum = k (-ve inc) : Hashing prefix sum
         * */
    }

    private void insertionSort(int[] arr, int n) {
        for (int i = 1; i <= n - 1; i++) {
            int currentPos = i;
            int changePlace = i - 1;
            while (arr[changePlace] > arr[currentPos]) {
                int swap = arr[currentPos];
                arr[currentPos] = arr[changePlace];
                arr[changePlace] = swap;
                changePlace--;
                currentPos--;
                if (changePlace < 0) {
                    break;
                }
            }
        }
    }

    public int largestElement(int[] arr, int n) {
//        new InsertionSort().sort(arr,0, n-1);
        insertionSort(arr, n);
        return arr[n - 1];
    }

    public int largestElementOptimal(int[] arr, int n) {
        int large = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > large) {
                large = arr[i];
            }
        }
        return large;
    }

    public int[] getSecondOrderElements(int n, int[] a) {
        insertionSort(a, n);
        return new int[]{a[n - 2], a[1]};
    }

    public int[] getSecondOrderElementsOptimal(int[] a, int n) {
        //For getting second small and second large is sorting all needed
        // Identify in single traversal
        int small = Integer.MAX_VALUE;
        int secondSmall = Integer.MAX_VALUE;
        int large = Integer.MIN_VALUE;
        int secondLarge = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (a[i] < small) {
                secondSmall = small;
                small = a[i];
            } else if (a[i] < secondSmall) {
                secondSmall = a[i];
            }
            if (a[i] > large) {
                secondLarge = large;
                large = a[i];
            } else if (a[i] > secondLarge) {
                secondLarge = a[i];
            }
        }
        return new int[]{secondLarge, secondSmall};
    }

    public int isSortedOptimal(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) {
                return 0;
            }
        }
        return 1;
    }

    public boolean isSortedRotated(int[] nums) {
        int secondCycleFirstElement = nums[0];
        int violationCount = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (violationCount > 1) {
                    return false;
                }
                violationCount++;
            } else if (violationCount == 1 && nums[i] > secondCycleFirstElement) {
                return false;
            }
        }
        return true;
    }

    public int removeDuplicates(int[] arr, int n) {
        int previousUniqueValue = arr[0];
        int indexToBeSwapped = 1;
        int counter = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] == previousUniqueValue) {
                counter++;
            } else {
                if (counter > 0) {
                    arr[indexToBeSwapped] = arr[i];
                    indexToBeSwapped++;
                } else {
                    indexToBeSwapped = i + 1;
                }
                previousUniqueValue = arr[i];
            }
        }
        return n - counter;
    }

    public int removeDuplicatesOptimal(int[] arr, int n) {
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }

    public int[] leftRotateArrayOneStepOptimal(int[] arr, int n) {
        int zerothIndexElement = arr[0];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[n - 1] = zerothIndexElement;
        return arr;
    }

    public int[] rightRotateArrayKStep(int[] nums, int k) {
        int[] tempArray = new int[k];
        int n = nums.length;
        if (k > n) {
            k = k % n;
            if (k == 0) {
                return nums;
            }
        }
        int temp = 0;
        for (int i = n - k; i < n; i++) {
            tempArray[temp] = nums[i];
            temp++;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i < k) {
                nums[i] = tempArray[i];
            } else {
                nums[i] = nums[i - k];
            }
        }
        return nums;
    }

    public int[] leftRotateArrayKStepOptimal(int[] arr, int k) {
//        12345
//        45123
//        32154
//        12345
        int n = arr.length;
        if (k > n) {
            k = k % n;
            if (k == 0) {
                return arr;
            }
        }
        basicHelper.reverse(arr, 0, k - 1);
        basicHelper.reverse(arr, k, arr.length - 1);
        basicHelper.reverse(arr);
        return arr;
    }

    public int[] rightRotateArrayKStepOptimal(int[] arr, int k) {

        int n = arr.length;
        if (k > n) {
            k = k % n;
            if (k == 0) {
                return arr;
            }
        }
        basicHelper.reverse(arr);
        basicHelper.reverse(arr, 0, k - 1);
        basicHelper.reverse(arr, k, arr.length - 1);
        return arr;
    }

    public int[] moveZerosOptimal(int[] a, int n) {
        int j = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                j = i;
                break;
            }
        }
        if (j == -1) return a;

        for (int i = j + 1; i < n; i++) {
            if (a[i] != 0) {
                int x = a[i];
                a[i] = a[j];
                a[j] = x;
                j++;
            }
        }
        return a;
    }

    public int linearSearch(int n, int num, int[] arr) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }

    public List<Integer> unionOfTwoArraysOptimal(int[] a, int[] b) {
        List<Integer> returnList = new ArrayList<>();
        int iElement = a[0];
        int jElement = b[0];
        int i = 0;
        int j = 0;
        while (i != -1 || j != -1) {
            if (iElement <= jElement) {
                returnList.add(iElement);
                if (iElement == jElement) {
                    j++;
                    j = identifyNextUniqueElement(b, j, jElement);
                    if (j != -1) {
                        jElement = b[j];
                    } else {
                        jElement = Integer.MAX_VALUE;
                    }

                }
                i++;
                i = identifyNextUniqueElement(a, i, iElement);
                if (i != -1) {
                    iElement = a[i];
                } else {
                    iElement = Integer.MAX_VALUE;
                }

            } else {
                returnList.add(jElement);
                j++;
                j = identifyNextUniqueElement(b, j, jElement);
                if (j != -1) {
                    jElement = b[j];
                } else {
                    jElement = Integer.MAX_VALUE;
                }

            }
        }
        return returnList;
    }

    private int identifyNextUniqueElement(int[] arr, int k, int kElement) {
        while (k < arr.length) {
            if (arr[k] > kElement) {
                return k;
            }
            k++;
        }
        return -1;
    }

    public int missingNumber(int[] nums) {

        insertionSort(nums, nums.length);
        int prev = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == prev) {
                prev++;
            } else {
                if (nums[i] == 0) {
                    continue;
                }
                return prev;
            }
        }
        return -1;
    }

    public int missingNumberOptimal(int[] nums) {
        int n = nums.length;
        int sum = (n * (n + 1)) / 2;
        for (int num : nums) {
            sum = sum - num;
        }
        return sum;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxConsecutiveOnes = (nums[0] == 1) ? 1 : 0;
        int currentConsecutive = maxConsecutiveOnes;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (currentConsecutive >= 1) {
                    currentConsecutive++;
                    if (currentConsecutive > maxConsecutiveOnes) {
                        maxConsecutiveOnes = currentConsecutive;
                    }
                } else {
                    currentConsecutive = 1;
                }
            } else {
                currentConsecutive = 0;
            }
        }
        return Math.max(currentConsecutive, maxConsecutiveOnes);
    }

    public int findMaxConsecutiveOnesOptimal(int[] nums) {

        int n = nums.length;
        int maxConsecutive = 0;
        for (int left = 0; left < n; left++) {
            int currentConsecutive = 0;
            int right = left;
            while (right < n) {
                if (nums[right] == 1) {
                    currentConsecutive++;
                } else {
                    break;
                }
                right++;
            }
            maxConsecutive = Math.max(maxConsecutive, currentConsecutive);
        }
        return maxConsecutive;
    }

    public int traffic(int n, int m, int[] vehicle) {
        int maxConsecutive = 0;
        for (int left = 0; left < n; left++) {
            int violationAllowed = m;
            //swap at most m elements
            int right = left;
            int currentConsecutive = 0;
            while (right < n) {
                if (vehicle[right] == 1) {
                    currentConsecutive++;
                } else if (violationAllowed > 0) {
                    violationAllowed--;
                    currentConsecutive++;
                } else {
                    break;
                }
                right++;
            }
            maxConsecutive = Math.max(maxConsecutive, currentConsecutive);
        }

        return maxConsecutive;
    }

    public int singleNumber(int[] nums) {
        int n = nums.length;
        List<Integer> ignoreList = new ArrayList<>();
        for (int left = 0; left < n; left++) {
            if (ignoreList.contains(left)) {
                // Removing pairing values
            } else {
                int currentElement = nums[left];
                int singleElement = nums[left];
                int right = left + 1;
                while (right < n) {
                    if (nums[right] == currentElement) {
                        ignoreList.add(right);
                        singleElement = -1;
                        break;
                    }
                    right++;
                }
                if (singleElement != -1) {
                    return singleElement;
                }
            }
        }
        return -1;
    }

    public int singleNumberOptimal(int[] nums) {

        int xorr = 0;
        for (int num : nums) {
            xorr = xorr ^ num;
        }
        return xorr;
    }

    public int longestSubArrayWithSumKOptimal(int[] a, long k) {
        int n = a.length;
        int maxLen = 0;
        int left = 0;
        int right = 0;
        long sum = a[0];
        while (right < n) {
            while (sum > k && left <= right) {
                sum = sum - a[left];
                left++;
            }
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
            if (right < n) {
                sum = sum + a[right];
            }
        }
        return maxLen;
    }

    public int longestSubArrayWithSumKPositiveNegativeOptimal(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int maxLen = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }
            int rem = sum - k;
            if (prefixSumMap.containsKey(rem)) {
                int len = i - prefixSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }
            if (!prefixSumMap.containsKey(sum)) {
                prefixSumMap.put(sum, i);
            }
        }
        return maxLen;
    }


}
