package automat.apps.console.mvc.addMode;

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

    private AddModeInputListener addModeInputListener;
    @BeforeEach
    void setUp(){
        addModeInputListener = new AddModeInputListener(automat, printer, consolereader);
    }

    @Test
    void should_call_consoleReader_methods(){
        when(event.getText()).thenReturn(":a");

        addModeInputListener.onInputEvent(event);

        verify(consolereader).setHandler(any(InputEventHandler.class));
        verify(consolereader).start();
    }

    @Test
    void should_call_println(){
        when(event.getText()).thenReturn(":a");

        addModeInputListener.onInputEvent(event);

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
    }
}