package automat.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

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

            //todo введена неверная команда
            clientOutputStream.writeObject("add/*");
            String str = (String) clientInputStream.readObject();
            System.out.println(str);

            clientOutputStream.writeObject("addK/Kremkuchen 2.50 Alex Erdnuss,Haselnuss 1400 24 Sahne");
            String infoKuchen =(String) clientInputStream.readObject();
            System.out.println(infoKuchen);

            clientOutputStream.writeObject("addK/Kuchen 2.50 Donna Gluten,Haselnuss 1400 54");
            String infoKuch2 =(String) clientInputStream.readObject();
            System.out.println(infoKuch2);

            clientOutputStream.writeObject("listH/*");
            Object l = clientInputStream.readObject();
            System.out.println(l);

            clientOutputStream.writeObject("listK/*");
            Object kuchL = clientInputStream.readObject();
            System.out.println(kuchL);

            clientOutputStream.writeObject("delH/donna");
            Object delH = clientInputStream.readObject();
            System.out.println(delH);

            clientOutputStream.writeObject("listH/*");
            Object readInf = clientInputStream.readObject();
            System.out.println(readInf);

            clientOutputStream.writeObject("delK/f0");
            Object delKuch = clientInputStream.readObject();
            System.out.println(delKuch);
        }
    }
}

