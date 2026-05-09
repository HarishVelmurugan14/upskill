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
        d24_classesObjects_linkedLists.printList(d25_linkedList_problems.deleteDuplicates(headA));
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
