package RobertSedgewick.algorithms.com.dsa.week3.sorting;

@SuppressWarnings("ALL")
public class MergeSort {

    public final int INSERTION_CUTOFF = 100000;
    public void sort(Comparable[] a, Comparable[] aux, int low, int high){
        if(high <= low + INSERTION_CUTOFF -1){
            new InsertionSort().sort(a, low, high);
            return;
        }
        int mid = low + (high-low)/2;
        sort(a, aux, low, mid);
        sort(a, aux, mid+1, high);
        merge(a, aux, low, mid, high);
    }

    public void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high){
        for(int k=low; k<=high; k++){
            aux[k] = a[k];
        }
        int i=low;
        int j=mid+1;
        for(int k = low; k<=high;k++){
           if(i > mid){
               a[k] = aux[j];
               j++;
           } else if (j > high) {
               a[k] = aux[i];
               i++;
           }else if(aux[i].compareTo(aux[j]) <0){
               a[k] = aux[i];
               i++;
           }else{
               a[k] = aux[j];
               j++;
           }
        }
    }

}
