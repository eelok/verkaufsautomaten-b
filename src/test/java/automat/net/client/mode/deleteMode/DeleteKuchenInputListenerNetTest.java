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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteKuchenInputListenerNetTest {

    @Mock
    private DataSender dataSender;

    private DeleteKuchenInputListenerNet deleteKuchenInputListenerNet;

    @BeforeEach
    void setUp(){
        deleteKuchenInputListenerNet = new DeleteKuchenInputListenerNet(dataSender);
    }

    @Test
    void should_be_no_interaction_when_input_null() {
        InputEvent event = mock(InputEvent.class);
        when(event.getText()).thenReturn(null);
        deleteKuchenInputListenerNet.onInputEvent(event);

        verifyNoInteractions(dataSender);
    }

    @Test
    void should_call_method_sendDataToServer() throws IOException, ClassNotFoundException {
        InputEvent event = mock(InputEvent.class);
        when(event.getText()).thenReturn("kuchen:");
        deleteKuchenInputListenerNet.onInputEvent(event);

        verify(dataSender).sendDataToServer("kuchen:", Command.DELETE_KUCHEN);
    }

    @Test
    void should_throws_exception() throws IOException, ClassNotFoundException {
        InputEvent event = mock(InputEvent.class);
        String textToSend = "kuchen:";
        when(event.getText()).thenReturn(textToSend);

        doThrow(new IOException()).when(dataSender).sendDataToServer(textToSend, Command.DELETE_KUCHEN);

        assertThatThrownBy(() -> deleteKuchenInputListenerNet.onInputEvent(event))
                .isInstanceOf(RuntimeException.class);
    }

}