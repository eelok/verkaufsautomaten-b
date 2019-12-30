package automat.apps.console.mvc;

import automat.apps.console.Printer;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class InfoCommandModeTest {

    @Test
    void should_call_println(){
        Printer printer = mock(Printer.class);
        InfoCommandMode infoCommandMode = new InfoCommandMode(printer);
        InputEvent event = mock(InputEvent.class);
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn("z");

        infoCommandMode.onInputEvent(event);

        verify(printer).println("Expected input: :a <input mode> | :l <list mode> | :d <delete mode>");
    }

}