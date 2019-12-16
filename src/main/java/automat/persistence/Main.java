package automat.persistence;

import automat.mainlib.Automat;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        AutomatRepositoryJOS automatRepositoryJOS = new AutomatRepositoryJOS();
        Automat automat = new Automat(5);
        String fileName = "automat.txt";

        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
            automatRepositoryJOS.saveToFile(out, automat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectInput ois = new ObjectInputStream(new FileInputStream(fileName));
            automatRepositoryJOS.readFromFile(ois);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
