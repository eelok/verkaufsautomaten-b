package automat.apps.console.mvc.addMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.mainlib.Automat;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.HerstellerImplementation;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddManufacturerInputListenerTest {

    private AddManufacturerInputListener addManufacturerInputListener;
    @Mock
    private  Automat automat;
    @Mock
    private Printer printer;

    @BeforeEach
    void setUp(){
        addManufacturerInputListener = new AddManufacturerInputListener(automat, printer);
    }

    @Test
    void should_be_no_interaction_when_user_input_null(){
        InputEvent event = mock(InputEvent.class);

        when(event.getText()).thenReturn(null);

        addManufacturerInputListener.onInputEvent(event);

        verifyNoInteractions(automat);
    }

    @Test
    void should_call_addHersteller(){
        InputEvent event = mock(InputEvent.class);
        String herstellerName = "manufacturer: alex";

        when(event.getText()).thenReturn(herstellerName);

        addManufacturerInputListener.onInputEvent(event);

        verify(automat).addHersteller(eq(new HerstellerImplementation("alex")));
    }

    @Test
    void should_call_println_add_existing_hersteller(){
        InputEvent event = mock(InputEvent.class);
        String text = "manufacturer: alex";

        when(event.getText()).thenReturn(text);
        when(automat.addHersteller(new HerstellerImplementation("alex"))).thenThrow(new ManufacturerExistException("Manufacturer already exists"));

        addManufacturerInputListener.onInputEvent(event);

        verify(printer).println("Manufacturer already exists");
    }

    @Test
    void should_call_println_when_no_name(){
        InputEvent event = mock(InputEvent.class);
        String text = "manufacturer:";

        when(event.getText()).thenReturn(text);
        when(automat.addHersteller(new HerstellerImplementation(""))).thenThrow(new IllegalArgumentException("Name is empty, add name"));

        addManufacturerInputListener.onInputEvent(event);

        verify(printer).println("Name is empty, add name");
    }
}