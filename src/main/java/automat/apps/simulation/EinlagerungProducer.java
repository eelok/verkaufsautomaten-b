package automat.apps.simulation;

import automat.apps.simulation.service.RandomKuchenService;

public class EinlagerungProducer extends Thread {

    private Storage storage;
    private RandomKuchenService randomKuchenService;

    public EinlagerungProducer(Storage storage, RandomKuchenService randomKuchenService) {
        this.storage = storage;
        this.randomKuchenService = randomKuchenService;
    }
    public void run() {
        try {
            while (true) {
                this.storage.put(randomKuchenService.getRandomKuchen());
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
