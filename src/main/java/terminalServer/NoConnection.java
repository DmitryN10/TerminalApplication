package terminalServer;

/**
 * Created by Дмитрий on 08.10.2017.
 */
public class NoConnection extends Exception {
    public NoConnection() {
    }

    public NoConnection(String message) {
        super(message);
    }

    public NoConnection(String message, Throwable cause) {
        super(message, cause);
    }

    public NoConnection(Throwable cause) {
        super(cause);
    }

    public NoConnection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
