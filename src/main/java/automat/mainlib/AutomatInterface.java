package automat.mainlib;

import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Kuchen;

import java.time.LocalDateTime;
import java.util.List;

public interface AutomatInterface {

    void addHersteller(Hersteller hersteller);

    EinlagerungEntry addKuchen(Kuchen newKuchen, LocalDateTime date);

    List<String> getAllKuchenWithFachNum();

    List<String> getHerstellerWithNumberOfKuchen();

    void deleteHersteller(String name);

    EinlagerungEntry removeKuchenFromAutomat(int fachNummer);

}
