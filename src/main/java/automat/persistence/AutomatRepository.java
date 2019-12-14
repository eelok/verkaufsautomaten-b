package automat.persistence;

import automat.mainlib.Automat;

import java.io.*;

public class AutomatRepository {
    //serialize
    public static void saveToFile(String fileName, Automat automat) {
        try (FileOutputStream file = new FileOutputStream(fileName)) {
            try (ObjectOutputStream out = new ObjectOutputStream(file)) {
                out.writeObject(automat);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException is caught");
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    //deserialize
    public static Automat readFromFile(String filename) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (Automat) ois.readObject();
            }
        } catch (FileNotFoundException exception) {
                System.out.println("FileNotFoundException is caught");
        } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException is caught");
        } catch (IOException e){
            System.out.println("IOException is caught");
        }
    return null;
    }
}
