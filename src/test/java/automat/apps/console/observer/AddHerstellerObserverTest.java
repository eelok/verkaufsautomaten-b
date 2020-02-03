package automat.apps.console.observer;

import automat.apps.console.service.Printer;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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