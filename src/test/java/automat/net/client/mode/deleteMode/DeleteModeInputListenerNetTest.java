package automat.net.client.mode.deleteMode;

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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteModeInputListenerNetTest {
    @Mock
    private Printer printer;
    @Mock
    private ConsoleReader consoleReader;
    @Mock
    private DataSender dataSender;

    private DeleteModeInputListenerNet deleteModeInputListenerNet;

    @BeforeEach
    void setUp(){
        deleteModeInputListenerNet = new DeleteModeInputListenerNet(printer, consoleReader, dataSender);
    }

    @Test
    void should_call_printr_and_consolReader_method(){
        InputEvent event = mock(InputEvent.class);
        when(event.getText()).thenReturn(":d");
        deleteModeInputListenerNet.onInputEvent(event);

        verify(printer).println("delete mode active");
        verify(printer).println(
                "Input example:\n" +
                "Manufacturer: Alex\n" +
                "Kuchen: <fachnummer>\n" +
                ":q -back to main menu");
        verify(consoleReader).setHandler(any(InputEventHandler.class));
        verify(consoleReader).start();
    }
}