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
    double lastBlockTime;


    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public void toCheckCurrentBalance() {
        if (this.operationIaAllowed()) {
            try {
                MessagePrinter.printBalance(this.server.toCheckCurrentBalance());
            } catch (NoConnection e) {
                MessagePrinter.printNoConnectionMessage();
            } catch (InvalidPinException e) {
                MessagePrinter.printInvalidPinMessage();
            }
        }
    }

    @Override
    public void toWithdrawCash(int money) {
        if (this.operationIaAllowed()) {
            if (money % 100 != 0) {
                MessagePrinter.printSumMustBeDevisibleBy100();
            } else {
                try {
                    this.server.toWithdrawCash();
                } catch (NoConnection e) {
                    MessagePrinter.printNoConnectionMessage();
                } catch (NoFunsException e) {
                    MessagePrinter.printNoFunsMessage();
                } catch (InvalidPinException e) {
                    System.out.println(e);
                    MessagePrinter.printInvalidPinMessage();
                }
            }
        }
    }



    private boolean operationIaAllowed(){
        if (System.currentTimeMillis() - lastBlockTime < 5000)  {
            System.out.println("Время не прошло");
            return false;
        }
        if (!this.pinValidator.validatePin()) {
            if (failedAttempts < 2) {
                failedAttempts++;
                System.out.println("Введен неверный пароль");
            } else {
                System.out.println("Введен неправильный пароль,  вы заблокированы на 5 сек");
                failedAttempts = 0;
                lastBlockTime = System.currentTimeMillis();
            }
            return false;
        }
        return true;

    }
}
