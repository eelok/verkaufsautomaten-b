package automat.apps.simulation;



import automat.mainlib.Automat;
import automat.mainlib.kuchen.Kuchen;

import java.time.LocalDateTime;
import java.util.Random;

public class StorageImpl implements Storage {

    private final Automat freshKuchenAutomat;
    private CreateAutomatService automatFactory;

    public StorageImpl(Automat freshKuchenAutomat, CreateAutomatService automatFactory) {
        this.freshKuchenAutomat = freshKuchenAutomat;
        this.automatFactory = automatFactory;
    }

    public synchronized void put(Kuchen kuchen) throws InterruptedException {
        if (freshKuchenAutomat.isFull()) {
            wait();
        } else {
            freshKuchenAutomat.addKuchen(kuchen, LocalDateTime.now());
            System.out.println(String.format("%s-> Adding >> %s {haltbarkeit in days: %s}",
                    this.freshKuchenAutomat.getName(),
                    kuchen,
                    kuchen.getHaltbarkeit().toDays())
            );
            notifyAll();
        }
    }

    public synchronized void poll() throws InterruptedException {
        if (!freshKuchenAutomat.isFull()) {
            wait();
        } else {
            umlagernKuchen();
            notifyAll();
        }
    }

    private void umlagernKuchen() {
        Kuchen kuchenWithSmallestHaltbarkeit = freshKuchenAutomat.findKuchenWithSmallestHaltbarkeit();
        freshKuchenAutomat.removeKuchenFromAutomat(kuchenWithSmallestHaltbarkeit);

        System.out.println(String.format("%s {haltbarkeit in days: %s} form %s wird in anderer Automat umgelagert",
                kuchenWithSmallestHaltbarkeit,
                kuchenWithSmallestHaltbarkeit.getHaltbarkeit().toDays(),
                freshKuchenAutomat.getName()
        ));
        System.out.println(String.format("%s Removing >> %s", freshKuchenAutomat.getName(), kuchenWithSmallestHaltbarkeit));

        Automat oldKuchenAutomat = automatFactory.createAutomat(10);
        oldKuchenAutomat.setName("OldKuchenAutomat");
        oldKuchenAutomat.addKuchen(kuchenWithSmallestHaltbarkeit, LocalDateTime.now());

        System.out.println(String.format("%s -> Adding >> %s {haltbarkeit in days: %s}", oldKuchenAutomat.getName(), kuchenWithSmallestHaltbarkeit, kuchenWithSmallestHaltbarkeit.getHaltbarkeit().toDays()));
    }


    public static void main(String[] args) {
        CreateAutomatService automatFactory = new CreateAutomatService();
        Automat automat = automatFactory.createAutomat(10);
        automat.setName("FreshKuchenAutomat");
        Storage storage = new StorageImpl(automat, automatFactory);

        for (int i = 0; i < 1; i++) {
            new EinlagerungProducer(storage, new RandomKuchenService(new Random())).start();
        }
        for (int i = 0; i < 1; i++) {
            new AuslagerungConsumer(storage).start();
        }
    }
}
