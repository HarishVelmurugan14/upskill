package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

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

        d25_linkedList_problems.insertAtPosition(head, 3, 5); // Q3
        d25_linkedList_problems.deleteAtPosition(head, 5); // Q4

        d25_linkedList_problems.removeElements(head, 7); // LC203

//        d25_linkedList_problems.reverseALinkedListInPlaceInOneIteration(head);


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
