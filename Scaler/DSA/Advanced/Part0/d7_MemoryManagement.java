package DSA.Advanced.Part0;

import Resources.Utilities.PrintHelper;

/**
 * @author Harish Velmurugan
 * @last-modified 15-01-2025
 * @since 15-01-2025
 */
public class d7_MemoryManagement {


    private final PrintHelper printHelper = new PrintHelper();

    public static void main(String[] args) {

        // Inputs


        // Call Stack
        d7_MemoryManagement memoryManagement = new d7_MemoryManagement();

        memoryManagement.argumentPassedAsValue_main();
        memoryManagement.argumentPassedAsReference_main();
        memoryManagement.argumentPassedAsReferenceWithReInitialization_main();
        memoryManagement.argumentPassedAsReferenceWithReInitializationAndReturn_main();


    }

    /* Section : ----------------------------------- [ Approaches ] ------------------------------------ */

    private void argumentPassedAsValue_main() {
        int a = 10;
        argumentPassedAsValue_executor(a);
        print("Pass By Value : ", a);
        // Even though a is altered in executor it doesn't affect this 'a'. Both are different memory locations
        // Prints 10
    }

    private void argumentPassedAsValue_executor(int a) {
        a = 50;
    }

    private void argumentPassedAsReference_main() {
        int[] a = {10};
        argumentPassedAsReference_executor(a);
        print("Pass By Reference : ", a[0]);
        // a is altered in executor it is affected. Both points to same memory location say 4000
        // so affecting it once affects all the later callers
        // Prints 50
    }

    private void argumentPassedAsReference_executor(int[] a) {
        a[0] = 50;
    }

    private void argumentPassedAsReferenceWithReInitialization_main() {
        int[] a = {10, 20, 50};
        argumentPassedAsReferenceWithReInitialization_executor(a);
        print("Pass By Reference with Re Initialization : ", a[0]);
        // a is not altered in executor it is not affected and only the new initialized array gets altered.
        // Both points to different memory location say 4000 , 7000
        // so affecting new one won't affect all the later callers
        // Prints 10
    }

    private void argumentPassedAsReferenceWithReInitialization_executor(int[] a) {
        a = new int[1];
        a[0] = 100;
    }

    private void argumentPassedAsReferenceWithReInitializationAndReturn_main() {
        int[] a = {10, 20, 50};
        a = argumentPassedAsReferenceWithReInitializationAndReturn_executor(a);
        print("Pass By Reference with Re Initialization And Return: ", a[0]);
        // a is altered in executor as it is returned.
        //
        // Prints 100
    }

    private int[] argumentPassedAsReferenceWithReInitializationAndReturn_executor(int[] a) {
        a = new int[1];
        a[0] = 100;
        return a;
    }

    /* Section : ------------------------------- [ Specific Utilities ] ------------------------------- */



    /* Section : ------------------------------- [ Generic Utilities ] ------------------------------- */

    private void print(String message, int a) {
        printHelper.print(message, a);
    }

    /* Section : ------------------------------- [ Definition Resources ] ---------------------------- */

    private void definitions() {
        /*
         * Notebook_06012025 : Page no : 22
         *
         * Stack : Pile of Plates, inserted at top and removed from top
         * Call Stack : whenever a function is called memory is allocated on top of the call stack
         * Reference variable : If a variable doesn't actually store data and instead stores location of a data
         * Types of Memory :
         *   1. Stack - primitive data type and reference variable values are stored in stack memory
         *           ex : int a, int[] a : where location of a is stored
         *   2. Heap - Actual data of the reference variables pointing is present here
         *           ex - arrays, arraylist, classObject
         * */
    }

    private void links() {
        /**/
    }

    /* Section : --------------------------------------- [ End ] ------------------------------------ */


}
