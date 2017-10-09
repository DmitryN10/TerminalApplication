package terminal;

/**
 * Created by Дмитрий on 08.10.2017.
 */
public class MessagePrinter {

    private void printMessage(String message){
        System.out.println(message);
    }

    public void printBalance(int balance) {
        System.out.println("Current balance is " + balance);
    }

    public void printNoConnectionMessage(){
        printMessage("No connection");
    }

    public void printNoFunsMessage(){
        System.out.println("No funs");
    }

    public void printInvalidPinMessage(){
        System.out.println("Inavalid pin");
    }

    public void printCardIsLockedMessage(){
        System.out.println("Card is locked");
    }

    public void printSumMustBeDevisibleBy100(){
        System.out.println("Sum must be multiple by 100");
    }

    public void printInvalidPinMessage3Times(){
        System.out.println("You entered the password 3 times incorrectly, your card is locked for 5 seconds.");
    }
    public void printBlockTimeDidNotComeOut(){
        System.out.println("The blocking time didn't come out");
    }

}
