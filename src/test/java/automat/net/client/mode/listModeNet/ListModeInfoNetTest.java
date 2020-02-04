package automat.net.client.mode.listModeNet;

import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.listMode.ListModeInfo;
import automat.apps.console.service.Printer;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ListModeInfoNetTest {

    @Mock
    private Printer printer;
    @Mock
    private InputEvent event;

    @Test
    void should_call_method_println(){
        ListModeInfoNet listModeInfoNet = new ListModeInfoNet(printer);
        when(event.getText()).thenReturn("something");

        listModeInfoNet.onInputEvent(event);

        verify(printer).println(
                "Input example:\n" +
                        "manufacturer\n" +
                        "kuchen\n" +
                        ":q -back to main menu");
    }

}