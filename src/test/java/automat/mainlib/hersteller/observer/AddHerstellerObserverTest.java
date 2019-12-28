package automat.mainlib.hersteller.observer;

import automat.mainlib.Automat;
import automat.mainlib.Printer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class AddHerstellerObserverTest {


    @Test
    void should_call_method_println(){
        Automat automat = mock(Automat.class);
        Printer printer = mock(Printer.class);
        String message = "newMessage";
        when(automat.getMessage()).thenReturn(message);

        AddHerstellerObserver addHerstellerObserver = new AddHerstellerObserver(automat, printer);
        addHerstellerObserver.aktualisiere();

        Mockito.verify(printer).println(String.format("Manufacturer %s was added", message));
    }

}