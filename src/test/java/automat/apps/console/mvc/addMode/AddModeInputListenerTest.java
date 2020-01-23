package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddModeInputListenerTest {

    @Mock
    private Automat automat;
    @Mock
    private Printer printer;
    @Mock
    private InputEvent event;
    @Mock
    private ConsoleReader consolereader;

    @Test
    void should_call_consoleReader_methods(){
        AddModeInputListener addModeInputListener = new AddModeInputListener(automat, printer, consolereader);
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn(":a");

        addModeInputListener.onInputEvent(event);

        verify(consolereader).setHandler(any(InputEventHandler.class));
        verify(consolereader).start();
    }

    @Test
    void should_call_println(){
        AddModeInputListener addModeInputListener = new AddModeInputListener(automat, printer, consolereader);
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn(":a");

        addModeInputListener.onInputEvent(event);

        verify(printer).println("add mode active");
        verify(printer).println("enter: manufacturer <name> | information about kuchen <Obstkuchen 2.5 Alex Gluten,Haselnuss 1400 24 Sahne>");
    }


}