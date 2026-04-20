package javaLearn.classConcept.Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //loadObject into memory

        // Save in screen

        ISaveable fragger = new Player("Harish", 12, 88, "M416");
        saveObject(fragger);
        System.out.println(fragger);
        //loadObject(fragger);
        //saveObject(fragger);

        ISaveable zombie = new Zombie("Satan", 40, 100);
        //Generic declaration is always ideal list is better than arraylist
        saveObject(zombie);
        loadObject(zombie);
        saveObject(zombie);
        //Cant get class specific methods from interface need to cast object as class object
        System.out.println(((Zombie) zombie).getName());
        System.out.println(zombie);

    }

    public static void loadObject(ISaveable objectToLoad) {
        List<String> values = readValues();
        objectToLoad.read(values);
    }

    public static void saveObject(ISaveable objectToSave) {
        int i = 0;
        System.out.println(objectToSave.write().size());
        while (i < objectToSave.write().size()) {
            System.out.println("Saving " + objectToSave.write().get(i) + " to storage device");
            i++;
        }
    }

    public static ArrayList<String> readValues() {
        ArrayList<String> values = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int index = 0;
        System.out.println("Choose \n 1 to enter a string \n 0 to quit");

        while (!quit) {
            System.out.println("Choose an option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter a String");
                    String stringInput = scanner.nextLine();
                    values.add(index, stringInput);
                    index++;
                    break;
            }
        }
        return values;
    }

}
