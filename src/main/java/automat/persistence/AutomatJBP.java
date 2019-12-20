package automat.persistence;

import automat.mainlib.Automat;
import automat.mainlib.kuchen.Allergen;
import automat.persistence.persistenceDelegate.BigDecimalPersistenceDelegate;
import automat.persistence.persistenceDelegate.DurationPersistenceDelegate;
import automat.persistence.persistenceDelegate.EnumPersistenceDelegate;
import automat.persistence.persistenceDelegate.LocalDateTimePersistenceDelegate;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class AutomatJBP {

    public void saveToFile(XMLEncoder encoder, Automat automat){
        encoder.setPersistenceDelegate(BigDecimal.class, new BigDecimalPersistenceDelegate());
        encoder.setPersistenceDelegate(Duration.class, new DurationPersistenceDelegate());
        encoder.setPersistenceDelegate(LocalDateTime.class, new LocalDateTimePersistenceDelegate());
        encoder.setPersistenceDelegate(Allergen.class, new EnumPersistenceDelegate());
        encoder.writeObject(automat);
        encoder.close();
    }

    public Automat readFromFile(XMLDecoder decoder) {
        return (Automat) decoder.readObject();
    }
}
