package automat.apps.simulation;

public class AuslagerungConsumer extends Thread {
    private Storage storage;

    public AuslagerungConsumer(Storage storage) {
        this.storage = storage;
    }

    public void run() {
        try {
            while (true) {
                this.storage.poll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
