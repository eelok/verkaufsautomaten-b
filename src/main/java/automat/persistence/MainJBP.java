package automat.persistence;

import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.KuchenImplementation;
import automat.mainlib.kuchen.ObstkuchenImplementation;

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
        AutomatJBP automatJBP = new AutomatJBP();
        Automat automat = new Automat(2);
        File automatXML = new File("automat.xml");

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
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(automatXML));
            XMLEncoder encoder = new XMLEncoder(outputStream);
            automatJBP.saveToFile(encoder, automat);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(automatXML));
            XMLDecoder decoder = new XMLDecoder(inputStream);
            Automat automatFromFile = automatJBP.readFromFile(decoder);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
