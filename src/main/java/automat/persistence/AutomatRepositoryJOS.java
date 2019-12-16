package automat.persistence;

import automat.mainlib.Automat;

import java.io.*;

public class AutomatRepositoryJOS {
    //serialize
    public void saveToFile(ObjectOutput out, Automat automat) {
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
            ois.readObject();
            ois.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }
}
