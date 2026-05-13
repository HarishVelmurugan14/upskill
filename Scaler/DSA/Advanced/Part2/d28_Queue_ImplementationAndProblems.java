package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.*;

/**
 * @author Harish Velmurugan
 * @last-modified 11-03-2025
 * @since 11-03-2025
 */
@SuppressWarnings({"StringTemplateMigration", "DataFlowIssue", "UnusedReturnValue"})
public class d28_Queue_ImplementationAndProblems {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d28_Queue_ImplementationAndProblems d28_queue_implementationAndProblems = new d28_Queue_ImplementationAndProblems();
        d28_queue_implementationAndProblems.implementQueuesUsingStack(); // Q1 //##
        d28_queue_implementationAndProblems.parkingIceCreamTruck(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3); // Q2 //##

        d28_queue_implementationAndProblems.nIntegersContaining123(8); // AQ1 //##
        d28_queue_implementationAndProblems.uniqueLetterInGrowthOfAString("jyhrcwuengcbnuchctluxjgtxqtfvrebveewgasl" +
                "uuwooupcyxwgl"); // AQ2 //##
        d28_queue_implementationAndProblems.sumOfMinAndMax(null, 2); // AQ3 // LC1438 //##

        d28_queue_implementationAndProblems.maximumInAFixedSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3); // LC239 //## // parking icecream


    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */


    public int[] parkingIceCreamTruck(int[] A, int B) {
        /* QUESTION
        Imagine you're an ice cream truck driver in a beachside town. The beach is divided into several sections,
        and each section has varying numbers of beach goers wanting ice cream given by the array of integers A.
        For simplicity, let's say the beach is divided into 8 sections. One day, you not down the number of
        potential customers in each section: [5, 12, 3, 4, 8, 10, 2, 7]. This means there are 5 people in
        the first section, 12 in the second, and so on.
        You can only stop your truck in B consecutive sections at a time because of parking restrictions.
        To maximize sales, you want to park where the most customers are clustered together.
        For all B consecutive sections, identify the busiest stretch to park your ice cream truck and serve
        the most customers. Return an array C, where C[i] is the busiest section in each of the B consecutive
        sections. Refer to the given example for clarity.
        If B > length of the array, return 1 element with the max of the array.
           */
        Deque<Integer> deque = new LinkedList<>();
        int N = A.length;
        int[] res = new int[N - B + 1];
        int indexToBeRemoved = 0;
        for (int i = 0; i < N; i++) {
            while (!deque.isEmpty() && A[i] >= A[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.offerLast(i);
//            printDeque(deque, A);
            if (i >= B - 1) {
                res[indexToBeRemoved] = A[deque.peekFirst()];
                if (deque.peekFirst() == indexToBeRemoved) {
                    deque.removeFirst();
                }
                indexToBeRemoved++;
            }
        }
        return res;
    }


    public int[] maximumInAFixedSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        int[] res = new int[N - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int index = 0;

        for (int i = 0; i < N; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                System.out.println(nums[i] + " - " + nums[deque.peekLast()]);
                deque.removeLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                res[index] = nums[deque.peekFirst()];
                if (index == deque.peekFirst()) {
                    deque.pop();
                }
                index++;
            }
        }
        return res;
    }

    public int firstUniqueCharacterInAString(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] array = s.toCharArray();
        int N = array.length;

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            char x = array[i];
            if (!map.containsKey(x)) {
                deque.offerLast(i);
                map.put(x, 1);
            } else {
                map.put(x, map.get(x) + 1);
            }
        }
        while (!deque.isEmpty()) {
            int index = deque.pollFirst();
            if (map.get(array[index]) == 1) {
                return index;
            }
        }
        return -1;
    }

    public String uniqueLetterInGrowthOfAString(String A) {
        /* QUESTION :
        Imagine you're a teacher. You ask students to call out a letter one by one. After each letter,
        you jot down the very first letter that's only been called out once. If all letters have been repeated,
        you write "#".
        Here's a scenario:
        A student says "a". It's the first letter. You write "a".
        Next, a student says "b", "a" is still unique, so you add "a". Now it's "aa".
        A student says "a" again. Now, "b" is the unique one. You add "b", making it "aab".
        A student says "b". All letters so far are repeated. You add "#". It becomes "aab#".
        A student says "c". "c" is unique. You add "c". The final is "aab#c".
        Your task? Given the sequence the students call out A, determine the string on the board.
            abadbc
            */

        HashMap<Character, Integer> map = new HashMap<>();
        Deque<Character> deque = new LinkedList<>();
//        String res = ""; it is in efficient to change string each time new string is created
        StringBuilder res = new StringBuilder();
        for (char x : A.toCharArray()) {
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else {
                deque.offerLast(x);
                map.put(x, 1);
            }

            while (!deque.isEmpty() && map.get(deque.peekFirst()) > 1) {
                deque.removeFirst();
            }
//            res = res + (deque.isEmpty() ? "#" : deque.peekFirst());
            res.append(deque.isEmpty() ? "#" : deque.peekFirst());

            System.out.println(res);
        }
        return res.toString();
    }

    public int[] nIntegersContaining123(int A) {
        int[] result = new int[A];
        Queue<Long> queue = new LinkedList<>();
        queue.add(1L);
        queue.add(2L);
        queue.add(3L);

        for (int i = 0; i < A; i++) {
            long num = queue.poll();
            result[i] = (int) num; // not i the number itself
            queue.add(num * 10 + 1);
            queue.add(num * 10 + 2);
            queue.add(num * 10 + 3);
        }

        return result;
    }

    public int sumOfMinAndMax(int[] A, int B) {
        int n = A.length;
        long MOD = 1_000_000_007L;
        long sum = 0;

        Deque<Integer> maxDeque = new ArrayDeque<>(); // decreasing
        Deque<Integer> minDeque = new ArrayDeque<>(); // increasing

        for (int i = 0; i < n; i++) {
            // maintain decreasing deque for max
            while (!maxDeque.isEmpty() && A[maxDeque.peekLast()] <= A[i])
                maxDeque.pollLast();
            maxDeque.addLast(i);

            // maintain increasing deque for min
            while (!minDeque.isEmpty() && A[minDeque.peekLast()] >= A[i])
                minDeque.pollLast();
            minDeque.addLast(i);

            // Fix: use while instead of if (multiple elements may be out of window)
            while (maxDeque.peekFirst() < i - B + 1) maxDeque.pollFirst();
            while (minDeque.peekFirst() < i - B + 1) minDeque.pollFirst();

            if (i >= B - 1) {
                sum = (sum + A[maxDeque.peekFirst()] + A[minDeque.peekFirst()]) % MOD;
            }
        }

        // Fix: handle negative sum (when all values are negative, mod can go negative)
        sum = (sum + MOD) % MOD;
        return (int) sum;
    }

    public void implementQueuesUsingStack() {
        UserQueue userQueue = new UserQueue();
        UserQueue.push(3);
        UserQueue.push(4);
        UserQueue.push(5);
        UserQueue.push(6);
        System.out.println("Size : " + UserQueue.size());
        System.out.println("Is Empty : " + UserQueue.empty());
        System.out.println("Peek " + UserQueue.peek());
        System.out.println("Pop " + UserQueue.pop());
        UserQueue.push(7);
        System.out.println("Peek " + UserQueue.peek());
    }

    public void printDeque(Deque<Integer> deque, int[] A) {
        for (Integer num : deque) {
            System.out.print(A[num] + " -> ");
        }
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */


    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int[] arr) {
        printHelper.print(message, arr);
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

class UserQueue {
    private static Stack<Integer> inputStack;
    private static Stack<Integer> outputStack;

    UserQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    public static void push(int X) {
        inputStack.push(X);
    }

    public static int pop() {
        populateOutputStackIfEmpty();
        return outputStack.pop();
    }

    public static int peek() {
        populateOutputStackIfEmpty();
        return outputStack.peek();
    }

    public static boolean empty() {
        return (inputStack.isEmpty() && outputStack.isEmpty());
    }

    public static int size() {
        return inputStack.size() + outputStack.size();
    }

    public static void populateOutputStackIfEmpty() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
    }
}


