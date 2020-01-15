package automat.net;

import automat.apps.console.Printer;
import automat.apps.console.observer.AddHerstellerObserver;
import automat.apps.console.observer.AddNewKuchenObserver;
import automat.apps.console.observer.DeleteHerstellerObserver;
import automat.apps.console.observer.RemoveKuchenObserver;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.net.server.DataHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAutomat extends IOException {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Automat automatInServer = new Automat(5);
        Printer printer = new Printer();
        AddHerstellerObserver addHerstellerObserver = new AddHerstellerObserver(automatInServer, printer);
        DeleteHerstellerObserver deleteHerstellerObserver = new DeleteHerstellerObserver(automatInServer, printer);
        AddNewKuchenObserver addNewKuchenObserver = new AddNewKuchenObserver(automatInServer, printer);
        RemoveKuchenObserver removeKuchenObserver = new RemoveKuchenObserver(automatInServer, printer);
        KuchenParser kuchenParser = new KuchenParser();

        DataHandler dataHandler = new DataHandler(automatInServer, kuchenParser);
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        ObjectInputStream serverInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream serverOutputStream = new ObjectOutputStream(socket.getOutputStream());

        while(true) {
            Object inputObject = serverInputStream.readObject();
            System.out.println(">>>>>>>>>>Object was received from client: " + inputObject.toString());
            String receivedString = inputObject.toString();

            String[] split = receivedString.split("/");
            String commandFromInput = split[0].trim();
            String data = split[1].trim();

            String replyFromServer = dataHandler.handleData(commandFromInput, data);
            serverOutputStream.writeObject(replyFromServer);
        }
    }
}
