package bookRead;

public class ThreadConceptRunnable {
    public static void main(String[] args) {
        MessageClass messageClass = new MessageClass();
        ThreadClass object2 = new ThreadClass("Thread 2", Thread.MIN_PRIORITY, messageClass, "HI");
        ThreadClass object3 = new ThreadClass("Thread 3", Thread.MAX_PRIORITY, messageClass, " Harish");
        ThreadClass object4 = new ThreadClass("Thread 4", Thread.NORM_PRIORITY - 2, messageClass, "How are you?");
        ThreadClass object5 = new ThreadClass("Thread 5", Thread.NORM_PRIORITY - 3, messageClass, "Mate");
        for (int i = 0; i < 5; i++) {
            // System.out.println("Thread 1 -> " + i);
        }
        try {
//            System.out.println("T2 " +  object2.thread.getPriority());
//            System.out.println("T3 " + object3.thread.getPriority());
//            System.out.println("T4 " + object4.thread.getPriority());
//            System.out.println("T5 " + object5.thread.getPriority());
            object2.thread.join();
            object3.thread.join();
            object4.thread.join();
            object5.thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Main Completed");
    }
}

class ThreadClass implements Runnable {

    Thread thread;
    MessageClass messageClass;
    String message;

    public ThreadClass(String threadName, int priority, MessageClass messageClass, String message) {
        thread = new Thread(this, threadName);
        thread.setPriority(priority);
        this.messageClass = messageClass;
        this.message = message;
        thread.start();
        // Passing this as first param means it has to call run from this object

    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            // System.out.println(thread.getName() + " -> " + i);
        }
        try {
            synchronized (messageClass) {
                messageClass.msg(message);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //   System.out.println(thread.getName() + " Completed");
    }
}

class MessageClass {
    void msg(String message) throws InterruptedException {
        System.out.print(" [ " + message);
        Thread.sleep(1000);
        System.out.println(" ] ");
    }

}
