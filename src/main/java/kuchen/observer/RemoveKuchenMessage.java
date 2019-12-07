package kuchen.observer;

import automat.Message;
import kuchen.Kuchen;

public class RemoveKuchenMessage implements Message {
    private Kuchen kuchen;

    public RemoveKuchenMessage(Kuchen kuchen) {
        this.kuchen = kuchen;
    }

    public Kuchen getKuchen() {
        return kuchen;
    }
}
