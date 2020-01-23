package automat.apps.console.mvc.listMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListKuchenInputListenerTest {

    private ListKuchenInputListener listKuchenInputListener;

    @Mock
    private Automat automat;
    @Mock
    private Printer printer;
    @Mock
    private InputEvent event;

    @BeforeEach
    void setUp(){
        listKuchenInputListener = new ListKuchenInputListener(automat, printer);
    }

    @Test
    void should_call_println_when_allKuchenWithFachNum_is_empty(){
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn("kuchen");

        listKuchenInputListener.onInputEvent(event);

        verify(printer).println("No Kuchen Available in the Automat");
    }

    @Test
    void should_call_println_when_allKuchenWithFachNum_is_not_empty(){
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn("kuchen");
        when(automat.getAllKuchenWithFachNum()).thenReturn(Collections.singletonList("kremkuchen: f2"));

        listKuchenInputListener.onInputEvent(event);

        verify(printer).println("kremkuchen: f2");
    }


}