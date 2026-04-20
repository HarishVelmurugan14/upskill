package bookRead;

public class ThreadConceptExtends {
    public static void main(String[] args) {
        new ThreadClassExtend();
        for (int i = 0;i<5;i++){
            System.out.println("Thread 1 -> "+i);
        }
    }
}

class ThreadClassExtend extends Thread{
    ThreadClassExtend()  {
       super("Thread 2");
        System.out.println("Thread 2 -> "+ this);
        start();
    }

    @Override
    public void run() {
        for (int i = 0;i<5;i++){
            System.out.println("Thread 2 -> "+i);
        }
    }
}
