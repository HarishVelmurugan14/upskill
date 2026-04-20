package javaLearn.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Theatre {

    private String theatreName;
    private List<Seat> seats = new ArrayList<>();
    //Collection extends list,set,ques,deque


   static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() > seat2.getPrice()) {
                return 1;
            } else if (seat1.getPrice() < seat2.getPrice()) {
                return -1;
            } else {
                return 0;
            }

        }
    };

    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;
        int lastRow = 'A' + numRows;

        for (char row = 'A'; row < lastRow; row++) {
            for (int seatNumberColumn = 1; seatNumberColumn <= seatsPerRow; seatNumberColumn++) {
                double price = 5.00;
                if (row < 'D') {
                    price = 10.00;
                }
                Seat seat = new Seat(row + String.format("%02d", seatNumberColumn), price);
                seats.add(seat);
            }
        }
    }


    public String bookTickets(String seatNumber, double price) {
        boolean bookingStatus = false;
        Seat requestedSeat = new Seat(seatNumber, price);
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

    public List<Seat> getSeats() {
        for (Seat seat : seats) {
            System.out.println(seat.getSeatNumber() + " Status = " + (seat.getReserved() ? "booked" : "open") + " Price - " + seat.getPrice());
            //System.out.println(seat);
        }
        return seats;
    }

    public void printRow(int numRows) {
        System.out.println("Entered");
        for (char row = 'A'; row < numRows; row++) {
            System.out.println(row);
        }
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
        private double price;
        private Boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
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



