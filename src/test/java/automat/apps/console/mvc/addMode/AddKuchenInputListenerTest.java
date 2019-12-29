package automat.apps.console.mvc.addMode;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class AddKuchenInputListenerTest {
    private AddKuchenInputListener addKuchenInputListener;
    private KuchenParser kuchenParser;
    private Automat automat;

    @BeforeEach
    void setUp() {
        automat = mock(Automat.class);
        kuchenParser = mock(KuchenParser.class);
        addKuchenInputListener = new AddKuchenInputListener(kuchenParser, automat);
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
    void should_throw_exception_automat_is_full() {
        when(automat.getPlatzImAutomat()).thenReturn(0);
        InputEvent event = mock(InputEvent.class);
        String text = "some text";
        when(event.getText()).thenReturn(text);
        Kuchen kuchen = mock(KuchenImplementation.class);
        when(kuchenParser.getKuchenInfo(event.getText())).thenReturn(kuchen);
        LocalDateTime ldt = LocalDateTime.now();
        when(automat.addKuchen(kuchen, ldt)).thenThrow(AutomatIsFullException.class);

        addKuchenInputListener.onInputEvent(event);

        assertThatThrownBy(() -> automat.addKuchen(kuchen, ldt))
                .isInstanceOf(AutomatIsFullException.class);
    }

}