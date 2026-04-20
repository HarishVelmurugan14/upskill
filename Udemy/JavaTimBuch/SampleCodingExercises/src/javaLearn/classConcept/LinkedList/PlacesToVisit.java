package javaLearn.classConcept.LinkedList;

import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings("rawtypes")
public class PlacesToVisit {
    public static void main(String[] args) {
        LinkedList<String> placesToVisit = new LinkedList<>();
        placesToVisit.add("Mumbai");
        placesToVisit.add("Goa");
        placesToVisit.add("Bangalore");
        placesToVisit.add("Hyderabad");
        placesToVisit.add("Chennai");
        placesToVisit.add("Coimbatore");
        placesToVisit.add("Cochin");
        placesToVisit.add("Kallakurichi");

        System.out.println("===============");
        printWithIterator(placesToVisit);

        placesToVisit.remove("Bangalore");
        System.out.println("===============");
        printWithoutIterator(placesToVisit);
    }


    private static void printWithIterator(LinkedList list) {
        Iterator entity = list.iterator();
        while (entity.hasNext()) {
            System.out.println(entity + " -> " + entity.next());
        }
    }

    private static void printWithoutIterator(LinkedList list) {
        int count = 0;
        for (Object entry : list){
            System.out.println(count + " -> " + entry);
            count++;
        }
    }
}

