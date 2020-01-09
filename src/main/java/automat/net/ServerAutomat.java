package automat.net;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Kuchen;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class ServerAutomat extends IOException {

    private static Automat automatInServer = new Automat(5);
    private static KuchenParser kuchenParser = new KuchenParser();


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        DataInputStream serverInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream serverOutputStream = new DataOutputStream(socket.getOutputStream());


        while (true) {
            String recivedString = serverInputStream.readUTF();
            System.out.println(">>>>>>>>>>Object was recived from client: " + recivedString);

            String[] split = recivedString.toLowerCase().split("|");
            String command = split[0];
            String data = split[1];
            if("Q".equalsIgnoreCase(command)){
                break;
            }
            if("addH".equalsIgnoreCase(command)){
                automatInServer.addHersteller(new HerstellerImplementation(data));
                serverOutputStream.writeUTF(command);
                System.out.println(String.format("hersteller %s was added to Automat", data));
            }
        }
    }
}
