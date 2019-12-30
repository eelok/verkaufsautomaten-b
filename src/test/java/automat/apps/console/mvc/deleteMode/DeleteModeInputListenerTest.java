package automat.apps.console.mvc.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteModeInputListenerTest {

    @Mock
    private Automat automat;
    @Mock
    private StringUtils stringUtils;
    @Mock
    private Printer printer;
    @Mock
    private ConsoleReader consoleReader;
    @Mock
    private InputEvent event;

    @Test
    void should_call_consoleReader_methods(){
        DeleteModeInputListener deleteModeInputListener = new DeleteModeInputListener(automat, stringUtils, printer, consoleReader);
        when(event.getText()).thenReturn(":d");
        deleteModeInputListener.onInputEvent(event);

        verify(consoleReader).start();
        verify(consoleReader).setHandler(any(InputEventHandler.class));
    }

    @Test
    void should_call_printlin(){
        DeleteModeInputListener deleteModeInputListener = new DeleteModeInputListener(automat, stringUtils, printer, consoleReader);
        when(event.getText()).thenReturn(":d");
        deleteModeInputListener.onInputEvent(event);

        verify(printer).println("delete mode active");
    }

}