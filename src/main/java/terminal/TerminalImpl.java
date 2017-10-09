package terminal;

import pinValidator.InvalidPinException;
import pinValidator.PinValidator;
import terminalServer.NoConnection;
import terminalServer.NoFunsException;
import terminalServer.TerminalServer;

/**
 * Created by Дмитрий on 08.10.2017.
 */
public class TerminalImpl implements Terminal {
    int failedAttempts = 0;
    long lastBlockTime;


    private final TerminalServer server;
    private final PinValidator pinValidator;
    //for Tests
    private final MessagePrinter messagePrinter;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator, MessagePrinter messagePrinter) {
        this.server = server;
        this.pinValidator = pinValidator;
        this.messagePrinter = messagePrinter;
    }

    @Override
    public void toCheckCurrentBalance() {
        if (this.operationIaAllowed()) {
            try {
                messagePrinter.printBalance(this.server.toCheckCurrentBalance());
            } catch (NoConnection e) {
                messagePrinter.printNoConnectionMessage();
            } catch (InvalidPinException e) {
                messagePrinter.printInvalidPinMessage();
            }
        }
    }

    @Override
    public void toWithdrawCash(int money) {
        if (this.operationIaAllowed()) {
            if (money % 100 != 0) {
                messagePrinter.printSumMustBeDevisibleBy100();
            } else {
                try {
                    this.server.toWithdrawCash();
                } catch (NoConnection e) {
                    messagePrinter.printNoConnectionMessage();
                } catch (NoFunsException e) {
                    messagePrinter.printNoFunsMessage();
                } catch (InvalidPinException e) {
                    System.out.println(e);
                    messagePrinter.printInvalidPinMessage();
                }
            }
        }
    }



    private boolean operationIaAllowed() {
        if (System.currentTimeMillis() - lastBlockTime < 5000) {
            messagePrinter.printBlockTimeDidNotComeOut();
            return false;
        } else {
            if (!this.pinValidator.validatePin()) {
                if (failedAttempts < 2) {
                    failedAttempts++;
                    messagePrinter.printInvalidPinMessage();
                } else {
                    if (failedAttempts == 2) {
                        messagePrinter.printInvalidPinMessage3Times();
                        failedAttempts = 0;
                        lastBlockTime = System.currentTimeMillis();
                    }
                }
                return false;
            }
            return true;

        }
    }
}
