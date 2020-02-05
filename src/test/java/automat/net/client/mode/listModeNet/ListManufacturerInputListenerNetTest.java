package automat.net.client.mode.listModeNet;

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
class ListManufacturerInputListenerNetTest {
    @Mock
    private DataSender dataSender;

    private ListManufacturerInputListenerNet listManufacturerInputListenerNet;

    @BeforeEach
    void setUp(){
        listManufacturerInputListenerNet = new ListManufacturerInputListenerNet(dataSender);
    }

    @Test
    void should_call_method_sendDataToServer() throws IOException, ClassNotFoundException {
        InputEvent event = mock(InputEvent.class);
        String data = "manufacturer";
        when(event.getText()).thenReturn(data);

        listManufacturerInputListenerNet.onInputEvent(event);

        verify(dataSender).sendDataToServer(data, Command.LIST_HERSTELLER);
    }

    @Test
    void should_throws_exception() throws IOException, ClassNotFoundException {
        InputEvent event = mock(InputEvent.class);
        when(event.getText()).thenReturn("manufacturer");

        doThrow(new IOException()).when(dataSender).sendDataToServer("manufacturer", Command.LIST_HERSTELLER);

        assertThatThrownBy(() -> listManufacturerInputListenerNet.onInputEvent(event))
                .isInstanceOf(RuntimeException.class);
    }

}