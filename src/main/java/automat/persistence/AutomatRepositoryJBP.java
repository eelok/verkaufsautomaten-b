package automat.persistence;

import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.KremkuchenImplementation;
import automat.mainlib.kuchen.KuchenImplementation;

import java.beans.*;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class AutomatRepositoryJBP {

    public static void main(String[] args) {
        Automat automat = new Automat(10);
        HerstellerImplementation alex = new HerstellerImplementation("alex");
        automat.addHersteller(alex);
        var kuchen = new KuchenImplementation(
                BigDecimal.valueOf(20),
                alex,
                Arrays.asList(Allergen.Erdnuss, Allergen.Gluten),
                550,
                Duration.ofDays(2)
        );

        automat.addKuchen(kuchen, LocalDateTime.now());
        write(automat);
    }

    public static void write(Automat automat) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("automat.xml")))) {
            encoder.setPersistenceDelegate(LocalDateTime.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object localDateTime, Encoder out) {
                    return new Expression(localDateTime, LocalDateTime.class, "parse", new Object[]{localDateTime.toString()});
                }
            });

            encoder.setPersistenceDelegate(BigDecimal.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object price, Encoder out) {
                    return new Expression(price, BigDecimal.class, "valueOf", new Object[]{price.toString()});
                }
            });

            encoder.setPersistenceDelegate(Duration.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object haltbarkeit, Encoder out) {
                    return new Expression(haltbarkeit, Duration.class, "parse", new Object[]{haltbarkeit.toString()});
                }
            });
            encoder.writeObject(automat);
//            encoder.writeObject(automat);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
