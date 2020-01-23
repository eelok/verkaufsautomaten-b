package automat.apps.console.observer;

import automat.apps.console.Printer;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class RemoveKuchenObserverTest {


    @Test
    void should_call_println(){
        Automat automat = mock(Automat.class);
        Printer printer = mock(Printer.class);
        RemoveKuchenObserver removeKuchenObserver = new RemoveKuchenObserver(automat, printer);

        removeKuchenObserver.aktualisiere();

        verify(printer).println(String.format("%s was deleted", automat.getMessage()));
    }


}