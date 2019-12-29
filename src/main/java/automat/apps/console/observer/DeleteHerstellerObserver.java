package automat.apps.console.observer;

import automat.apps.console.Printer;
import automat.mainlib.Automat;
import automat.mainlib.Beobachter;

public class DeleteHerstellerObserver implements Beobachter {

    private Automat automat;
    private Printer printer;


    public DeleteHerstellerObserver(Automat automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        printer.println(String.format("Manufacturer %s was deleted", automat.getMessage()));
    }
}
