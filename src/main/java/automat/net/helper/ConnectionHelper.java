package automat.net.helper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionHelper {
    private int port = 1234;
    private Socket socketConnection = null;
    private ObjectOutputStream clientOutputStream = null;
    private ObjectInputStream clientInputStream = null;

    private ConnectionHelper() {
        try {
            this.socketConnection = new Socket(InetAddress.getLocalHost(), port);
            this.clientOutputStream = new ObjectOutputStream(this.socketConnection.getOutputStream());
            this.clientInputStream = new ObjectInputStream(this.socketConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final ConnectionHelper connectionHelperSingleton = new ConnectionHelper();


    public static ConnectionHelper getConnectionHelperSingleton(){
        return connectionHelperSingleton;
    }

    public Socket getSocketConnection() {
        return socketConnection;
    }

    public ObjectOutputStream getClientOutputStream() {
        return clientOutputStream;
    }

    public ObjectInputStream getClientInputStream() {
        return clientInputStream;
    }

}
