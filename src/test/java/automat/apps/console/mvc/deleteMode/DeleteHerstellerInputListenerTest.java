package automat.apps.console.mvc.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
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

    @Test
    void should_call_deleteHersteller(){
        DeleteHerstellerInputListener deleteHerstellerInputListener = new DeleteHerstellerInputListener(automat, printer);
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn("alex");

        deleteHerstellerInputListener.onInputEvent(event);

        verify(automat).deleteHersteller(any());
    }

    @Test
    void should_call_println(){
        DeleteHerstellerInputListener deleteHerstellerInputListener = new DeleteHerstellerInputListener(automat, printer);
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn("alex");

        doThrow(new IllegalArgumentException("Hersteller does not exist")).when(automat).deleteHersteller(any(String.class));

        deleteHerstellerInputListener.onInputEvent(event);

        verify(printer).println("Hersteller does not exist");
    }


}