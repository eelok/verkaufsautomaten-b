package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

class AddKuchenInputListenerTest {
    private AddKuchenInputListener addKuchenInputListener;
    private KuchenParser kuchenParser;
    private Automat automat;
    private Printer printer;

    @BeforeEach
    void setUp() {
        automat = mock(Automat.class);
        kuchenParser = mock(KuchenParser.class);
        printer = mock(Printer.class);
        addKuchenInputListener = new AddKuchenInputListener(kuchenParser, automat, printer);
    }


    @Test
    void should_call_addkuchen() {
        String someText = "text";
        Object source = new Object();
        InputEvent event = new InputEvent(source, someText);
        Kuchen kuchen = mock(KuchenImplementation.class);
        when(kuchenParser.getKuchenInfo(event.getText())).thenReturn(kuchen);

        addKuchenInputListener.onInputEvent(event);

        verify(automat).addKuchen(eq(kuchen), any(LocalDateTime.class));
    }

    @Test
    void should_call_println_when_automat_is_full() {
        when(automat.getPlatzImAutomat()).thenReturn(0);
        InputEvent event = mock(InputEvent.class);
        String text = "some text";
        when(event.getText()).thenReturn(text);
        Kuchen kuchen = mock(KuchenImplementation.class);
        when(kuchenParser.getKuchenInfo(event.getText())).thenReturn(kuchen);
        when(automat.addKuchen(any(), any())).thenThrow(new AutomatIsFullException("message"));

        addKuchenInputListener.onInputEvent(event);

        verify(printer).println("Can not add kuchen, reason: message");
    }
    @Test
    void should_call_println_when_no_manufacturer(){
        when(automat.getPlatzImAutomat()).thenReturn(1);
        InputEvent event = mock(InputEvent.class);
        String text = "some text";
        when(event.getText()).thenReturn(text);
        List<Hersteller> herstellerList = mock(List.class);
        when(herstellerList.size()).thenReturn(0);
        when(kuchenParser.getKuchenInfo(event.getText())).thenReturn(mock(KuchenImplementation.class));
        when(automat.getHerstellerList()).thenReturn(herstellerList);

        when(automat.addKuchen(any(), any())).thenThrow(new IllegalArgumentException("no such manufacturer"));

        addKuchenInputListener.onInputEvent(event);

        verify(printer).println("The Kuchen could not be added, reason: no such manufacturer");
    }

}