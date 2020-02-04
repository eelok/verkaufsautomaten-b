package automat.net.client.mode.addModeNet;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.apps.console.service.Printer;
import automat.net.client.connection.DataSender;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.beans.EventHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddModeInputListenerNetTest {
    @Mock
    private Printer printer;
    @Mock
    private ConsoleReader consoleReader;
    @Mock
    private DataSender dataSender;

    @Test
    void should_call_println(){
        InputEvent event = mock(InputEvent.class);
        when(event.getText()).thenReturn(":a");

        AddModeInputListenerNet addModeInputListenerNet = new AddModeInputListenerNet(printer, consoleReader, dataSender);

        addModeInputListenerNet.onInputEvent(event);

        verify(printer).println("add mode active");
        verify(printer).println(
                "Input example:\n" +
                "Manufacturer: Alex\n" +
                "Kuchen: Kuchen 8.8 Alex Erdnuss 980 48\n"+
                "Kuchen: Kremkuchen 2.5 Alex Gluten,Haselnuss 1400 24 Sahne\n" +
                "Kuchen: Obstkuchen 5.0 Alex Gluten,Haselnuss,Sesamsamen 1000 36 Heidelbeeren\n"+
                "Kuchen: Obsttorte 10.0 Alex Gluten,Haselnuss,Erdnuss 550 25 Heidelbeeren Butter\n"+
                ":q -back to main menu"
        );
        verify(consoleReader).setHandler(any(InputEventHandler.class));
        verify(consoleReader).start();
    }

}