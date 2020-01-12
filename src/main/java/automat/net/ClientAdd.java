package automat.net;

import automat.apps.console.service.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientAdd {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StringUtils stringUtils = new StringUtils();

        try (Socket socketConnection = new Socket(InetAddress.getLocalHost(), 1234)) {
            ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
            ObjectInputStream clientInputStream = new ObjectInputStream(socketConnection.getInputStream());

            Scanner scanner = new Scanner(System.in);
            String dataForTransport = "";
            while (true){
                System.out.print(">");
                String inputData = scanner.nextLine();

                if(stringUtils.isOneWord(inputData)){
                    dataForTransport = Command.addH + "/" + inputData;
                } else {
                    dataForTransport = Command.addK + "/" + inputData;
                }

                clientOutputStream.writeObject(dataForTransport);
                Object replyFromServer = clientInputStream.readObject();
                System.out.println(replyFromServer);
            }
        }
    }
}

