package automat.persistence;

import automat.mainlib.Automat;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PersistenceTest {

    @Test
    void should_save() {
        var originalAutomat = new Automat(5);
        HerstellerImplementation alex = new HerstellerImplementation("alex");
        originalAutomat.addHersteller(alex);
        var kuchen = new KuchenImplementation(
                BigDecimal.valueOf(20),
                alex,
                Arrays.asList(Allergen.Erdnuss, Allergen.Gluten),
                550,
                Duration.ofDays(2)
        );
        var kremkuchen = new KremkuchenImplementation(
                BigDecimal.valueOf(20),
                alex,
                Arrays.asList(Allergen.Erdnuss, Allergen.Gluten, Allergen.Haselnuss),
                550,
                Duration.ofDays(2),
                "Sahne"
        );

        var obstkuchen = new ObstkuchenImplementation(
                BigDecimal.valueOf(20),
                alex,
                Arrays.asList(Allergen.Erdnuss, Allergen.Gluten, Allergen.Haselnuss),
                550,
                Duration.ofDays(2),
                "Erdbeeren"
        );
        var obsttorte = new ObsttorteImplementation(
                BigDecimal.valueOf(20),
                alex,
                Arrays.asList(Allergen.Erdnuss, Allergen.Gluten, Allergen.Haselnuss),
                550,
                Duration.ofDays(2),
                "Erdbeeren",
                "Sahne"
        );
        originalAutomat.addKuchen(obsttorte, LocalDateTime.now());
        originalAutomat.addKuchen(obstkuchen, LocalDateTime.now());
        originalAutomat.addKuchen(kremkuchen, LocalDateTime.now());
        originalAutomat.addKuchen(kuchen, LocalDateTime.now());
        String fileName = "automat.txt";
        saveAutomat(originalAutomat, fileName);
        Automat deserializedAutomat = readFromFile(fileName);

        assertThat(deserializedAutomat).isEqualTo(originalAutomat);
    }

    private Automat readFromFile(String fileName) {
        try (FileInputStream file = new FileInputStream(fileName)) {
            try (ObjectInputStream in = new ObjectInputStream(file)) {
                return (Automat) in.readObject();
            }
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return null;
    }

    private static void saveAutomat(Automat automat, String fileName) {
        try (FileOutputStream file = new FileOutputStream(fileName)) {
            try (ObjectOutputStream out = new ObjectOutputStream(file)) {
                out.writeObject(automat);
            }
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

}
