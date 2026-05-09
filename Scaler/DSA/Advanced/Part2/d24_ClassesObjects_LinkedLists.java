package DSA.Advanced.Part2;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 26-02-2025
 * @since 26-02-2025
 */
public class d24_ClassesObjects_LinkedLists {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d24_ClassesObjects_LinkedLists classObject = new d24_ClassesObjects_LinkedLists();

        /*
         * LINKED LIST - Core Operations
         *
         * ListNode: val + next pointer
         *
         * UTILITIES:
         *   createList(arr)              → builds list from array, returns head
         *   printList(head)              → prints val -> val -> null
         *   insertAtLast(head)           → traverses to tail, appends node(100)
         *   insertAtIndex(head, i, val)  → i=0: new head | i>0: wire prev.next → new → old
         *   deleteAtIndex(head, i)       → i=0: return head.next | i>0: skip node via prev.next = prev.next.next
         *
         * EDGE CASES HANDLED:
         *   null head, index=0, index out of bounds
         */

        ListNode list = createList(new int[]{3, 5, 1, 7, 8, 2, 5, 3});

        ListNode head = list;
        insertAtLast(head);
        insertAtIndex(head, 3, 100);
        printList(head);
        deleteAtIndex(head, 1);


    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    public void bruteForce() {
        // Complexity : Time : [  ]
        // Complexity : Space : [  ]


    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print("null");
    }

    public static ListNode createList(int[] values) {
        if (values == null || values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    public static ListNode insertAtLast(ListNode head) {
        if (head == null) return new ListNode(100); // ✅ null check

        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new ListNode(100);
        return head;
    }

    public static ListNode insertAtIndex(ListNode head, int index, int val) {
        if (index < 0) return head;

        if (index == 0) {                   // ✅ insert at head
            ListNode newNode = new ListNode(val);
            newNode.next = head;
            return newNode;
        }

        ListNode current = head;
        int pos = 0;
        while (current != null) {
            if (pos == index - 1) {
                ListNode temp = current.next;
                current.next = new ListNode(val);
                current.next.next = temp;
                return head;               // ✅ stop after inserting
            }
            pos++;
            current = current.next;
        }

        System.out.println("Index out of bounds"); // ✅ bounds check
        return head;
    }

    public static ListNode deleteAtIndex(ListNode head, int index) {
        if (head == null) return null;
        if (index == 0) return head.next; // delete head

        ListNode current = head;
        int pos = 0;
        while (current != null && current.next != null) {
            if (pos == index - 1) {
                current.next = current.next.next; // skip node
                return head;
            }
            pos++;
            current = current.next;
        }
        System.out.println("Index out of bounds");
        return head;
    }



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message) {
        printHelper.print(message, "");
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
