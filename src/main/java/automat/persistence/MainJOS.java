package automat.persistence;

import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainJOS {

    public static void main(String[] args) {
        AutomatRepositoryJOS automatRepositoryJOS = new AutomatRepositoryJOS();
        Automat automat = new Automat(5);
        automat.setName("food automat");
        String fileName = "automat.txt";

        List<Allergen> allergens = new ArrayList<>();
        allergens.add(Allergen.ERDNUSS);
        allergens.add(Allergen.GLUTEN);
        Hersteller alex = new HerstellerImplementation("alex");
        Hersteller donna = new HerstellerImplementation("donna");
        automat.addHersteller(alex);
        automat.addHersteller(donna);

        Kuchen kuchen = new KuchenImplementation(
                new BigDecimal(20),
                alex,
                allergens,
                500,
                Duration.ofDays(2)
        );

        Kremkuchen kremkuchen = new KremkuchenImplementation(
                new BigDecimal(15),
                donna,
                Arrays.asList(Allergen.HASELNUSS, Allergen.SESAMSAMEN),
                500,
                Duration.ofDays(1),
                "sahne"
        );

        Obstkuchen obstkuchen = new ObstkuchenImplementation(
                new BigDecimal(15),
                donna,
                Arrays.asList(Allergen.GLUTEN, Allergen.SESAMSAMEN),
                800,
                Duration.ofDays(2),
                "Kirsche"
        );

        Obsttorte obsttorte = new ObsttorteImplementation(
                new BigDecimal(15),
                donna,
                Arrays.asList(Allergen.GLUTEN, Allergen.SESAMSAMEN, Allergen.HASELNUSS),
                800,
                Duration.ofDays(2),
                "Sahne",
                "Kirsche"
        );
        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremkuchen, LocalDateTime.now());
        automat.addKuchen(obstkuchen, LocalDateTime.now());
        automat.addKuchen(obsttorte, LocalDateTime.now());

        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
            automatRepositoryJOS.writeToFile(out, automat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectInput ois = new ObjectInputStream(new FileInputStream(fileName));
            Automat automatFromFile = automatRepositoryJOS.readFromFile(ois);
            System.out.println(automatFromFile.toString());
            System.out.println("*********");
            System.out.println(automatFromFile.getHerstellerList());
            System.out.println("*********");
            System.out.println(automatFromFile.getAllEingelagertenKuchen().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
