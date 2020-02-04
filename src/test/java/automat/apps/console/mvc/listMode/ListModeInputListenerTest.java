package automat.apps.console.mvc.listMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListModeInputListenerTest {

    @Mock
    private Automat automat;
    @Mock
    private Printer printer;
    @Mock
    private ConsoleReader consoleReader;
    @Mock
    private InputEvent event;

    private ListModeInputListener listModeInputListener;

    @BeforeEach
    void setUp() {
        listModeInputListener = new ListModeInputListener(automat, printer, consoleReader);
    }

    @Test
    void should_call_println() {
        when(event.getText()).thenReturn(":l");

        listModeInputListener.onInputEvent(event);

        verify(printer).println("list mode active");
        verify(printer).println(
                "Input example:\n" +
                "manufacturer\n" +
                "kuchen\n" +
                ":q -back to main menu"
        );
    }

    @Test
    void should_call_consoleReader_methods() {
        when(event.getText()).thenReturn(":l");

        listModeInputListener.onInputEvent(event);

        verify(consoleReader).start();
        verify(consoleReader).setHandler(any(InputEventHandler.class));
    }


}