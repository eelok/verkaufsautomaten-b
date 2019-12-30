package automat.apps.console.mvc.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteKuchenInputListenerTest {

    @Mock
    private Automat automat;
    @Mock
    private Printer printer;
    @Mock
    private StringUtils stringUtiles;
    @Mock
    private InputEvent event;

    private DeleteKuchenInputListener deleteKuchenInputListener;

    @BeforeEach
    void setUp(){
        deleteKuchenInputListener = new DeleteKuchenInputListener(automat, stringUtiles, printer);
    }

    @Test
    void should_call_removeKuchen(){
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn("f1");
        int fachNum = 1;
        when(stringUtiles.extractFachNumberFromString(event.getText())).thenReturn(fachNum);

        deleteKuchenInputListener.onInputEvent(event);

        verify(automat).removeKuchenFromAutomat(fachNum);
    }

    @Test
    void should_call_removeKuchen_when_fach_does_not_exist(){
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn("f1");

        doThrow(new IllegalArgumentException("fachnummer does not exist")).when(automat).removeKuchenFromAutomat(anyInt());

        deleteKuchenInputListener.onInputEvent(event);

        verify(printer).println("fachnummer does not exist");
    }

}