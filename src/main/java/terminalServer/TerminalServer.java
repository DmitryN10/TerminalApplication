package terminalServer;

import pinValidator.InvalidPinException;

/**
 * Created by Дмитрий on 08.10.2017.
 */
public interface TerminalServer {
    int toCheckCurrentBalance() throws NoConnection, InvalidPinException;
    void toWithdrawCash()  throws NoConnection, NoFunsException, InvalidPinException;
}
