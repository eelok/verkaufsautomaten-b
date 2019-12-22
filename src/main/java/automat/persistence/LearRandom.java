package automat.persistence;

import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenImplementation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LearRandom {

    public static void main(String args[]) {
        Automat automat = new Automat(2);

        List<Allergen> allergens = new ArrayList<>();
        allergens.add(Allergen.Erdnuss);
        allergens.add(Allergen.Gluten);
        Hersteller alex = new HerstellerImplementation("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = new KuchenImplementation(
                new BigDecimal(20),
                alex,
                allergens,
                500,
                Duration.ofDays(2)
        );

        Kuchen kuchen1 = new KuchenImplementation(
                new BigDecimal(250),
                alex,
                allergens,
                500,
                Duration.ofDays(2)
        );
        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kuchen1, LocalDateTime.now());


        Kuchen kuchenUeberFachNummber = getKuchenUeberFachNummber(0, automat);

        System.out.println(kuchenUeberFachNummber.toString());

    }

    public static Kuchen getKuchenUeberFachNummber(int fachNum, Automat automat) {
        List<EinlagerungEntry> storage = automat.getStorage();
        if (fachNum < storage.size()) {
            EinlagerungEntry einlagerungEntry = storage.get(fachNum);
            return einlagerungEntry.getKuchen();
        }
        throw new IllegalArgumentException("Fach number doesn't exist");
    }

    public static void saveKuchenToFile(String fileName, Automat automat, int fachNum) {
        try (RandomAccessFile fileStore = new RandomAccessFile(fileName, "rw")) {
            Kuchen kuchen = getKuchenUeberFachNummber(fachNum, automat);
            int priceInt = kuchen.getPrice().intValue();
            Collection<Allergen> allergenes = kuchen.getAllergenes();
            String allAlergens = "";
            for (Allergen each : allergenes) {
                allAlergens += each + " ";
            }
            fileStore.write(priceInt);
            fileStore.writeUTF(kuchen.getHersteller().getName());
            fileStore.writeUTF(allAlergens);
            fileStore.write(kuchen.getNaehrwert());
            fileStore.writeLong(kuchen.getHaltbarkeit().toDays());
            fileStore.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Kuchen randomAccessLoad(String filename) {
        Kuchen resultKuchen = new KuchenImplementation();
        try (RandomAccessFile ras = new RandomAccessFile(filename, "r")) {
            ras.seek(0);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
