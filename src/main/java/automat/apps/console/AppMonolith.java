package automat.apps.console;

import automat.mainlib.hersteller.observer.DeleteHarstellerObserver;
import automat.mainlib.kuchen.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.hersteller.observer.AddHerstellerObserver;

import java.util.ArrayList;

public class AppMonolith {

    public static void main(String[] args) {
        Automat automat = new Automat(3, new ArrayList<>(), new ArrayList<>());
        KuchenParser kuchenParser = new KuchenParser();
        StringUtils stringUtils = new StringUtils();

        AddHerstellerObserver addHerstellerObserver = new AddHerstellerObserver(automat);
        DeleteHarstellerObserver deleteHarstellerObserver = new DeleteHarstellerObserver(automat);

        VerwaltungController verwaltungController = new VerwaltungController(automat, kuchenParser, stringUtils);

        verwaltungController.run();
    }

}
