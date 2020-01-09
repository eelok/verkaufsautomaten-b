package automat.net;

import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;

import java.io.*;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 1234;
        InetAddress ip = InetAddress.getLocalHost();
        Scanner scanner = new Scanner(System.in);
        Socket socketConnection = new Socket(ip, port);


        DataOutputStream clientOutputStream = new DataOutputStream(socketConnection.getOutputStream());
        DataInputStream clientInputStream = new DataInputStream(socketConnection.getInputStream());

        while (true){
            System.out.println("enter command");
            String commad = scanner.nextLine();
            System.out.println("enter data");
            String data = scanner.nextLine();
            String userInput = commad + "|" + data;

            if("q".equalsIgnoreCase(userInput)){
                System.out.println("bye");
                break;
            }
            clientOutputStream.writeUTF(userInput);

            Object recivedOject = clientInputStream.readUTF();
            System.out.println("was recived " + recivedOject);

        }
//        try(Socket socketConnection = new Socket(ip, port);
//            ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
//            ObjectInputStream clientInputStream = new ObjectInputStream(socketConnection.getInputStream()))  {
//            clientOutputStream.writeObject(addHerstellerCommand);
//            Automat automtResivedFromServer = (Automat)clientInputStream.readObject();
//            System.out.println(automtResivedFromServer.getAllEingelagertenKuchen().toString());
//            System.out.println("*********Resiced from Server"+ automtResivedFromServer.getHerstellerList().toString());
//        }catch (Exception e){
//            System.out.println(e);
//        }
    }
}

