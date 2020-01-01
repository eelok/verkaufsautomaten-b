package automat.apps.simulation;

import automat.apps.simulation.service.UmlagerungService;
import automat.mainlib.Automat;
import automat.mainlib.kuchen.Kuchen;

import java.time.LocalDateTime;

public class StorageImpl implements Storage {

    private final Automat freshKuchenAutomat;
    private UmlagerungService umlagerungService;


    public StorageImpl(Automat freshKuchenAutomat, UmlagerungService umlagerungService) {
        this.freshKuchenAutomat = freshKuchenAutomat;
        this.umlagerungService = umlagerungService;
    }

    public synchronized void put(Kuchen kuchen) throws InterruptedException {
        if (freshKuchenAutomat.isFull()) {
            wait();
        } else {
            freshKuchenAutomat.addKuchen(kuchen, LocalDateTime.now());
            System.out.println(String.format("%s-> Adding >> %s {haltbarkeit in days: %s}",
                    this.freshKuchenAutomat.getName(),
                    kuchen.getType(),
                    kuchen.getHaltbarkeit().toDays())
            );
            notifyAll();
        }
    }

    public synchronized void poll() throws InterruptedException {
        if (!freshKuchenAutomat.isFull()) {
            wait();
        } else {
            umlagerungService.umlagernKuchen();
            notifyAll();
        }
    }
}
