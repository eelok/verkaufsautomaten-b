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
import java.util.List;

public class MainJBP {

    public static void main(String[] args) {
        AutomatRepositoryJBP automatRepositoryJBP = new AutomatRepositoryJBP();
        Automat automat = new Automat(2);
        automat.setName("kuchen automat");
        File automatXML = new File("automat.xml");

        List<Allergen> allergens = new ArrayList<>();
        allergens.add(Allergen.Erdnuss);
        allergens.add(Allergen.Gluten);
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

        automat.addKuchen(kuchen, LocalDateTime.now());

        try {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(automatXML));
            XMLEncoder encoder = new XMLEncoder(outputStream);
            automatRepositoryJBP.saveToFile(encoder, automat);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(automatXML));
            XMLDecoder decoder = new XMLDecoder(inputStream);
            Automat automatFromFile = automatRepositoryJBP.readFromFile(decoder);
            System.out.println(automatFromFile.toString());
            System.out.println(automat.getAllEingelagertenKuchen().toString());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
