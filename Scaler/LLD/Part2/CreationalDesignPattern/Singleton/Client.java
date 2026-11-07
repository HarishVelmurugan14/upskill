package LLD.Part2.CreationalDesignPattern.Singleton;

public class Client {
    public static void main(String[] args) {
        SingletonPattern sp = new SingletonPattern();

        sp.SingleThreadSingletonPattern(); // Good but only used for single threaded problems

        sp.InEfficient(); // Thread safe but not efficient | do not use

        sp.BasicEagerApproach(); // Thread safe with good efficiency || Good for web application with few cons
        sp.LazyApproach(); // Thread safe good eff || double checking singleton pattern
        sp.StaticHolderSingleton(); // Avoids variable load on class startup

        sp.EnumSingleton(); // Best possible singleton approach
    }
}
