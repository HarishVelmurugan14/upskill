package RobertSedgewick.algorithms.com.dsa.week4.sorting;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class HeapSort {
    private static int[] BINARY_HEAP_ARRAY = null;
    private static int MAX_INDEX = -1;
    private static int MAX_ALLOWED_INDEX = -1;

    public static void insert(int newElement){
        if(MAX_ALLOWED_INDEX == MAX_INDEX+1){
            resize(true);
        }
        MAX_INDEX++;
        BINARY_HEAP_ARRAY[MAX_INDEX] = newElement;
        swim(MAX_INDEX);
    }

    public static void put(int element, int index){
        if(index <= MAX_INDEX){
            BINARY_HEAP_ARRAY[index] = element;
            swim(index);
            sink(index);
        }else{
            System.out.println("ERROR");
        }
    }

    public static void delMax(){
        swap(BINARY_HEAP_ARRAY,0, MAX_INDEX);
        BINARY_HEAP_ARRAY[MAX_INDEX]=-1;
        MAX_INDEX--;
        sink(0);
        if(MAX_ALLOWED_INDEX / 4 == MAX_INDEX+1){
            resize(false);
        }
    }

    public static void swim(int currentKey){
        while(currentKey >= 0 && BINARY_HEAP_ARRAY[currentKey] > BINARY_HEAP_ARRAY[(currentKey-1)/2]){
            swap(BINARY_HEAP_ARRAY,currentKey, (currentKey-1)/2);
            currentKey = (currentKey-1)/2;
        }
    }

    public static  void sink(int currentKey){
        while(true){
            int swapPosition = swapPosition((2*currentKey)+1, (2*currentKey)+2);
            if(swapPosition !=-1 && BINARY_HEAP_ARRAY[currentKey] < BINARY_HEAP_ARRAY[swapPosition]){
                swap(BINARY_HEAP_ARRAY,currentKey, swapPosition);
            }else{
                break;
            }
            currentKey = swapPosition;
        }
    }

    public static int swapPosition(int i, int j){
        if(i>MAX_INDEX){
            return -1;
        }else if(j > MAX_INDEX) {
            return i;
        }else{
            return BINARY_HEAP_ARRAY[i] >= BINARY_HEAP_ARRAY[j] ? i :j;
        }
    }

    public static void sort(){
        swap(BINARY_HEAP_ARRAY,0, MAX_INDEX);
        MAX_INDEX--;
        sink(0);
        if(MAX_INDEX != -1){
            sort();
        }
    }

    /* NOTE CLIENT */
    public static void main(String[] args) {
        int[] a = {2,1,3,10,5,6,8,1,7};
        printArray(BINARY_HEAP_ARRAY, "Before - ");
        BinaryHeap(a); // CONVERTING TO MAX BINARY HEAP
        printArray(BINARY_HEAP_ARRAY, "After - ");
        put(9,7);
        printArray(BINARY_HEAP_ARRAY, "After Process- ");
        insert(4);
        printArray(BINARY_HEAP_ARRAY, "After Process- ");
        delMax(); // PRIORITY QUEUE
        printArray(BINARY_HEAP_ARRAY, "After Delete - ");
        int maxIndex = MAX_INDEX; // Maintain length of the used array
        sort(); // HEAP SORT
        System.out.println(BINARY_HEAP_ARRAY[maxIndex]);
        printArray(BINARY_HEAP_ARRAY, "After Sort - ");
    }

    public static void BinaryHeap(int[] array){
        MAX_ALLOWED_INDEX = array.length;
        BINARY_HEAP_ARRAY = new int[MAX_ALLOWED_INDEX];
        for(int element : array){
            insert(element);
        }
    }

    /* NOTE UTILITIES */
    public static void printArray(int[] array, String comment){
        System.out.println(comment + Arrays.toString(array));
    }

    public static void swap(int[] array, int i, int j){
        int k = array[i];
        array[i] = array[j];
        array[j] = k;
    }

    public static void resize(boolean increase){
        MAX_ALLOWED_INDEX = increase ? MAX_ALLOWED_INDEX * 2 : MAX_ALLOWED_INDEX / 2;
        int[] newArray = new int[MAX_ALLOWED_INDEX];
        int i = 0;
        for (int element : BINARY_HEAP_ARRAY) {
            if(i==MAX_ALLOWED_INDEX)break;
            newArray[i] = element;
            i++;
        }
        BINARY_HEAP_ARRAY = newArray;
    }

    //-------------------


}
