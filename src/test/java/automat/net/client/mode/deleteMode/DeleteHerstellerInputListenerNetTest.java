package automat.net.client.mode.deleteMode;

import automat.apps.console.mvc.event.InputEvent;
import automat.net.client.connection.DataSender;
import automat.net.common.Command;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteHerstellerInputListenerNetTest {

    @Mock
    private DataSender dataSender;

    private DeleteHerstellerInputListenerNet deleteHerstellerInputListenerNet;

    @BeforeEach
    void setUp() {
        deleteHerstellerInputListenerNet = new DeleteHerstellerInputListenerNet(dataSender);
    }

    @Test
    void should_be_no_interaction_when_input_null() {
        InputEvent event = mock(InputEvent.class);
        when(event.getText()).thenReturn(null);
        deleteHerstellerInputListenerNet.onInputEvent(event);

        verifyNoInteractions(dataSender);
    }

    @Test
    void should_call_method_sendDataToServer() throws IOException, ClassNotFoundException {
        InputEvent event = mock(InputEvent.class);
        when(event.getText()).thenReturn("manufacturer:");
        deleteHerstellerInputListenerNet.onInputEvent(event);

        verify(dataSender).sendDataToServer("manufacturer:", Command.DELETE_HERSTELLER);
    }


}