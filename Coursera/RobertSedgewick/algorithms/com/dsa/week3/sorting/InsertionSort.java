package RobertSedgewick.algorithms.com.dsa.week3.sorting;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class InsertionSort {
    public void definitions(){
        //Resources/InsertionSort.gif
    }
    public void sort(Comparable[] array){
        sort(array, 0, array.length-1);
//        NOTE : Best Possible sort for smaller number below 1Lakh : O(N^2)
//        Loop from low +1 -> high
//        check i with all the previous elements and swapping it unless the criteria fails for smaller case
//        dont check beyond 0
        /*
        * Insert pannanum
        * Yarra - low class la irka orthana high class ku equal ah
        * low class the current veedu; high - change ahapora pinnadi veedu
        * eppo varaikum mathnum highclass karanga ellam low class ah vida edathla kammi ahra varaikum. Appo current ah adhihama irkanhga
        * ovvoru change kum oru adi vangnum
        *
        * //Check Resources/ folder
        * */
    }

    public void sort(Comparable[] a, int low, int high){

        for(int i=low+1; i<=high;i++){
            int currentPos = i;
            int changePlace = i - 1;
            while(a[changePlace].compareTo(a[currentPos]) > 0){
                SortingBaseClass.swap(a, currentPos, changePlace);
                changePlace--;
                currentPos--;
                if(changePlace < low){
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
