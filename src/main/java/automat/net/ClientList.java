package automat.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientList {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try (Socket socketConnection = new Socket(InetAddress.getLocalHost(), 1234)) {
            ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
            ObjectInputStream clientInputStream = new ObjectInputStream(socketConnection.getInputStream());

            Scanner scanner = new Scanner(System.in);
            String dataForTransport = "";

            while (true) {
                System.out.print(">");
                String inputData = scanner.nextLine();

                if ("manufacturer".equalsIgnoreCase(inputData)) {
                    dataForTransport = Command.listH + "/" + "*";
                }
                if ("kuchen".equalsIgnoreCase(inputData)) {
                    dataForTransport = Command.listK + "/" + "*";
                }
                clientOutputStream.writeObject(dataForTransport);
                Object infoFromServer = clientInputStream.readObject();
                System.out.println(infoFromServer);
            }
        }
    }
}