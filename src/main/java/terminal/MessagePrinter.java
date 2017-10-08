package terminal;

/**
 * Created by Дмитрий on 08.10.2017.
 */
public class MessagePrinter {

    private static void printMessage(String message){
        System.out.println(message);
    }

    public static void printBalance(int balance) {
        System.out.println("Current balance is " + balance);
    }

    public static void printNoConnectionMessage(){
        printMessage("No connection");
    }

    public static void printNoFunsMessage(){
        System.out.println("No funs");
    }

    public static void printInvalidPinMessage(){
        System.out.println("Inavalid pin");
    }

    public static void printCardIsLockedMessage(){
        System.out.println("Card is locked");
    }

    public static void printSumMustBeDevisibleBy100(){
        System.out.println("Sum must be multiple by 100");
    }

    public static void printInvalidPinMessage3Times(){
        System.out.println("You entered the password 3 times incorrectly, your card is locked for 5 seconds.");
    }
    public static void ptintBlockTimeDidNotComeOut(){
        System.out.println("The blocking time didn't come out");
    }

}
