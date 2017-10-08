package pinValidator;

/**
 * Thrown when PIN does not match card ID.
 *
 * Created by Дмитрий on 08.10.2017.
 */
public class InvalidPinException extends Exception{

    public InvalidPinException() {
    }

    public InvalidPinException(String message) {
        super(message);
    }

    public InvalidPinException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPinException(Throwable cause) {
        super(cause);
    }

    public InvalidPinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
