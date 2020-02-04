package automat.net.client.mode.listModeNet;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.apps.console.service.Printer;
import automat.net.client.connection.DataSender;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListModeInputListenerNetTest {

    @Mock
    private DataSender dataSender;
    @Mock
    private Printer printer;
    @Mock
    private ConsoleReader consoleReader;
    @Mock
    private InputEvent event;

    private ListModeInputListenerNet listModeInputListenerNet;

    @BeforeEach
    void setUp(){
        listModeInputListenerNet = new ListModeInputListenerNet(printer, consoleReader, dataSender);
    }

    @Test
    void should_call_println_and_consoleReader_methods() {
        when(event.getText()).thenReturn(":l");

        listModeInputListenerNet.onInputEvent(event);

        verify(printer).println("list mode active");
        verify(printer).println(
                "Input example:\n" +
                        "manufacturer\n" +
                        "kuchen\n" +
                        ":q -back to main menu"
        );
        verify(consoleReader).start();
        verify(consoleReader).setHandler(any(InputEventHandler.class));
    }
}