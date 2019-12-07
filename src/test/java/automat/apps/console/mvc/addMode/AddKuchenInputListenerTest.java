package automat.mvc.addMode;

import automat.apps.console.mvc.addMode.AddKuchenInputListener;
import automat.mainlib.Verwaltung;
import automat.apps.console.mvc.InputEvent;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AddKuchenInputListenerTest {

    @Test
    void should_call_addKuchen(){
        Verwaltung verwaltung = mock(Verwaltung.class);
        KuchenParser kuchenParser = mock(KuchenParser.class);
        AddKuchenInputListener addKuchenInputListener = new AddKuchenInputListener(kuchenParser, verwaltung);
        InputEvent inputEvent = mock(InputEvent.class);
        Kuchen kuchen = mock(Kuchen.class);
        when(kuchenParser.getKuchenInfo("text")).thenReturn(kuchen);
        when(inputEvent.getText()).thenReturn("text");

        addKuchenInputListener.onInputEvent(inputEvent);

        verify(verwaltung).addKuchen(eq(kuchen), any(LocalDateTime.class));
    }

}
