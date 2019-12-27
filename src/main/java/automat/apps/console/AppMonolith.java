package automat.apps.console;

import automat.apps.console.service.StringUtils;
import automat.mainlib.hersteller.observer.DeleteHarstellerObserver;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.hersteller.observer.AddHerstellerObserver;
import automat.mainlib.kuchen.observer.AddNewKuchenObserver;
import automat.mainlib.kuchen.observer.RemoveKuchenObserver;

import java.util.ArrayList;

public class AppMonolith {

    public static void main(String[] args) {
        Automat automat = new Automat(3, new ArrayList<>(), new ArrayList<>());
        KuchenParser kuchenParser = new KuchenParser();
        StringUtils stringUtils = new StringUtils();

        AddHerstellerObserver addHerstellerObserver = new AddHerstellerObserver(automat);
        DeleteHarstellerObserver deleteHarstellerObserver = new DeleteHarstellerObserver(automat);
        AddNewKuchenObserver addNewKuchenObserver = new AddNewKuchenObserver(automat);
        RemoveKuchenObserver removeKuchenObserver = new RemoveKuchenObserver(automat);

        VerwaltungController verwaltungController = new VerwaltungController(automat, kuchenParser, stringUtils);

        verwaltungController.run();
    }

}
