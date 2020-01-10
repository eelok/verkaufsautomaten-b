package automat.net;

import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenImplementation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (Socket socketConnection = new Socket(InetAddress.getLocalHost(), 1234)) {
            ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
            ObjectInputStream clientInputStream = new ObjectInputStream(socketConnection.getInputStream());


            clientOutputStream.writeObject("addH/alex");
            String alex = (String) clientInputStream.readObject();
            System.out.println(alex);
            clientOutputStream.writeObject("addH/donna");
            String donna = (String) clientInputStream.readObject();
            System.out.println(donna);
//            clientOutputStream.writeObject("addK/Kremkuchen 2.50 Alex Erdnuss,Haselnuss 1400 24 Sahne");
//            String infoKuchen =(String) clientInputStream.readObject();
//            System.out.println("from server" + infoKuchen);
//            clientOutputStream.writeObject("listH/*");
//            Object l = clientInputStream.readObject();
//            System.out.println("from server" + l.toString());
//            clientOutputStream.writeObject("listK/*");
//            Object kuchL = clientInputStream.readObject();
//            System.out.println("from server" + kuchL.toString());
            clientOutputStream.writeObject("delH/alex");
            Object delH = clientInputStream.readObject();
            System.out.println("from server" + delH.toString());
            clientOutputStream.writeObject("listH/*");
            Object readInf = clientInputStream.readObject();
            System.out.println("from server" + readInf.toString());
        }
    }
}

