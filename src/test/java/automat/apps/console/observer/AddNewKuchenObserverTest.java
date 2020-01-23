package automat.apps.console.observer;

import automat.apps.console.Printer;
import automat.mainlib.Automat;
import automat.mainlib.kuchen.KremkuchenImplementation;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenImplementation;
import automat.mainlib.kuchen.ObstkuchenImplementation;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddNewKuchenObserverTest {

    private Automat automat;
    private Printer printer;
    AddNewKuchenObserver addNewKuchenObserver;

    @BeforeEach
    void setUp(){
        automat = mock(Automat.class);
        printer = mock(Printer.class);
        addNewKuchenObserver  = new AddNewKuchenObserver(automat, printer);
    }


    @Test
    void should_call_println_when_more_than_one_places_in_automat(){
        when(automat.getPlatzImAutomat()).thenReturn(4);
        String message = "message";
        when(automat.getMessage()).thenReturn(message);
        List<Kuchen> listWithKuchen = Collections.singletonList(mock(KremkuchenImplementation.class));
        when(automat.getAllEingelagertenKuchen()).thenReturn(listWithKuchen);

        addNewKuchenObserver.aktualisiere();

        verify(printer).println(String.format("%s was added", automat.getMessage()));

    }

    @Test
    void should_call_println_when_one_place_in_automat_left(){
        when(automat.getPlatzImAutomat()).thenReturn(4);

        List<Kuchen> listWithKuchen = Arrays.asList(
                mock(KremkuchenImplementation.class),
                mock(ObstkuchenImplementation.class),
                mock(KuchenImplementation.class)
        );
        when(automat.getAllEingelagertenKuchen()).thenReturn(listWithKuchen);

        addNewKuchenObserver.aktualisiere();
        
        verify(printer).println("There is only one place available left");
    }
}