package automat.apps.simulation;

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
            e.printStackTrace();
        }
    }
}
