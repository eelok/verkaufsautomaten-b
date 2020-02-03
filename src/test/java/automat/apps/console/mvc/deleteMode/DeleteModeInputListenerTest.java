package automat.apps.console.mvc.deleteMode;

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
class DeleteModeInputListenerTest {

    @Mock
    private Automat automat;
    @Mock
    private Printer printer;
    @Mock
    private ConsoleReader consoleReader;
    @Mock
    private InputEvent event;

    DeleteModeInputListener deleteModeInputListener;

    @BeforeEach
    void setUp(){
        deleteModeInputListener = new DeleteModeInputListener(automat, printer, consoleReader);
    }

    @Test
    void should_call_consoleReader_methods(){
        when(event.getText()).thenReturn(":d");
        deleteModeInputListener.onInputEvent(event);

        verify(consoleReader).start();
        verify(consoleReader).setHandler(any(InputEventHandler.class));
    }

    @Test
    void should_call_printlin(){
        when(event.getText()).thenReturn(":d");
        deleteModeInputListener.onInputEvent(event);

        verify(printer).println("delete mode active");
        verify(printer).println("Expected input: name of manufacturer / f<fachnummer>");
    }

}