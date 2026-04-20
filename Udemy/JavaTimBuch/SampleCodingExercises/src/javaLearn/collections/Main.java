package javaLearn.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Theatre nationalTheatre = new Theatre("nationalTheatre", 6, 4);
        nationalTheatre.bookTickets("C03", 10);
       // nationalTheatre.getSeats();

        List<Theatre.Seat> seatCopy = new ArrayList<>(nationalTheatre.getSeats());
        Collections.sort(seatCopy, Theatre.PRICE_ORDER);
        printList(seatCopy);

    }


    public static void printListOld(List<TheatreInEff.Seat> list) {

        for (TheatreInEff.Seat seat : list) {
            System.out.print(seat.getSeatNumber() + " ");
        }
    }

    public static void printList(List<Theatre.Seat> list) {

        for (Theatre.Seat seat : list) {
            System.out.print(seat.getSeatNumber() + " ");
        }
    }

    public static void theatreInEff() {
        //min,max,shuffle,swap,sort,reverse,compareTo,copy

        TheatreInEff vidhyaTheatreInEff = new TheatreInEff("vidhyaTheatre", 5, 3);
        // Taking shallow copy
        ArrayList<TheatreInEff.Seat> seatCopy = new ArrayList<>(vidhyaTheatreInEff.seats);
        vidhyaTheatreInEff.bookTickets("A03");
        System.out.println("Is Available for booking? -> " + seatCopy.get(2).isReserved());
        // Even though booking is done in original seats changes inflicted in seats copy as both use same memory space

        Collections.sort(vidhyaTheatreInEff.seats);
        System.out.println("\nPrinting Seats");
        printListOld(vidhyaTheatreInEff.seats);

        Collections.reverse(seatCopy);
        System.out.println("\nPrinting SeatsCopy");
        printListOld(seatCopy);

        // But act as a different arraylist

        Collections.shuffle(seatCopy);
        System.out.println("\nPrinting Shuffle Seat Copy");
        printListOld(seatCopy);
        // Shuffling seats

        TheatreInEff.Seat minSeat = Collections.min(seatCopy);
        TheatreInEff.Seat maxSeat = Collections.max(seatCopy);
        System.out.println("\nMin Seat -> " + minSeat.getSeatNumber());
        System.out.println("Max Seat -> " + maxSeat.getSeatNumber());
        // Even though shuffled it uses compareTo method to identify the min and max

        sortList(seatCopy);
        System.out.println("Printing sortList Seat Copy");
        printListOld(seatCopy);

        ArrayList newList = new ArrayList(vidhyaTheatreInEff.seats.size());
        Collections.copy(newList, vidhyaTheatreInEff.seats);
        //Assigned number of memory required but actually it needs value in places of newList
    }

    public static void sortList(List<? extends TheatreInEff.Seat> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size() - 1; j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    Collections.swap(list, i, j);
                }
            }
        }
    }
}
