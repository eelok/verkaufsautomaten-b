package automat.net;

import automat.mainlib.Automat;
import automat.net.server.DataHandler;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServerAutomatTest {

    private ServerAutomat server;

    @Mock
    private Automat automat;

    @Mock
    private DataHandler dataHandler;

    @BeforeEach
    void setup() {
        server = new ServerAutomat(automat, dataHandler);
    }

    @Test//todo come up with better name
    void test1() throws IOException, ClassNotFoundException {
        ServerAutomat serverAutomat = new ServerAutomat(automat, dataHandler);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(14);
        ObjectOutputStream outputStream = new ObjectOutputStream(bos);

        when(dataHandler.handleData("command", "donna")).thenReturn("42");
        outputStream.writeObject("command/donna");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        serverAutomat.run(objectInputStream, outputStream);
        ObjectInputStream dis = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        assertThat(dis.readObject()).isEqualTo("command/donna");
        assertThat(dis.readObject()).isEqualTo("42");
    }

}