package automat.apps.console.observer;

import automat.apps.console.service.Printer;
import automat.mainlib.Automat;
import automat.mainlib.interfaceSubBeob.Beobachter;

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
