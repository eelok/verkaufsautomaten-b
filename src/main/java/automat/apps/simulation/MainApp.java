package automat.apps.simulation;

import automat.apps.simulation.service.CreateAutomatService;
import automat.apps.simulation.service.RandomKuchenService;
import automat.apps.simulation.service.UmlagerungService;
import automat.mainlib.Automat;

import java.util.Random;

public class MainApp {

    public static void main(String[] args) {
        CreateAutomatService automatFactory = new CreateAutomatService();
        Automat automat = automatFactory.createAutomat(10);
        UmlagerungService umlagerungService = new UmlagerungService(automat, automatFactory);
        StorageImpl storage = new StorageImpl(automat, umlagerungService);

        for (int i = 0; i < 1; i++) {
            new EinlagerungProducer(storage, new RandomKuchenService(new Random())).start();
        }
        for (int i = 0; i < 1; i++) {
            new AuslagerungConsumer(storage).start();
        }
    }
}
