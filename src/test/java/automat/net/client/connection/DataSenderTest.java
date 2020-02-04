package automat.net.client.connection;

import automat.apps.console.service.Printer;
import automat.net.common.Command;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataSenderTest {
    @Mock
    private ConnectionHelper connectionHelper;
    @Mock
    private Printer printer;

    private DataSender dataSender;

    @BeforeEach
    void setUp(){
        dataSender = new DataSender(connectionHelper, printer);
    }

    @Test
    void should_call_method_println() throws IOException, ClassNotFoundException {
        ObjectOutputStream outputStream = mock(ObjectOutputStream.class);
        when(connectionHelper.getClientOutputStream()).thenReturn(outputStream);

        dataSender.sendDataToServer("data", Command.ADD_HERSTELLER);

        verify(connectionHelper.getClientOutputStream()).writeObject(anyString());

    }

}