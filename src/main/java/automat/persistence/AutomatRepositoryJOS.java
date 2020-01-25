package automat.persistence;

import automat.mainlib.Automat;

import java.io.*;

public class AutomatRepositoryJOS {
    //serialize
    public void writeToFile(ObjectOutput out, Automat automat) {
        try {
            out.writeObject(automat);
            out.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    //deserialize
    public Automat readFromFile(ObjectInput ois) {
        try {
            Automat automatFromFile = (Automat) ois.readObject();
            ois.close();
            return automatFromFile;
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }
}
