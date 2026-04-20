package com.datastructures;

public class SingletonClass {
    private static SingletonClass single_instance = null;

    private SingletonClass() {
    }

    public static SingletonClass getInstance() {
        if (single_instance == null) {
            single_instance = new SingletonClass();
        }
        return single_instance;
    }

    public void printSomething(){
        System.out.println("Hello");
    }
}
class Main{
    public static void main(String[] args) {
        SingletonClass a = SingletonClass.getInstance();
        a.printSomething();
    }
}
