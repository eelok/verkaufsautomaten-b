package automat.mainlib.hersteller.observer;

import automat.mainlib.Automat;
import automat.mainlib.Beobachter;
import automat.mainlib.Printer;

public class DeleteHarstellerObserver implements Beobachter {

    private Automat automat;
    private Printer printer;


    public DeleteHarstellerObserver(Automat automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        printer.println(String.format("Manufacturer %s was deleted", automat.getMessage()));
    }
}
