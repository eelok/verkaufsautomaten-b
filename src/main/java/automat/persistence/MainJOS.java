package automat.persistence;

import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenImplementation;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainJOS {

    public static void main(String[] args) {
        AutomatRepositoryJOS automatRepositoryJOS = new AutomatRepositoryJOS();
        Automat automat = new Automat(5);
        String fileName = "automat.txt";

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

        automat.addKuchen(kuchen, LocalDateTime.now());

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
