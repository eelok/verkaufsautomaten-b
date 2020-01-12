package automat.net;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerAutomat extends IOException {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Automat automatInServer = new Automat(5);
//        //todo start temp data
        List<Hersteller> list = new ArrayList<>();
        Automat automatInServer = new Automat(5, new ArrayList<>(), list);
        HerstellerImplementation alex = new HerstellerImplementation("alex");
        list.add(alex);
        HerstellerImplementation donna = new HerstellerImplementation("donna");
        list.add(donna);
//        List<Allergen> allergens = new ArrayList<>();
//        allergens.add(Allergen.Erdnuss);
//        allergens.add(Allergen.Gluten);
//        Kuchen kuchen = new KuchenImplementation(
//                new BigDecimal(20),
//                alex,
//                allergens,
//                500,
//                Duration.ofDays(2)
//        );
//
//        Kuchen kremkuchen = new KremkuchenImplementation(
//                new BigDecimal(15),
//                donna,
//                Arrays.asList(Allergen.Haselnuss, Allergen.Sesamsamen),
//                500,
//                Duration.ofDays(1),
//                "sahne"
//        );
//        automatInServer.addKuchen(kuchen, LocalDateTime.now());
//        automatInServer.addKuchen(kremkuchen, LocalDateTime.now());
//        // todo end temp data

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
            String replyFromServer = transit.existingCommand(commandFromInput, data);
            serverOutputStream.writeObject(replyFromServer);

        }
    }
}
