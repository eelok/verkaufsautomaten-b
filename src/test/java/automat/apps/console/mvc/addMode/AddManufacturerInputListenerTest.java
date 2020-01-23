package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.HerstellerImplementation;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddManufacturerInputListenerTest {

    private AddManufacturerInputListener addManufacturerInputListener;
    private StringUtils stringUtils;
    private  Automat automat;
    private Printer printer;

    @BeforeEach
    void setUp(){
        stringUtils = mock(StringUtils.class);
        automat = mock(Automat.class);
        printer = mock(Printer.class);
        addManufacturerInputListener = new AddManufacturerInputListener(stringUtils, automat, printer);
    }

    @Test
    void should_be_no_interaction_when_usert_input_null(){
        InputEvent event = mock(InputEvent.class);
        when(event.getText()).thenReturn(null);

        addManufacturerInputListener.onInputEvent(event);

        verify(automat, times(0)).addHersteller(any());
    }

    @Test
    void should_call_addHersteller(){
        InputEvent event = mock(InputEvent.class);
        String herstellerName = "alex";

        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn(herstellerName);
        when(stringUtils.isOneWord(event.getText())).thenReturn(true);

        addManufacturerInputListener.onInputEvent(event);

        verify(automat).addHersteller(eq(new HerstellerImplementation(herstellerName)));
    }

    @Test
    void should_call_println(){
        InputEvent event = mock(InputEvent.class);
        when(event.getSource()).thenReturn(new Object());
        String text = "alex";
        when(event.getText()).thenReturn(text);
        when(stringUtils.isOneWord(text)).thenReturn(true);

        when(automat.addHersteller(new HerstellerImplementation("alex"))).thenThrow(new ManufacturerExistException("Manufacturer already exists"));

        addManufacturerInputListener.onInputEvent(event);

        verify(printer).println("Manufacturer already exists");
    }
}