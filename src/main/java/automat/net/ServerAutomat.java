package automat.net;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAutomat extends IOException {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Automat automatInServer = new Automat(5);
        KuchenParser kuchenParser = new KuchenParser();

        Transit transit = new Transit(automatInServer, kuchenParser);
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        ObjectInputStream serverInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream serverOutputStream = new ObjectOutputStream(socket.getOutputStream());


        while(true) {
            Object inputObject = serverInputStream.readObject();
            System.out.println(">>>>>>>>>>Object was recived from client: " + inputObject.toString());
            String receivedString = inputObject.toString();

            String[] split = receivedString.split("/");
            String commandFromInput = split[0].trim();
            String data = split[1].trim();

            String info = transit.existingCommand(commandFromInput, data);
            serverOutputStream.writeObject(info);

        }
    }
}
