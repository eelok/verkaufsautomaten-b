package automat.apps.console.mvc.deleteMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteHerstellerInputListenerTest {

    @Mock
    private Automat automat;
    @Mock
    private Printer printer;
    @Mock
    private InputEvent event;

    private DeleteHerstellerInputListener deleteHerstellerInputListener;

    @BeforeEach
    void setUp() {
        deleteHerstellerInputListener = new DeleteHerstellerInputListener(automat, printer);
    }

    @Test
    void should_be_no_interaction_when_input_text_null() {
        when(event.getText()).thenReturn(null);
        deleteHerstellerInputListener.onInputEvent(event);

        verifyNoInteractions(automat);
    }

    @Test
    void should_call_deleteHersteller() {
        when(event.getText()).thenReturn("manufacturer: alex");

        deleteHerstellerInputListener.onInputEvent(event);

        verify(automat).deleteHersteller(any());
    }

    @Test
    void should_call_println_when_hersteller_was_not_deleted() {
        when(event.getText()).thenReturn("manufacturer: alex");

        doThrow(new IllegalArgumentException("Hersteller does not exist")).when(automat).deleteHersteller(any(String.class));

        deleteHerstellerInputListener.onInputEvent(event);

        verify(printer).println("Hersteller does not exist");
    }

    @Test
    void should_call_println_when_hersteller_when_name_empty() {
        when(event.getText()).thenReturn("manufacturer:");

        doThrow(new IllegalArgumentException("name is empty")).when(automat).deleteHersteller(any(String.class));

        deleteHerstellerInputListener.onInputEvent(event);

        verify(printer).println("name is empty");
    }

    @Test
    void should_call_println_when_hersteller_when_kuchen_from_hersteller_in_automat() {
        when(event.getText()).thenReturn("manufacturer: alex");

        doThrow(new IllegalArgumentException(
                "Hersteller %s may not be deletedn\n"+
                "reason: a kuchen from alex is in Automat"
        ))
                .when(automat).deleteHersteller(any(String.class));

        deleteHerstellerInputListener.onInputEvent(event);

        verify(printer).println(
                "Hersteller %s may not be deletedn\n"+
                "reason: a kuchen from alex is in Automat"
        );
    }


}