package DSA.Advanced.Part1;

import Resources.Utilities.PrintHelper;

import java.util.Arrays;

/**
 * @author Harish Velmurugan
 * @last-modified 23-01-2025
 * @since 22-01-2025
 */
public class d10_Arrays_InterviewProblems {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        int[][] sampleArray = {
                {4, 10},
                {3, 8}
        };

//        int[][] sampleArray = {{1, 5}, {4, 6}, {7, 20}, {15, 30}, {31, 34}, {35, 50}};


        // Inputs


        // Call Stack
        d10_Arrays_InterviewProblems arraysInterviewProblems = new d10_Arrays_InterviewProblems();
        arraysInterviewProblems.mergeInterval(sampleArray[0], sampleArray[1]);
        arraysInterviewProblems.mergeIntervalAndProvidePointsMerged();
        arraysInterviewProblems.firstMissingNaturalNumber();
        arraysInterviewProblems.nextPermutation();
        arraysInterviewProblems.insertANewIntervalAndMergeIfPossible();


    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public int bruteForce_MissingNaturalNumber(int[] array) {
        // Complexity : Time : [ O(N^2) ]
        // Complexity : Space : [ O(1) ]
        int n = array.length;
        int i = 1;
        while (i < n + 2) {
            boolean isPresent = false;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == i) {
                    i++;
                    isPresent = true;
                }
            }
            if (!isPresent) {
                return i;
            }
        }
        return n + 1;
    }

    public int approach2_MissingNaturalNumber(int[] array) {
        // Complexity : Time : [ O(N+N) ]
        // Complexity : Space : [ O(1) ]
        Arrays.sort(array);
        int i = 1;
        int index = 0;

        while (index < array.length && array[index] <= i) {
            if (array[index] == i) {
                i++;
            }
            index++;
        }
        return i;
    }

    public void approach3_MissingNaturalNumber(int[] array) {
        // Complexity : Time : [ O(N+N) ]
        // Complexity : Space : [ O(1) ]
        int n = array.length;
        int[] status = new int[n];
        print("", status);
        for (int i = 0; i < n; i++) {
            if (array[i] > 0 && array[i] < n + 2) {
                status[array[i] - 1] = 1;
//                print("", status);
            }
        }
        print("", status);
        for (int i = 0; i < n; i++) {
            if (status[i] == 0) {
                print("Missing Number is ", (i + 1));
                return;
            }
        }
        print("Missing Number is : ", (n + 1));

    }

    public void approach4_MissingNaturalNumber(int[] A) {
        // Complexity : Time : [ O(N+N+N) ]
        // Complexity : Space : [ O(1) ]
        //Replace one unusable information with another unusable information inplace
        int n = A.length;

        //        int[] A = {5, 3, 1, -1, -2, 1, 2};

        for (int i = 0; i < n; i++) {
            if (A[i] <= 0 || A[i] > n + 1) {
                A[i] = n + 2;
            }
        }

//        print("", A);


        for (int i = 0; i < n; i++) {
            int val = Math.abs(A[i]);
            if (val < n + 1) {
                System.out.println(i + " - " + A[i] + " - " + A.length);
                A[val - 1] = -1 * Math.abs(A[val - 1]);
//                print("", A);
            }
        }
//        print("", A);
        for (int i = 0; i < n; i++) {
            if (A[i] > 0) {
                print("Missing Number is ", (i + 1));
                return;
            }
        }
        print("Missing Number is : ", (n + 1));

    }

    /* Section : ------------------------------- [ Problems ] ------------------------------- */

    public void mergeIntervalAndProvidePointsMerged() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]
        //Intervals are sorted based on ascending order
        //Merge all overlapping intervals and provide resulting set of overlapping intervals
//        int[][] A = {
//                {0, 2}, {1, 4}, {5, 6}, {6, 8}, {7, 10}, {8, 9}, {12, 14}
//        };

//        int[][] A = {{1, 5}, {4, 6}, {7, 20}, {15, 30}, {31, 34}, {35, 50}};
        int[][] A = {{13, 71}};

        int n = A.length;

        int[][] result2DArray = new int[n][2];
        int counter = 0;

        int[] startPoint = A[0];

        for (int i = 1; i < n; i++) {
            int start1 = startPoint[0];
            int end1 = startPoint[1];
            int start2 = A[i][0];
            int end2 = A[i][1];
            if (start2 <= end1) {
                startPoint = mergeInterval(startPoint, A[i]);
            } else {
                print("Hello : ", startPoint);
                result2DArray[counter] = startPoint;
                counter++;
                startPoint = A[i];
            }
        }
        result2DArray[counter] = startPoint;
        print("Sample ", result2DArray);
        System.out.println(counter);

        int[][] result = new int[counter + 1][2];

        for (int i = 0; i <= counter; i++) {
            result[i] = result2DArray[i];
        }
        print("Sample ", result);
        // If the start indexes are not sorted then sort and then start merging
    }

    public void firstMissingNaturalNumber() {
//        int[] array = {5, 3, 1, -1, -2, 1, 2};
//        int[] array = {1, 5, 4, 2, 3};
//        int brute = bruteForce_MissingNaturalNumber(array);
//        int approach2 = approach2_MissingNaturalNumber(array);
//        System.out.println(brute);
//        System.out.println(approach2);
//        approach3_MissingNaturalNumber(array);
        int[] array = {463, 127, 436, 72, 79, 301, 613, 898, 675, 960, 832, 486, 453, 274, 133, 721, 750, 538, 545, 112, 414, 817, 885, 812, 906, 577, 544, 101, 165, 45, 489, 503, 479, 293, 234, 427, 347, 851, 316, 827, 209, 578, 255, 56, 608, 914, 156, 537, 870, 567, 284, 240, 292, 111, 590, 713, 110, 768, 598, 879, 980, 660, 46, 320, 410, 869, 154, 970, 836, 423, 413, 501, 782, 403, 561, 117, 624, 638, 67, 646, 917, 379, 344, 543, 978, 506, 936, 947, 645, 633, 375, 706, 531, 470, 551, 632, 536, 642, 573, 705, 823, 897, 26, 476, 139, 496, 628, 91, 725, 570, 701, 244, 935, 126, 2, 560, 726, 20, 680, 7, 888, 183, 80, 804, 729, 583, 728, 515, 644, 774, 856, 192, 386, 25, 57, 471, 482, 174, 627, 757, 714, 203, 206, 847, 245, 336, 989, 326, 607, 95, 69, 71, 54, 975, 366, 591, 185, 964, 848, 84, 819, 737, 687, 215, 904, 651, 289, 134, 232, 341, 932, 64, 483, 128, 901, 808, 896, 941, 530, 195, 865, 903, 472, 508, 42, 971, 53, 86, 689, 925, 685, 934, 549, 841, 169, 317, 826, 600, 950, 90, 495, 219, 674, 814, 359, 556, 269, 187, 517, 541, 558, 8, 744, 958, 332, 163, 862, 218, 376, 23, 321, 346, 534, 864, 157, 285, 318, 200, 595, 810, 43, 32, 368, 753, 670, 887, 238, 1000, 513, 979, 499, 708, 473, 584, 981, 106, 695, 868, 881, 610, 273, 239, 190, 281, 373, 247, 364, 396, 837, 521, 871, 528, 617, 123, 894, 965, 108, 976, 451, 454, 673, 910, 681, 300, 702, 703, 307, 196, 535, 407, 763, 966, 945, 944, 65, 752, 776, 973, 554, 3, 998, 559, 35, -3, 147, 395, 761, 442, 586, 899, 191, 990, 606, 771, 393, 649, 987, 593, 877, 527, 201, 259, 150, 683, 263, 330, 21, 105, 406, 233, 303, 254, 33, 417, 497, 622, 286, 9, 967, 603, 78, 118, 304, 235, 985, 657, 741, 425, 995, 592, 844, 933, 99, 524, 418, 623, 529, 797, 342, 217, 580, 691, 772, 13, 390, 666, 87, 448, 505, 907, 765, 802, 484, 419, 669, 780, 96, 585, 796, 686, 302, 858, 388, 438, 893, 735, 360, 913, 902, 279, 720, 408, 287, 996, 507, 28, 416, 731, 928, 977, 547, 739, 788, 168, 331, 146, 664, 619, 723, 732, 102, 14, 424, 216, 575, 568, 93, 992, 272, 160, 389, 47, 647, 189, 988, 343, 991, 940, 358, 181, 611, 229, 265, 892, 422, 211, 443, 747, 736, 266, 652, 351, 612, 514, 876, 637, 329, 474, 68, 730, 825, 676, 778, 208, 956, 270, 398, 968, 268, 594, 288, 385, 866, 197, 428, 441, 672, 158, 618, 811, 363, 905, 462, 241, 226, 450, 309, 170, 822, 727, 333, 335, 92, 540, 202, 205, 115, 153, 569, 142, 290, 943, 394, 248, 228, 643, 415, 784, 579, 571, 291, 177, 711, 149, 130, 921, 922, 439, 951, 338, 769, 283, 308, 857, 253, 833, 490, 824, 518, 525, 131, 924, 27, 830, 915, 237, 694, 581, 609, 19, 152, 566, 465, 140, 81, 313, 969, 327, 6, 526, 135, 186, 656, 662, 155, 874, 648, 488, 199, 677, 952, 614, 722, 369, 682, 129, 478, 433, 809, 891, 717, 550, 748, 0, 323, 469, 151, 41, 299, 193, 487, 931, 634, 400, 799, 884, 405, 480, 76, 805, 926, 426, 312, 821, 178, 789, 449, 697, 853, 295, 48, 224, 397, 447, 946, 49, 382, 236, 867, 485, 349, 231, 227, 39, 38, 882, 210, 457, 222, 852, 665, 138, 455, 114, 204, 498, 511, 230, 509, 278, 365, 831, 412, 5, 816, 1, 324, 194, 464, 141, 420, 795, 839, 641, 10, 777, 15, 519, 829, 961, 109, 31, -5, 63, 421, 77, 430, 542, 452, 256, 355, 357, 704, 434, 459, 262, 132, 863, 468, 929, 716, 564, 890, 616, 855, 845, 548, 143, 145, 707, 787, 948, 11, 872, 61, 909, 762, 639, 786, 350, 136, 972, 75, 605, 354, 339, 305, 754, 755, 658, 40, 319, 620, 679, 984, 252, 477, 432, 684, 766, 280, 912, 949, 328, 834, 522, 310, 920, 546, 770, 214, 962, 678, 760, 916, -4, 401, 12, 957, 806, 791, 261, 277, 372, 17, 85, 982, 97, 125, 698, 399, 381, 655, 315, 182, 923, 886, 440, 223, 387, 173, 663, 588, 122, 113, 98, 803, 353, 668, 311, 587, 444, 636, 939, 429, 790, 718, 938, 738, 50, 362, 435, 813, 908, 650, 843, 959, 460, 849, 167, 384, 348, 467, 337, 356, 724, 516, 121, 880, 667, 779, 709, 986, 751, 51, 781, 659, 794, 653, 635, 553, 60, 322, 352, 696, 392, 250, 119, 431, 746, 164, 107, 563, 461, 532, 712, 391, 840, 380, 801, 574, 900, 576, 640, 378, 963, 601, 267, 207, 370, 225, 260, 500, 883, 159, 58, 166, 745, 179, 251, 271, 294, 257, 631, 895, 604, 828, 953, 520, 16, 818, 539, 491, 120, 875, 89, 692, 458, 552, 599, 861, 492, 74, 699, 55, 475, 345, 24, 700, 889, 937, 785, 758, 983};
        approach4_MissingNaturalNumber(array);

    }

    public void nextPermutation() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(1) ]

//        int[] array = {4, 3, 1, 5, 8, 7, 6, 2};
//        int[] array = {1, 2, 4, 3, 6, 5};
//        int[] array = {1, 2, 4, 5, 6, 3};
//        int[] array = {1};
        int[] array = {4, 3, 2, 1};
        print("Input", array);

        //Identify pivot where current element is less than next element while checking from right to left
        // If no pivot is present , reverse and return
        // if pivot is present, identify immediate successor by check from right to left and fetch the first element crossing a[i]
        // swap pivot and successor
        // reverse the rest


//        (1, 2, 4, 3, 6, 5)
//        (1, 2, 4, 5, 3, 6)
//        (1, 2, 4, 5, 6, 3)
//        (1, 2, 4, 6, 3, 5)


        int n = array.length;
        int previous = Integer.MIN_VALUE;
        //Identify Pivot
        int pivotIndex = -1;
        for (int i = n - 1; i >= 0; i--) {
            int current = array[i];
            if (previous > current) { // todo
                pivotIndex = i;
                System.out.println(current + " - " + i);
                break;
            } else {
                previous = current;
            }
        }

        if (pivotIndex == -1) {
            reverse(array, 0, n - 1);
            print("One ", array);
            return;
        }

        // Identify successor
        int successorIndex = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (array[i] >= array[pivotIndex]) {
                successorIndex = i;
                break;
            }
        }

        swap(array, pivotIndex, successorIndex);
        reverse(array, pivotIndex + 1, n - 1);

        print("Result", array);
        System.out.println(pivotIndex + " -- " + successorIndex);
    }

    public void insertANewIntervalAndMergeIfPossible() {
        // Complexity : Time : [ O(N) ]
        // Complexity : Space : [ O(N) ]
        /*
        You have a set of non-overlapping intervals. You are given a new interval [start, end],
        insert this new interval into the set of intervals (merge if necessary).
        You may assume that the intervals were initially sorted according to their start times
        */

//        int[][] A = {
//                {1, 3}, {6, 9}
//        };
//        int[] B = {2, 6};

        int[][] A = {
                {1, 2}, {3, 6}
        };
        int[] B = {8, 10};


        // Place the new element in the new Array in ascending order
        boolean canItBeMerged = true;
        int k = 0;
        int[][] C = new int[A.length + 1][2];
        int[][] ans = new int[A.length + 1][2];

        for (int[] subArray : A) {
            if (canItBeMerged && (subArray[0] > B[0])) {
                C[k] = B;
                k++;
                canItBeMerged = false;
            }
            C[k] = subArray;
            k++;
        }
        if (canItBeMerged) {
            C[k] = B;
        }

        int[] pointOne = C[0];
        k = 0;
        System.out.println(C.length);
        for (int i = 1; i < C.length; i++) {
            int[] pointTwo = C[i];
            int start1 = pointOne[0];
            int start2 = pointTwo[0];
            int end1 = pointOne[1];
            int end2 = pointTwo[1];
            if (start1 > end2 || start2 > end1) {
                ans[k] = pointOne;
                k++;
                pointOne = pointTwo;
            } else {
                pointOne[0] = Math.min(start1, start2);
                pointOne[1] = Math.max(end1, end2);
            }
        }
        ans[k] = pointOne;

        int[][] result = new int[k + 1][2];

        for (int i = 0; i < k + 1; i++) {
            result[i] = ans[i];
        }

        print("", result);

    }


    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    private int[] mergeInterval(int[] pointOne, int[] pointTwo) {
        int[] returnArray = new int[2];
        int start1 = pointOne[0];
        int start2 = pointTwo[0];
        int end1 = pointOne[1];
        int end2 = pointTwo[1];
        if (start1 > end2 || start2 > end1) {
            System.out.println("Non Overlapping");
        } else {
            returnArray[0] = Math.min(start1, start2);
            returnArray[1] = Math.max(end1, end2);
        }
        print("Merged Interval ", returnArray);
        return returnArray;
    }



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int res) {
        printHelper.print(message, res);
    }

    private void print(String message, int[] array) {
        printHelper.print(message, array);
    }

    private void print(String message, int[][] matrix) {
        printHelper.print(message, matrix);
    }

    private void swap(int[] array, int start, int end) {
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }

    private void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         * Notebook_06012025 : Page No - 33
         * */
    }

    private void links() {
        /*
         * academy/mentee-dashboard/class/345239/session?navref=cl_dd
         * */
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
