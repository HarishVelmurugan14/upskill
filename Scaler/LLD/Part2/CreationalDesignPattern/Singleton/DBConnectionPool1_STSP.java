package LLD.Part2.CreationalDesignPattern.Singleton;

public class DBConnectionPool1_STSP {

    private static DBConnectionPool1_STSP instance;

    private DBConnectionPool1_STSP() {}

    public static DBConnectionPool1_STSP getInstance() {
        if(instance == null) {
            instance = new DBConnectionPool1_STSP();
        }
        return instance;
    }

    public static DBConnectionPool1_STSP getInstanceBad() {
        // Creates one object per instance call. Not resource reuse
        // Not singleton pattern
        return new DBConnectionPool1_STSP();
    }

    public void doSomething(){

    }
}
