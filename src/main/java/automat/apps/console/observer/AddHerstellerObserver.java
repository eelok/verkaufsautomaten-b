package automat.apps.console.observer;

import automat.apps.console.Printer;
import automat.mainlib.Automat;
import automat.mainlib.AutomatInterface;
import automat.mainlib.Beobachter;

public class AddHerstellerObserver implements Beobachter {

    private AutomatInterface automat;
    private Printer printer;

    public AddHerstellerObserver(AutomatInterface automat, Printer printer) {
        this.automat = automat;
        this.automat.meldeAn(this);
        this.printer = printer;
    }

    @Override
    public void aktualisiere() {
        printer.println(String.format("Manufacturer %s was added", automat.getMessage()));
    }
}
