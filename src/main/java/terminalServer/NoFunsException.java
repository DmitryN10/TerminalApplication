package terminalServer;

/**
 * Created by Дмитрий on 08.10.2017.
 */
public class NoFunsException extends Exception {
    public NoFunsException() {
    }

    public NoFunsException(String message) {
        super(message);
    }

    public NoFunsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFunsException(Throwable cause) {
        super(cause);
    }

    public NoFunsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
