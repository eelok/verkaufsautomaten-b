package automat.apps.console.observer;

import automat.apps.console.Printer;
import automat.mainlib.AutomatInterface;
import automat.mainlib.Beobachter;

public class RemoveKuchenObserver implements Beobachter {

    private AutomatInterface automat;
    private Printer printer;

    public RemoveKuchenObserver(AutomatInterface automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        printer.println(String.format("%s was deleted", automat.getMessage()));
    }

}
