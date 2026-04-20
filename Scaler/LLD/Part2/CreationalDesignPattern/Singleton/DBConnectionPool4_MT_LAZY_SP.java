package LLD.Part2.CreationalDesignPattern.Singleton;

public class DBConnectionPool4_MT_LAZY_SP {

    private static volatile DBConnectionPool4_MT_LAZY_SP instance;

    private DBConnectionPool4_MT_LAZY_SP() {

    }

    public static DBConnectionPool4_MT_LAZY_SP getInstance() {
        if(instance == null) {
            synchronized(DBConnectionPool4_MT_LAZY_SP.class) {
                if(instance == null) {
                    instance = new DBConnectionPool4_MT_LAZY_SP();
                }
            }
        }
        return instance;
    }

    public void doSomething(){

    }

    public void markedAsVolatile() {
        // Why volatile mark ?
        // Sequence of instruction may be obstructed
        // memory = allocate()
        // constructor(memory)
        // instance = memory || 2 and 3 will be reversely executed
    }
}
