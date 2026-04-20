package LLD.Part2.CreationalDesignPattern.Singleton;

public class DBConnectionPool5_MT_STATICHolder_SP {


    private DBConnectionPool5_MT_STATICHolder_SP() {
        // Problem :
        // application startup overhead. How to avoid ? move to method level
        // Holder only will be registered not loaded so variable is still unknown
        // Only when getInstance gets called it will be loaded.
    }

    private static class Holder {
        private static DBConnectionPool5_MT_STATICHolder_SP instance = new DBConnectionPool5_MT_STATICHolder_SP();
    }

    public static DBConnectionPool5_MT_STATICHolder_SP getInstance() {
        return Holder.instance;
    }

    public void doSomething(){

    }
}
