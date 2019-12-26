package automat.mainlib.hersteller.observer;

import automat.mainlib.Automat;
import automat.mainlib.Beobachter;

public class AddHerstellerObserver implements Beobachter {

    private Automat automat;

    public AddHerstellerObserver(Automat automat) {
        this.automat = automat;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        String message = automat.getMessage();
        System.out.println(String.format("Manufacturer %s was added", message));
    }
}
