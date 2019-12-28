package automat.mainlib.hersteller.observer;

import automat.mainlib.Automat;
import automat.mainlib.Printer;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteHarstellerObserverTest {


    @Test
    void should_call_method_println(){
        Automat automat = mock(Automat.class);
        Printer printer = mock(Printer.class);
        String message = "was called";
        when(automat.getMessage()).thenReturn(message);
        DeleteHarstellerObserver deleteHarstellerObserver = new DeleteHarstellerObserver(automat, printer);

        deleteHarstellerObserver.aktualisiere();
        verify(printer).println(String.format("Manufacturer %s was deleted", automat.getMessage()));
    }

}