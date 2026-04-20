package LLD.Part2.CreationalDesignPattern.Singleton;

public class DBConnectionPool2_MT_INF_SP {

    private static DBConnectionPool2_MT_INF_SP instance;

    private DBConnectionPool2_MT_INF_SP() {
        // Problems lies in critical code block
        // if allows multiple bombardment of threads so multiple object creations allowed as a result

        // How to enforce thread safety ?
        // Using synchronized block DBConnectionPool2.java:15
    }

    public synchronized static DBConnectionPool2_MT_INF_SP getInstance() {
        if(instance == null) {
            // CRITICAL CODE BLOCK
            instance = new DBConnectionPool2_MT_INF_SP();
        }
        return instance;
    }

    public void doSomething(){

    }
}
