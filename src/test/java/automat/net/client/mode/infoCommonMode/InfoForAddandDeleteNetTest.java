package automat.net.client.mode.infoCommonMode;

import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.service.Printer;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfoForAddandDeleteNetTest {
    @Mock
    private Printer printer;
    @Mock
    private InputEvent inputEvent;

    @Test
    void should_call_method_println() {
        InfoForAddandDeleteNet infoForAddandDeleteNet = new InfoForAddandDeleteNet(printer);
        when(inputEvent.getText()).thenReturn("some text");

        infoForAddandDeleteNet.onInputEvent(inputEvent);

        verify(printer).println("Wrong input");
    }

}