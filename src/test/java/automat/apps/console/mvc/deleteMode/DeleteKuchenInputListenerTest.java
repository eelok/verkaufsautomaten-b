package automat.apps.console.mvc.deleteMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteKuchenInputListenerTest {

    @Mock
    private Automat automat;
    @Mock
    private Printer printer;

    @Mock
    private InputEvent event;

    private DeleteKuchenInputListener deleteKuchenInputListener;

    @BeforeEach
    void setUp(){
        deleteKuchenInputListener = new DeleteKuchenInputListener(automat, printer);
    }

    @Test
    void should_be_no_interaction(){
        when(event.getText()).thenReturn(null);
        deleteKuchenInputListener.onInputEvent(event);

        verifyNoInteractions(automat);
    }

    @Test
    void should_call_removeKuchen(){
        when(event.getText()).thenReturn("kuchen: 1");
        int fachNum = 1;

        deleteKuchenInputListener.onInputEvent(event);

        verify(automat).removeKuchenFromAutomat(fachNum);
    }

    @Test
    void should_call_pringln_when_fach_contains_not_number(){
        when(event.getText()).thenReturn("kuchen: f1");

        doThrow(new NumberFormatException("Fachnummer should be a number")).when(automat).removeKuchenFromAutomat(anyInt());

        deleteKuchenInputListener.onInputEvent(event);

        verify(printer).println("Fachnummer should be a number");
    }

    @Test
    void should_call_pringln_when_fach_does_not_exist(){
        when(event.getText()).thenReturn("kuchen: 11");

        doThrow(new IllegalArgumentException("fachnummer does not exist")).when(automat).removeKuchenFromAutomat(anyInt());

        deleteKuchenInputListener.onInputEvent(event);

        verify(printer).println("fachnummer does not exist");
    }

}