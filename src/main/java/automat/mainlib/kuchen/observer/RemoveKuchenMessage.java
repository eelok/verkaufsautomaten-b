package automat.mainlib.kuchen.observer;

import automat.apps.console.Message;
import automat.mainlib.kuchen.Kuchen;

public class RemoveKuchenMessage implements Message {
    private Kuchen kuchen;

    public RemoveKuchenMessage(Kuchen kuchen) {
        this.kuchen = kuchen;
    }

    public Kuchen getKuchen() {
        return kuchen;
    }
}
