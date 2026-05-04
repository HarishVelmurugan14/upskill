package Resources.Utilities;

public class TimerClass {

    private final long startTime;
    private final long startNanoTime;

    public TimerClass() {
        this.startTime = System.currentTimeMillis();
        this.startNanoTime = System.nanoTime();
    }

    @SuppressWarnings("ReassignedVariable")
    public void elapsedTime(){
        long endTime = System.currentTimeMillis();
        System.out.println("\n====================\nElapsed time in seconds: " + (endTime - startTime)/1000.0);
        System.out.println("Elapsed time in milliseconds: " + (endTime - startTime));
        endTime = System.nanoTime();
        System.out.println("Elapsed time in microseconds: " + (endTime - startNanoTime)/1000.0);
        System.out.println("Elapsed time in nanoseconds: " + (endTime - startNanoTime) + "\n====================");
    }


}