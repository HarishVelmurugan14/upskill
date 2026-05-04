package Resources.Utilities;

import java.util.Arrays;
import java.util.Random;

public class GenericUtil {

    private TimerClass timer;

    public GenericUtil() {
       timer = new TimerClass();
    }

    @SuppressWarnings("ReassignedVariable")
    public int[] IntegerArrayGenerator(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        ShuffleArray(new int[][]{array});
        return array;
    }

    @SuppressWarnings("ReassignedVariable")
    public <T> void ShuffleArray(T[] array) {
        int n = array.length;
        Random rand = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Swap array[i] and array[j]
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public <T> void PrintArray(T[] array){
        System.out.println(Arrays.toString(array));
    }

    public void ElapsedTime(){
        timer.elapsedTime();
    }
}
