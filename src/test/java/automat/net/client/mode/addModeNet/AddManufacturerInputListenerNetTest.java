package automat.net.client.mode.addModeNet;

import automat.apps.console.mvc.event.InputEvent;
import automat.net.client.connection.DataSender;
import automat.net.common.Command;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddManufacturerInputListenerNetTest {

    private AddManufacturerInputListenerNet addManufacturerInputListenerNet;
    @Mock
    private DataSender dataSender;

    @BeforeEach
    void setUp() {
        addManufacturerInputListenerNet = new AddManufacturerInputListenerNet(dataSender);
    }

    @Test
    void should_be_no_interaction_when_userInput_null(){
        InputEvent event = mock(InputEvent.class);
        when(event.getText()).thenReturn(null);

        addManufacturerInputListenerNet.onInputEvent(event);

        verifyNoInteractions(dataSender);
    }

    @Test
    void should_call_method_sendDataToServer() throws IOException, ClassNotFoundException {
        InputEvent event = mock(InputEvent.class);
        String textForInput = "manufacturer:";
        when(event.getText()).thenReturn(textForInput);

        addManufacturerInputListenerNet.onInputEvent(event);

        verify(dataSender).sendDataToServer(textForInput, Command.ADD_HERSTELLER);
    }

    @Test
    void should_throws_exception() throws IOException, ClassNotFoundException {
        InputEvent event = mock(InputEvent.class);
        String textToSend = "manufacturer:";
        when(event.getText()).thenReturn(textToSend);

        doThrow(new IOException()).when(dataSender).sendDataToServer(textToSend, Command.ADD_HERSTELLER);

        assertThatThrownBy(() -> addManufacturerInputListenerNet.onInputEvent(event))
                .isInstanceOf(RuntimeException.class);
    }

}