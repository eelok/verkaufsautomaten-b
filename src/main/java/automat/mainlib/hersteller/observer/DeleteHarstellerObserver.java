package automat.mainlib.hersteller.observer;

import automat.mainlib.Automat;
import automat.mainlib.Beobachter;

public class DeleteHarstellerObserver implements Beobachter {

    private Automat automat;

    public DeleteHarstellerObserver(Automat automat) {
        this.automat = automat;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        System.out.println(String.format("Manufacturer %s was deleted", automat.getMessage()));
    }
}
