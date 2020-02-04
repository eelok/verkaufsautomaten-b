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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddKuchenInputListenerNetTest {

    @Mock
    private DataSender dataSender;
    private AddKuchenInputListenerNet addkuchenInputListenerNet;

    @BeforeEach
    void setUp(){
        addkuchenInputListenerNet = new AddKuchenInputListenerNet(dataSender);
    }

    @Test
    void should_be_no_interaction_when_userInput_null(){
        InputEvent inputEvent = mock(InputEvent.class);
        when(inputEvent.getText()).thenReturn(null);

        addkuchenInputListenerNet.onInputEvent(inputEvent);

        verifyNoInteractions(dataSender);
    }

    @Test
    void should_call_sendDataToServer() throws IOException, ClassNotFoundException {
        InputEvent event = mock(InputEvent.class);
        String textToSend = "kuchen: a";
        when(event.getText()).thenReturn(textToSend);

        addkuchenInputListenerNet.onInputEvent(event);

        verify(dataSender).sendDataToServer(textToSend, Command.ADD_KUCHEN);
    }

    ///todo
//    @Test
//    void should_throw_ClassNotFoundException() throws IOException, ClassNotFoundException {
//        InputEvent event = mock(InputEvent.class);
//        String textToSend = "kuchen:";
//        when(event.getText()).thenReturn(textToSend);
//
//        doThrow(new RuntimeException()).when(dataSender).sendDataToServer(textToSend, Command.ADD_KUCHEN);
//        addkuchenInputListenerNet.onInputEvent(event);

//    }
}