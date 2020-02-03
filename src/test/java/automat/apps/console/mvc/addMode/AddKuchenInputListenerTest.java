package automat.apps.console.mvc.addMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenImplementation;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    void should_be_no_interaction_when_input_text_is_null(){
        InputEvent inputEvent = mock(InputEvent.class);
        when(inputEvent.getSource()).thenReturn(mock(Object.class));
        when(inputEvent.getText()).thenReturn(null);

        addKuchenInputListener.onInputEvent(inputEvent);

        verifyNoInteractions(automat);
    }

    @Test
    void should_be_no_interaction_when_kuchenParser_returns_null(){
        String notKuchen = "not kuchen";
        InputEvent inputEvent = mock(InputEvent.class);
        when(inputEvent.getText()).thenReturn(notKuchen);
        when(kuchenParser.getKuchenInfo(notKuchen)).thenReturn(null);

        addKuchenInputListener.onInputEvent(inputEvent);

        verifyNoInteractions(automat);
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
        List<Hersteller> herstellerList = new ArrayList<>();

        when(kuchenParser.getKuchenInfo(event.getText())).thenReturn(mock(KuchenImplementation.class));
        when(automat.getHerstellerList()).thenReturn(herstellerList);

        when(automat.addKuchen(any(), any())).thenThrow(new IllegalArgumentException("no such manufacturer"));

        addKuchenInputListener.onInputEvent(event);

        verify(printer).println("The Kuchen could not be added, reason: no such manufacturer");
    }

}