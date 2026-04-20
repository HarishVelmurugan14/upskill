@SuppressWarnings("ALL")
public class sampleCodes {

    public static void main(String[] args) {
        // Speed convertor
        // printConversion(0.0);
        // Kilo bytes to Mega bytes
        // printMegaBytesAndKiloBytes(0);
        // wake up on dag barking
        // boolean toWakeUp = shouldWakeUp(true, 1);
    }

    public static void printDayOfTheWeek(int day){

    }

    public static void printEqual(int firstNum, int secondNum, int thirdNum) {
        if (firstNum < 0 || secondNum < 0 || thirdNum < 0) {
            System.out.println("Invalid Value");
        } else if (firstNum == secondNum && firstNum == thirdNum) {
            System.out.println("All numbers are equal");
        } else if (firstNum != secondNum && firstNum != thirdNum && secondNum != thirdNum) {
            System.out.println("All numbers are different");
        } else {
            System.out.println("Neither all are equal or different");
        }
    }

    public static boolean hasTeen(int firstNumber, int secondNumber, int thirdNumber) {
        return (firstNumber > 12 && firstNumber < 20) ||
                (secondNumber > 12 && secondNumber < 20) ||
                (thirdNumber > 12 && thirdNumber < 20);
    }

    public static boolean isTeen(int number) {
        return number > 12 && number < 20;
    }

    public static boolean shouldWakeUp(boolean barking, int hourOfDay) {
        if (barking) {
            if (hourOfDay < 0 || hourOfDay > 23) {
                return false;
            } else return hourOfDay < 8 || hourOfDay > 22;
        }
        return false;
    }

    public static void printMegaBytesAndKiloBytes(int kiloBytes) {
        if (kiloBytes < 0) {
            System.out.println("Invalid Value");
        } else {
            System.out.println(kiloBytes + " KB = " + kiloBytes / 1024 + " MB and " + kiloBytes % 1024 + " KB");
        }
    }

    public static long toMilesPerHour(double kilometersPerHour) {

        long returnValue = -1L;
        if (kilometersPerHour > 0) {
            double milePerHour = kilometersPerHour / 1.609;
            returnValue = Math.round(milePerHour);
        }
        return returnValue;
    }

    public static void printConversion(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            System.out.println("Invalid Value");
        } else {
            System.out.println(kilometersPerHour + " km/h = " + toMilesPerHour(kilometersPerHour) + " mi/h");
        }
    }

}
