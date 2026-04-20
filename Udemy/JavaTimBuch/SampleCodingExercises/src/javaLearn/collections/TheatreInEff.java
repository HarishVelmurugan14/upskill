package javaLearn.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheatreInEff {
    private String theatreName;
    public List<Seat> seats = new ArrayList<>();
    //Collection extends list,set,ques,deque

    public TheatreInEff(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;
        int lastRow = 'A' + numRows;

        for (char row = 'A'; row < lastRow; row++) {
            for (int seatNumberColumn = 1; seatNumberColumn <= seatsPerRow; seatNumberColumn++) {
                Seat seat = new Seat(row + String.format("%02d", seatNumberColumn));
                seats.add(seat);
            }
        }
    }

//    public String bookTickets(String seatNumber) {
//        boolean bookingStatus = false;
//        for (Seat seat : seats) {
//            System.out.println(".");
//            if (seat.getSeatNumber().equalsIgnoreCase(seatNumber)) {
//                bookingStatus = seat.isReserved();
//                if(bookingStatus){
//                    return "Tickets booked succesfully";
//                }
//                return "Ticket is already taken";
//            }
//        }
//        return "Select a valid seat";
//    }

    public String bookTickets(String seatNumber) {
        boolean bookingStatus = false;
        Seat requestedSeat = new Seat(seatNumber);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        //Binary search works only for sorted list
        String status = "";
        if (foundSeat > 0) {
            status = seats.get(foundSeat).isReserved() ? "Tickets booked succesfully" : "Ticket is already taken";
        } else {
            status = "Select a valid seat";
        }
        return status;
    }

    public void getSeats() {
        for (Seat seat : seats) {
            System.out.println(seat.getSeatNumber() + " Status = " + (seat.getReserved() ? "booked" : "open"));
            //System.out.println(seat);
        }
    }

    public void printRow(int numRows) {
        System.out.println("Entered");
        for (char row = 'A'; row < numRows; row++) {
            System.out.println(row);
        }
    }

    public void binarySearch(Seat seat) {
        binarySearch(seats, seat);
    }

    public void binarySearch(List<Seat> allSeats, Seat seat) {
        int low = 0;
        System.out.println(allSeats.size());
        int high = allSeats.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("Low - " + low + " Mid - " + mid + " High - " + high);
            Seat midVal = allSeats.get(mid);
            int result = midVal.compareTo(seat);
            System.out.println(result);
            if (result > 0) {
                //  System.out.println("Mid+1");
                low = mid + 1;
            } else if (result < 0) {
                //  System.out.println("Mid-1");
                high = mid - 1;
            } else {
                //    System.out.println("Rslt");
                System.out.println(allSeats.get(mid));
            }
        }
    }


public class Seat implements Comparable<Seat> {
    private String seatNumber;
    private Boolean reserved = false;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public boolean isReserved() {
        if (!this.reserved) {
            this.reserved = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean cancelTicket() {
        if (this.reserved) {
            this.reserved = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Seat seat) {
        return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
    }
}
}

