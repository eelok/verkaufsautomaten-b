package automat.persistence;

import automat.mainlib.Automat;
import automat.mainlib.kuchen.Allergen;
import automat.persistence.persistenceDelegate.BigDecimalPersistenceDelegate;
import automat.persistence.persistenceDelegate.DurationPersistenceDelegate;
import automat.persistence.persistenceDelegate.EnumPersistenceDelegate;
import automat.persistence.persistenceDelegate.LocalDateTimePersistenceDelegate;

import java.beans.Introspector;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class AutomatRepositoryJBP {

    public void saveToFile(XMLEncoder encoder, Automat automat){
        encoder.setPersistenceDelegate(BigDecimal.class, new BigDecimalPersistenceDelegate());
        encoder.setPersistenceDelegate(Duration.class, new DurationPersistenceDelegate());
        encoder.setPersistenceDelegate(LocalDateTime.class, new LocalDateTimePersistenceDelegate());
        encoder.setPersistenceDelegate(Allergen.class, new EnumPersistenceDelegate());
        encoder.writeObject(automat);
        encoder.close();
    }

    public Automat readFromFile(XMLDecoder decoder) {
        Automat automat = (Automat) decoder.readObject();
        decoder.close();
        return automat;
    }
}
