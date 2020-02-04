package automat.apps.console.mvc.addMode;

import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.service.KuchenParser;
import automat.apps.console.service.Printer;
import automat.mainlib.Automat;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenImplementation;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddKuchenInputListenerTest {

    private AddKuchenInputListener addKuchenInputListener;
    @Mock
    private KuchenParser kuchenParser;
    @Mock
    private Automat automat;
    @Mock
    private Printer printer;

    @BeforeEach
    void setUp() {
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
        String someText =  "Kuchen: kuchen 2.5 Alex Gluten,Haselnuss 1400 24";
        Object source = new Object();
        InputEvent event = new InputEvent(source, someText);
        Kuchen kuchen = mock(KuchenImplementation.class);

        when(kuchenParser.getKuchenInfo(any())).thenReturn(kuchen);

        addKuchenInputListener.onInputEvent(event);

        verify(automat).addKuchen(eq(kuchen), any(LocalDateTime.class));
    }

    @Test
    void should_call_println_when_automat_is_full() {
        when(automat.getPlatzImAutomat()).thenReturn(0);
        InputEvent event = mock(InputEvent.class);
        String text = "Kuchen: kuchen 2.5 Alex Gluten,Haselnuss 1400 24";
        when(event.getText()).thenReturn(text);

        when(automat.addKuchen(any(), any())).thenThrow(new AutomatIsFullException("message"));

        addKuchenInputListener.onInputEvent(event);

        verify(printer).println("Can not add kuchen, reason: message");
    }

    @Test
    void should_call_println_when_no_manufacturer(){
        when(automat.getPlatzImAutomat()).thenReturn(1);
        InputEvent event = mock(InputEvent.class);
        String text = "Kuchen: kuchen 2.5 Alex Gluten,Haselnuss 1400 24";
        when(event.getText()).thenReturn(text);

        when(automat.addKuchen(any(), any())).thenThrow(new IllegalArgumentException("no such manufacturer"));

        addKuchenInputListener.onInputEvent(event);

        verify(printer).println("The Kuchen could not be added, reason: no such manufacturer");
    }

    @Test
    void should_call_println_when_wrong_kuchenType(){
        when(automat.getPlatzImAutomat()).thenReturn(1);
        InputEvent event = mock(InputEvent.class);
        String text = "Kuchen: 2.5 Alex Gluten,Haselnuss 1400 24";
        when(event.getText()).thenReturn(text);

        when(automat.addKuchen(any(), any())).thenThrow(new ArrayIndexOutOfBoundsException("wrong input"));

        addKuchenInputListener.onInputEvent(event);

        verify(printer).println("wrong input");
    }

}