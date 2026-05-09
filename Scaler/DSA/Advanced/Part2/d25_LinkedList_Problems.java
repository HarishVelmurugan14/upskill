package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Harish Velmurugan
 * @last-modified 05-03-2025
 * @since 05-03-2025
 */
@SuppressWarnings({"ClassEscapesDefinedScope", "UnusedReturnValue", "AccessStaticViaInstance"})
public class d25_LinkedList_Problems {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d24_ClassesObjects_LinkedLists d24_classesObjects_linkedLists = new d24_ClassesObjects_LinkedLists();
        d25_LinkedList_Problems d25_linkedList_problems = new d25_LinkedList_Problems();
        ListNode head = d25_linkedList_problems.insertAtHead(null, 7);
        head = d25_linkedList_problems.insertAtHead(head, 7);
        head = d25_linkedList_problems.insertAtHead(head, 7);
        head = d25_linkedList_problems.insertAtHead(head, 7);
        head = d25_linkedList_problems.insertAtHead(head, 7);
        head = d25_linkedList_problems.insertAtHead(head, 6);
        head = d25_linkedList_problems.insertAtHead(head, 3);
        head = d25_linkedList_problems.insertAtHead(head, 3);
        head = d25_linkedList_problems.insertAtHead(head, 6);

        d25_linkedList_problems.printALinkedList(head);
        d25_linkedList_problems.reverseALinkedListInPlaceInOneIteration(head); // Q1

        RandomListNode head1 = d25_linkedList_problems.createList(new int[]{1, 2, 3, 4, 5});
        d25_linkedList_problems.setRandomPointers(head1, new int[]{2, 0, 4, -1, 1});
        d25_linkedList_problems.printList(head1);
        RandomListNode cloned = d25_linkedList_problems.copyRandomList(head1); //Q2
        d25_linkedList_problems.printList(cloned);

        d25_linkedList_problems.insertAtPosition(head, 3, 5); // Q3
        d25_linkedList_problems.deleteAtPosition(head, 5); // Q4

        d25_linkedList_problems.removeElements(head, 7); // LC203


        ListNode headA = d24_classesObjects_linkedLists.createList(new int[]{1, 1, 1, 2, 3, 3, 4, 5, 5, 5, 5, 6});
        d25_linkedList_problems.deleteDuplicates(headA); // AQ1

        ListNode headB = d24_classesObjects_linkedLists.createList(new int[]{1, 1, 1, 2, 3, 3, 4, 5, 5, 5, 5, 6, 7, 9, 11});
        d25_linkedList_problems.removeNthFromEnd(headB, 4); // AQ2
    }

    /* Section : ----------------------------------- [ Problems ] ------------------------------------ */

    public static void printALinkedList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }


    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode curr = head, prev = null;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }

//    public ListNode reverseALinkedListInPlaceInOneIteration(ListNode A) {
//
//        if (A == null) return null;
//        if (A.next == null) return A;
//
//
//    }

    public ListNode insertAtHead(ListNode A, int B) {
        ListNode x = new ListNode(B);
        x.next = A;
        return x;
    }

    public ListNode insertAtPosition(ListNode A, int B, int C) {
        ListNode x = new ListNode(B);
        ListNode temp = A;
        int currentPos = 0;

        if (C == 0 || A == null) {
            return insertAtHead(A, B);
        }

        while (temp.next != null && currentPos < C - 1) {
            temp = temp.next;
            currentPos++;
        }

        x.next = temp.next;
        temp.next = x;
        printALinkedList(A);
        return A;
    }

    public ListNode deleteAtPosition(ListNode A, int B) {
        if (A == null) {
            return null;
        }
        if (B == 0) {
            return A.next;
        }
        ListNode temp = A;
        int currentPos = 0;

        while (temp.next != null && currentPos < B - 1) {
            temp = temp.next;
            currentPos++;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }

        printALinkedList(A);
        return A;
    }

    public ListNode reverseALinkedListInPlaceInOneIteration(ListNode A) {
        /*
         * REVERSE LINKED LIST - In-place one iteration
         *
         * 3 POINTERS: prev=null, curr=head, next=null
         *
         * EACH STEP:
         *   next = curr.next   → save forward link before breaking
         *   curr.next = prev   → reverse the pointer
         *   prev = curr        → advance prev
         *   curr = next        → advance curr
         *
         * 1→2→3→null
         * null←1  2→3→null  (prev=1, curr=2)
         * null←1←2  3→null  (prev=2, curr=3)
         * null←1←2←3        (prev=3, curr=null) → return prev
         *
         * Time: O(N)  Space: O(1)
         */

        ListNode prev = null;
        ListNode curr = A;
        ListNode next = null;

        while (curr != null) {
            next = curr.next; // copy the flow first before breaking
            curr.next = prev; // break the flow and init as null (temp) // second iteration prev becomes next
            prev = curr; // copy the isolated element
            curr = next; // make next element current
        }

        return prev;
    }


    public RandomListNode copyRandomList(RandomListNode head) {
        /*
         * COPY LIST WITH RANDOM POINTER - HashMap cloning
         *
         * 2 PASSES:
         *   Pass 1: old node → new node (label only, no links)
         *   Pass 2: wire next + random using map lookup
         *
         * WHY HASHMAP: random pointer can point anywhere,
         *              map gives O(1) lookup of already created clone
         *
         * store.get(current.next)   → null safe (HashMap returns null for null key)
         * store.get(current.random) → null safe (same)
         *
         * return store.get(head) → returns head's clone, not original
         *
         * Time: O(N)  Space: O(N)
         */
        HashMap<RandomListNode, RandomListNode> store = new HashMap<>();
        RandomListNode current = head;
        while (current != null) {
            RandomListNode currentCopy = new RandomListNode(current.label);
            store.put(current, currentCopy);
            current = current.next;
        }

        current = head;
        RandomListNode res = store.get(head);
        RandomListNode ans = res;

        while (current != null) {
            RandomListNode newNext = store.get(current.next);
            RandomListNode newRand = store.get(current.random);
            ans.next = newNext;
            ans.random = newRand;
            current = current.next;
            ans = ans.next;
        }
        return res;
    }

    public ListNode deleteDuplicates(ListNode head) {
        /*
         * DELETE DUPLICATES - New list approach (sorted input)
         *
         * STRATEGY: Build new list, skip node if val == prevElement
         *
         * 1→1→2→3→3→null
         *   ↓
         * 1→2→3→null
         *
         * prevElement tracks last added val → O(1) duplicate check (works only on sorted list)
         * resWorker   tracks tail of new list for O(1) append
         *
         * NOTE: in-place alternative → curr.next = curr.next.next (no extra space)
         *
         * Time: O(N)  Space: O(N)
         */
        ListNode res = new ListNode(head.val);
        ListNode resWorker = res;
        int prevElement = head.val;
        head = head.next;

        while (head != null) {
            if (head.val != prevElement) {
                prevElement = head.val;
                resWorker.next = new ListNode(head.val);
                resWorker = resWorker.next;
            }
            head = head.next;
        }
        return res;
    }

    public ListNode removeNthFromEnd(ListNode A, int B) {
        /*
         * REMOVE NTH FROM END - Two pass approach
         *
         * STRATEGY: convert "from end" → "from start" index
         *   deleteIndex = length - B
         *   deleteIndex < 0 → clamp to 0 (remove head)
         *
         * EXAMPLE: 1→2→3→4→5, B=2
         *   length=5, deleteIndex=5-2=3 → delete node(4) → 1→2→3→5
         *
         * deleteAtIndex: traverse to index-1, skip node via curr.next = curr.next.next
         *
         * NOTE: optimal approach → two pointer (fast/slow), fast starts B ahead
         *       when fast reaches end, slow is at index-1 → O(N) one pass
         *
         * Time: O(N)  Space: O(1)
         */

        int count = length(A);
        int deleteIndex = count - B;
        if (deleteIndex < 0) {
            deleteIndex = 0;
        }
        return deleteAtIndex(A, deleteIndex);
    }

    public int length(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public ListNode deleteAtIndex(ListNode head, int index) {
        if (head == null) return null;
        if (index < 0 || index >= length(head)) {
            System.out.println("Index out of bounds");
            return head;
        }
        if (index == 0) return head.next;

        ListNode current = head;
        int pos = 0;
        while (current != null) {
            if (pos == index - 1) {
                current.next = current.next.next;
                return head;
            }
            pos++;
            current = current.next;
        }
        return head;
    }

    public static ListNode reverseBetween(ListNode head, int B, int C) {

        /*
         * REVERSE LINKED LIST BETWEEN B and C - Dummy node approach
         *
         * STRATEGY: 3 steps
         *   1. reach node just before B (prevNode)
         *   2. reverse B to C (standard 3-pointer reverse)
         *   3. reconnect both ends
         *
         * EXAMPLE: 1→2→3→4→5, B=2, C=4
         *   prevNode=1, reverse [2,3,4] → 4→3→2
         *   prevNode.next.next = curr   → 2(tail) → 5
         *   prevNode.next = prev        → 1 → 4(head)
         *   result: 1→4→3→2→5
         *
         * WHY DUMMY: handles edge case where B=1 (no real node before head)
         *            prevNode = dummy → dummy.next = prev works uniformly
         *
         * Time: O(N)  Space: O(1)
         */
        if (head == null || B == C) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevNode = dummy; // node just before position B

        // step 1: reach node just before B
        for (int i = 1; i < B; i++) {
            prevNode = prevNode.next;
        }

        // step 2: reverse from B to C
        ListNode curr = prevNode.next; // node at position B
        ListNode prev = null;
        ListNode next = null;

        for (int i = 0; i <= C - B; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // step 3: connect back
        prevNode.next.next = curr; // B (now tail) -> node after C
        prevNode.next = prev;      // node before B -> C (now head)

        return dummy.next;
    }


    public RandomListNode createList(int[] values) {
        if (values == null || values.length == 0) return null;

        RandomListNode head = new RandomListNode(values[0]);
        RandomListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new RandomListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    // helper to set random pointers by index
    // randomIndices[i] = j means node[i].random = node[j]
    // randomIndices[i] = -1 means node[i].random = null
    public void setRandomPointers(RandomListNode head, int[] randomIndices) {
        // collect all nodes into a list first
        List<RandomListNode> nodes = new ArrayList<>();
        RandomListNode current = head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }

        // assign random pointers
        for (int i = 0; i < nodes.size(); i++) {
            int ri = randomIndices[i];
            nodes.get(i).random = (ri == -1) ? null : nodes.get(ri);
        }
    }

    public void printList(RandomListNode head) {
        while (head != null) {
            String random = (head.random == null) ? "null" : String.valueOf(head.random.label);
            System.out.println("label=" + head.label + ", random=" + random);
            head = head.next;
        }
    }

    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
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

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
