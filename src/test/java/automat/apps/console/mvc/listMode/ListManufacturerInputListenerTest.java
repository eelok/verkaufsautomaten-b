package automat.apps.console.mvc.listMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListManufacturerInputListenerTest {

    @Mock
    private Automat automat;

    @Mock
    private InputEvent event;

    @Mock
    private Printer printer;

    private ListManufacturerInputListener listManufacturerInputListener;

    @BeforeEach
    void setUp() {
        listManufacturerInputListener = new ListManufacturerInputListener(automat, printer);
    }

    @Test
    void should_call_method_println_when_herstellerWithNumberOfKuchen_is_empty() {
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn("manufacturer");

        listManufacturerInputListener.onInputEvent(event);

        verify(printer).println("there is no manufacturer");
    }

    @Test
    void should_call_herstellerWithNumberOfKuchen() {
        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn("manufacturer");
        when(automat.getHerstellerWithNumberOfKuchen()).thenReturn(Collections.singletonList("alex: 1"));

        listManufacturerInputListener.onInputEvent(event);

        verify(printer).println("alex: 1");
    }


}