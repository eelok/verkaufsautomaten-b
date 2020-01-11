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
    public static String existingCommand(Command existingCommand, String inputCommand, String inputData) throws IOException {

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
                if(automatInServer.addHersteller(new HerstellerImplementation(inputData.toLowerCase()))){
                    return String.format("from server: hersteller %s was added to automat", inputData);
                }
            case addK:
                Kuchen kuchenInfo = kuchenParser.getKuchenInfo(inputData);
                automatInServer.addKuchen(kuchenInfo, LocalDateTime.now());
                return String.format("from server: kuchen %s was added to automat", kuchenInfo.getType());
            case listH:
                List<Hersteller> herstellerList = automatInServer.getHerstellerList();
                return "from server: " + herstellerList.toString();
            case listK:
                List<Kuchen> allEingelagertenKuchen = automatInServer.getAllEingelagertenKuchen();
                return "from server: " + allEingelagertenKuchen.toString();
            case delH:
                automatInServer.deleteHersteller(inputData);
                return String.format("hersteller from server: %s was deleted from automat", inputData);
            case delK:
                String typeOfKuchenWasDel = deleteKuchen(inputData);
                return String.format("%s from fach %s was deleted", typeOfKuchenWasDel, inputData);
            default:
//                serverOutputStream.writeObject("An Error occurred, check your input");
        }
        return null;
    }
    private static String deleteKuchen(String inputData){
        String number = inputData.replace("f", "");
        int fachNum = Integer.parseInt(number);
        EinlagerungEntry einlagerungEntry = automatInServer.removeKuchenFromAutomat(fachNum);
        return einlagerungEntry.getKuchen().getType();
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

            String s = existingCommand(existingCommand, commandFromInput, data);
            serverOutputStream.writeObject(s);

        }
    }
}
