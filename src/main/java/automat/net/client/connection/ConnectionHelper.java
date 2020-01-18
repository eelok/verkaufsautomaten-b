package automat.net.client.connection;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionHelper {
    private Socket socketConnection;
    private ObjectOutputStream clientOutputStream;
    private ObjectInputStream clientInputStream ;

    private ConnectionHelper(Socket socketConnection, ObjectOutputStream clientOutputStream, ObjectInputStream clientInputStream) {
        this.socketConnection = socketConnection;
        this.clientOutputStream = clientOutputStream;
        this.clientInputStream = clientInputStream;
    }

    private static ConnectionHelper connectionHelperSingleton = null;


    public static ConnectionHelper getConnectionHelperSingleton() throws IOException {
        if(connectionHelperSingleton == null){
            Socket socketConnection = new Socket(InetAddress.getLocalHost(), 1234);
            ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
            ObjectInputStream clientInputStream = new ObjectInputStream(socketConnection.getInputStream());
            connectionHelperSingleton = new ConnectionHelper(socketConnection, clientOutputStream, clientInputStream);
        }
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
