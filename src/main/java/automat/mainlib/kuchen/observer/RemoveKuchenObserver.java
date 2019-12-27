package automat.mainlib.kuchen.observer;

import automat.mainlib.Automat;
import automat.mainlib.Beobachter;

public class RemoveKuchenObserver implements Beobachter {

    private Automat automat;

    public RemoveKuchenObserver(Automat automat) {
        this.automat = automat;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        System.out.println("kuchen was deleted");
    }

}
