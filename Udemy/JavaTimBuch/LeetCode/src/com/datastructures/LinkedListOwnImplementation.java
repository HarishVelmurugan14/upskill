package com.datastructures;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinkedListOwnImplementation {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert("Harish");
        list.insert("Binu");
        list.insert("Guru");
        list.insert("Vikki");
        //    list.insert(3,"Bala");
        list.insertAtStart("Gokul");
        list.insert(0, "Bala");
        list.show();
        System.out.println("SpCE -> ");
        list.delete(0);
        list.show();
    }

//    public static void main(String[] args) {
//        LocalDate fromDate= LocalDate.parse("2023-01-01");
//        LocalDate toDate= LocalDate.parse("2023-01-21");
//        Set<YearMonth> yearMonthSet = Stream.iterate(fromDate, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(fromDate, toDate.plusDays(1)))
//                .map(date -> localDateToYearMonth.apply(date)).collect(Collectors.toSet());
//        for(YearMonth yearMonth : yearMonthSet) {
//            String tableName = "downloads_" + yearMonthToString.apply(yearMonth);
//            System.out.println(tableName);
//        }
//    }
//    public static Function<LocalDate, YearMonth> localDateToYearMonth = localDate ->
//            YearMonth.of(localDate.getYear(), localDate.getMonthValue());
//
//    public static Function<YearMonth, String> yearMonthToString = yearMonth ->
//            dateValueToString(yearMonth.getYear(), yearMonth.getMonthValue());
//    public static String dateValueToString(Integer year, Integer month) {
//        String monthString = month < 10 ? "0" + month : String.valueOf(month);
//        return year + monthString;
//    }

}

class LinkedList {
    Node head;

    public void insert(String data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        if (head == null) {
            head = node;
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
    }


    public void insert(int position, String data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        boolean positionValid = false;
        Node n = head;
        if (position == 0) {
            insertAtStart(data);
        } else {
            for (int i = 0; i < position - 1; i++) {
                n = n.next;
                if (i == position - 2 && n != null) {
                    positionValid = true;
                }
            }
            if (positionValid) {
                node.next = n.next;
                n.next = node;
            } else {
                System.out.println("Invalid Position");
            }
        }
    }

    public void insertAtStart(String data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }


    public void delete(int position) {
        Node n = head;
        if (position == 0) {
            head = n.next;
        } else {

            for (int i = 0; i < position - 1; i++) {
                n = n.next;
            }
            n.next = n.next.next;
        }
    }

    public void show() {
        Node n = head;
        while (n.next != null) {
            System.out.println(n.data);
            n = n.next;
        }
        System.out.println(n.data);
    }

}

class Node {
    String data;
    Node next;
}
