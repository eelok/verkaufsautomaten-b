package automat;

import kuchen.KuchenParser;

public class AppMonolith {

    public static void main(String[] args) {
        Verwaltung verwaltung = new Verwaltung(3);
        KuchenParser kuchenParser = new KuchenParser();
        StringUtils stringUtils = new StringUtils();

        VerwaltungController verwaltungController = new VerwaltungController(verwaltung, kuchenParser, stringUtils);

        verwaltungController.run();
    }

}
