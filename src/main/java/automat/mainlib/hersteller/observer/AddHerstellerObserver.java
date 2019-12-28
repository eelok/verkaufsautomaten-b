package automat.mainlib.hersteller.observer;

import automat.mainlib.Automat;
import automat.mainlib.Beobachter;
import automat.mainlib.Printer;

public class AddHerstellerObserver implements Beobachter {

    private Automat automat;
    private Printer printer;

    public AddHerstellerObserver(Automat automat, Printer printer) {
        this.automat = automat;
        this.automat.meldeAn(this);
        this.printer = printer;
    }

    @Override
    public void aktualisiere() {
        printer.println(String.format("Manufacturer %s was added", automat.getMessage()));
    }
}
