package LLD.Part2.CreationalDesignPattern.Singleton;

public class DBConnectionPool3_MT_EAGER_SP {

    private static final DBConnectionPool3_MT_EAGER_SP instance = new DBConnectionPool3_MT_EAGER_SP();

    private DBConnectionPool3_MT_EAGER_SP() {
        // how to avoid inefficiency ? without using synchronized and ensure thread safety
        // Load them as a static variable on the class level
    }

    public static DBConnectionPool3_MT_EAGER_SP getInstance() {
        return instance;
    }

    public void doSomething(){

    }
}
