package automat.apps.simulation.service;


import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;

public class CreateAutomatService {

    public Automat createAutomat(int place) {
        Automat automat = new Automat(place);
        addManufacturer(automat);

        return automat;
    }

    private static void addManufacturer(Automat automat) {
        Hersteller alex = new HerstellerImplementation("Alex");
        Hersteller donna = new HerstellerImplementation("Donna");
        automat.addHersteller(alex);
        automat.addHersteller(donna);
    }

}
