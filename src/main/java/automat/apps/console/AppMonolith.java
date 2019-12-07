package automat.apps.console;

import automat.mainlib.kuchen.KuchenParser;
import automat.mainlib.Automat;

public class AppMonolith {

    public static void main(String[] args) {
        Automat automat = new Automat(3);
        KuchenParser kuchenParser = new KuchenParser();
        StringUtils stringUtils = new StringUtils();

        VerwaltungController verwaltungController = new VerwaltungController(automat, kuchenParser, stringUtils);

        verwaltungController.run();
    }

}
