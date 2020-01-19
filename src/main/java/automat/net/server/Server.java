package automat.net.server;

import automat.net.common.Printer;
import automat.net.server.observer.AddHerstellerObserver;
import automat.net.server.observer.AddNewKuchenObserver;
import automat.net.server.observer.DeleteHerstellerObserver;
import automat.net.server.observer.RemoveKuchenObserver;
import automat.net.common.KuchenParser;
import automat.mainlib.Automat;
import automat.net.server.handler.DataHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Automat automat;
    private DataHandler dataHandler;

    public Server(Automat automat, DataHandler dataHandler) {
        this.automat = automat;
        this.dataHandler = dataHandler;
    }


    public void initData() {
        Printer printer = new Printer();
        new AddHerstellerObserver(automat, printer);
        new DeleteHerstellerObserver(automat, printer);
        new AddNewKuchenObserver(automat, printer);
        new RemoveKuchenObserver(automat, printer);
    }

    public void run(ObjectInputStream serverInputStream, ObjectOutputStream serverOutputStream) throws IOException, ClassNotFoundException {
        Object inputObject = serverInputStream.readObject();
        System.out.println(">>>>>>>>>>Object was received from client: " + inputObject.toString());
        String receivedString = inputObject.toString();

        String[] split = receivedString.split("/");
        String commandFromInput = split[0].trim();
        String data = split[1].trim();

        String replyFromServer = dataHandler.handleData(commandFromInput, data);
        serverOutputStream.writeObject(replyFromServer);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket socket = serverSocket.accept();
        Automat automat = new Automat(5);
        Server server = new Server(automat, new DataHandler(automat, new KuchenParser()));
        server.initData();
        ObjectInputStream serverInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream serverOutputStream = new ObjectOutputStream(socket.getOutputStream());
        while (true) {
            server.run(serverInputStream, serverOutputStream);
        }

    }

}
