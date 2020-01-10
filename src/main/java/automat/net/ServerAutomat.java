package automat.net;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Kuchen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

public class ServerAutomat extends IOException {

    private static Automat automatInServer = new Automat(5);
    private static KuchenParser kuchenParser = new KuchenParser();


    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
            String command = split[0].toLowerCase();
            String data = split[1].trim();

            if ("Q".equalsIgnoreCase(command)) {
                return;
            }
            if ("addH".equalsIgnoreCase(command)) {
                automatInServer.addHersteller(new HerstellerImplementation(data.toLowerCase()));
                serverOutputStream.writeObject(String.format("hersteller %s was added to Automat", data));
            }
            if ("addK".equalsIgnoreCase(command)) {
                Kuchen kuchenInfo = kuchenParser.getKuchenInfo(data);
                automatInServer.addKuchen(kuchenInfo, LocalDateTime.now());
                serverOutputStream.writeObject(String.format("kuchen %s was added to Automat", kuchenInfo.getType()));
            }
            if("listH".equalsIgnoreCase(command)){
                List<Hersteller> herstellerList = automatInServer.getHerstellerList();
                serverOutputStream.writeObject(herstellerList);
            }
            if("listK".equalsIgnoreCase(command)){
                List<Kuchen> allEingelagertenKuchen = automatInServer.getAllEingelagertenKuchen();
                serverOutputStream.writeObject(allEingelagertenKuchen);
            }
            if("delH".equalsIgnoreCase(command)){
                automatInServer.deleteHersteller(data);
                serverOutputStream.writeObject(String.format("from server %s was deleted", data));
            }

        }
    }
}
