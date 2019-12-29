package automat.apps.console.observer;

import automat.apps.console.Printer;
import automat.mainlib.Automat;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteHerstellerObserverTest {


    @Test
    void should_call_method_println(){
        Automat automat = mock(Automat.class);
        Printer printer = mock(Printer.class);
        String message = "was called";
        when(automat.getMessage()).thenReturn(message);
        DeleteHerstellerObserver deleteHerstellerObserver = new DeleteHerstellerObserver(automat, printer);

        deleteHerstellerObserver.aktualisiere();
        verify(printer).println(String.format("Manufacturer %s was deleted", automat.getMessage()));
    }

}