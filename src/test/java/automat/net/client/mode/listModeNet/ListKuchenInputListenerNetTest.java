package automat.net.client.mode.listModeNet;

import automat.apps.console.mvc.event.InputEvent;
import automat.net.client.connection.DataSender;
import automat.net.common.Command;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListKuchenInputListenerNetTest {
    @Mock
    private DataSender dataSender;

    @Test
    void should_call_method_sendDataToServer() throws IOException, ClassNotFoundException {
        ListKuchenInputListenerNet listKuchenInputListenerNet = new ListKuchenInputListenerNet(dataSender);
        InputEvent event = mock(InputEvent.class);
        String data = "kuchen";
        when(event.getText()).thenReturn(data);

        listKuchenInputListenerNet.onInputEvent(event);

        verify(dataSender).sendDataToServer(data, Command.LIST_KUCHEN);
    }
}