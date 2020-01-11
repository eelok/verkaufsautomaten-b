package automat.net;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
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
    private static Command existingCommand;
    public static void existingCommand(Command existingCommand, String inputCommand, String inputData, ObjectOutputStream serverOutputStream) throws IOException {

        //todo если введена неверная комманда
//        try {
//            Command receivedCommand = Command.valueOf(inputCommand);
//        } catch (IllegalArgumentException ex){
//            serverOutputStream.writeObject(ex.getMessage());
////            serverOutputStream.writeObject(String.format("%s does not exist, check your input", serverOutputStream));
//        }
        switch (Command.valueOf(inputCommand)){
            case q:
                break;
            case addH:
                automatInServer.addHersteller(new HerstellerImplementation(inputData.toLowerCase()));
                serverOutputStream.writeObject(String.format("from server: hersteller %s was added to Automat", inputData));
                break;
            case addK:
                Kuchen kuchenInfo = kuchenParser.getKuchenInfo(inputData);
                automatInServer.addKuchen(kuchenInfo, LocalDateTime.now());
                serverOutputStream.writeObject(String.format("from server: kuchen %s was added to Automat", kuchenInfo.getType()));
                break;
            case listH:
                List<Hersteller> herstellerList = automatInServer.getHerstellerList();
                serverOutputStream.writeObject(herstellerList);
                break;
            case listK:
                List<Kuchen> allEingelagertenKuchen = automatInServer.getAllEingelagertenKuchen();
                serverOutputStream.writeObject(allEingelagertenKuchen);
                break;
            case delH:
                automatInServer.deleteHersteller(inputData);
                serverOutputStream.writeObject(String.format("from server %s was deleted", inputData));
                break;
            case delK:
                int fachNum = Integer.parseInt(inputData);
                EinlagerungEntry einlagerungEntry = automatInServer.removeKuchenFromAutomat(fachNum);
                serverOutputStream.writeObject(einlagerungEntry);
                break;
            default:
                serverOutputStream.writeObject("An Error occurred, check your input");
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        ObjectInputStream serverInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream serverOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Command command;


        while(true) {
            Object inputObject = serverInputStream.readObject();
            System.out.println(">>>>>>>>>>Object was recived from client: " + inputObject.toString());
            String receivedString = inputObject.toString();

            String[] split = receivedString.split("/");
            String commandFromInput = split[0].trim();
            String data = split[1].trim();

            existingCommand(existingCommand, commandFromInput, data, serverOutputStream);

        }
    }
}
