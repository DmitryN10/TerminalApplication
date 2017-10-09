package terminal;

import junit.framework.TestCase;
import org.junit.Before;
import org.mockito.Mockito;
import pinValidator.PinValidator;
import terminalServer.NoConnection;
import terminalServer.NoFunsException;
import terminalServer.TerminalServer;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Дмитрий on 09.10.2017.
 */
public class TerminalImplTest extends TestCase {

    public void testCheckCurrentBalanceIfAllOk() throws Exception {
        PinValidator pinValidator = Mockito.mock(PinValidator.class);
        when(pinValidator.validatePin()).thenReturn(true);

        TerminalServer server = Mockito.mock(TerminalServer.class);
        when(server.toCheckCurrentBalance()).thenReturn(1000);

        MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);
        TerminalImpl terminal = new TerminalImpl(server,pinValidator,messagePrinter );
        terminal.toCheckCurrentBalance();
        verify(messagePrinter).printBalance(1000);

    }


    public void testCCBUncorrectPIN() throws Exception{
        PinValidator pinValidator = Mockito.mock(PinValidator.class);
        when(pinValidator.validatePin()).thenReturn(false);

        TerminalServer server = Mockito.mock(TerminalServer.class);
        when(server.toCheckCurrentBalance()).thenReturn(1000);

        MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);
        TerminalImpl terminal = new TerminalImpl(server,pinValidator,messagePrinter );
        terminal.toCheckCurrentBalance();
        verify(messagePrinter).printInvalidPinMessage();
    }

    public void testCCBUncorrectPin3TimesAndBlockTimeDidNotComeOut()  throws Exception{
        PinValidator pinValidator = Mockito.mock(PinValidator.class);
        when(pinValidator.validatePin()).thenReturn(false);

        TerminalServer server = Mockito.mock(TerminalServer.class);
        when(server.toCheckCurrentBalance()).thenReturn(100);

        MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);
        TerminalImpl terminal = new TerminalImpl(server,pinValidator,messagePrinter );
        terminal.toCheckCurrentBalance();
        terminal.toCheckCurrentBalance();
        terminal.toCheckCurrentBalance();
        verify(messagePrinter).printInvalidPinMessage3Times();

        terminal.toCheckCurrentBalance();
        verify(messagePrinter).printBlockTimeDidNotComeOut();
    }

    public void testBlockTimeDidNotComeOutAndAfter5seconds() throws Exception{
        PinValidator pinValidator = Mockito.mock(PinValidator.class);
        when(pinValidator.validatePin()).thenReturn(false);

        TerminalServer server = Mockito.mock(TerminalServer.class);
        when(server.toCheckCurrentBalance()).thenReturn(10);

        MessagePrinter messagePrinter = Mockito.mock(MessagePrinter.class);
        TerminalImpl terminal = new TerminalImpl(server,pinValidator,messagePrinter );
        terminal.toCheckCurrentBalance();
        terminal.toCheckCurrentBalance();
        terminal.toCheckCurrentBalance();
        verify(messagePrinter).printInvalidPinMessage3Times();
        terminal.toCheckCurrentBalance();
        verify(messagePrinter).printBlockTimeDidNotComeOut();
        sleep(6000);
        terminal.toCheckCurrentBalance();
        verify(messagePrinter).printBlockTimeDidNotComeOut();
    }


}