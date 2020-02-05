package automat.persistence;

import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainJBP {

    public static void main(String[] args) {
        AutomatRepositoryJBP automatRepositoryJBP = new AutomatRepositoryJBP();
        Automat automat = new Automat(4);
        automat.setName("kuchen automat");
        File automatXML = new File("automat.xml");

        List<Allergen> allergens = new ArrayList<>();
        allergens.add(Allergen.ERDNUSS);
        allergens.add(Allergen.GLUTEN);



        Hersteller alex = new HerstellerImplementation("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = new ObsttorteImplementation(
                new BigDecimal(20),
                alex,
                allergens,
                500,
                Duration.ofDays(2),
                "sahne",
                "heidelbeeren"
        );

        Kremkuchen kremkuchen = new KremkuchenImplementation(
                new BigDecimal("25.5"),
                alex,
                allergens,
                890,
                Duration.ofDays(3),
                "sahne"
        );

        Obstkuchen obstkuchen = new ObstkuchenImplementation(
                new BigDecimal(15),
                alex,
                allergens,
                800,
                Duration.ofDays(2),
                "Kirsche"
        );

        List<Allergen> allergens2 = new ArrayList<>();
        allergens.add(Allergen.HASELNUSS);
        allergens.add(Allergen.SESAMSAMEN);

        Hersteller donna = new HerstellerImplementation("donna");
        automat.addHersteller(donna);

        Obsttorte obsttorte = new ObsttorteImplementation(
                new BigDecimal(15),
                alex,
                allergens2,
                800,
                Duration.ofDays(2),
                "Sahne",
                "Kirsche"
        );
        automat.addKuchen(kremkuchen, LocalDateTime.now());
        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(obstkuchen, LocalDateTime.now());
        automat.addKuchen(obsttorte, LocalDateTime.now());
        try {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(automatXML));
            XMLEncoder encoder = new XMLEncoder(outputStream);
            automatRepositoryJBP.saveToFile(encoder, automat);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(automatXML));
            XMLDecoder decoder = new XMLDecoder(inputStream);
            Automat automatFromFile = automatRepositoryJBP.readFromFile(decoder);
            System.out.println(automatFromFile.toString());
            System.out.println("*******");
            System.out.println(automatFromFile.getHerstellerList().toString());
            System.out.println("*******");
            System.out.println(automatFromFile.getAllEingelagertenKuchen().toString());
            System.out.println("*******");
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

    }
}
