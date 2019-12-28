package automat.mainlib.kuchen.observer;

import automat.mainlib.Automat;
import automat.mainlib.Beobachter;
import automat.mainlib.Printer;

public class RemoveKuchenObserver implements Beobachter {

    private Automat automat;
    private Printer printer;

    public RemoveKuchenObserver(Automat automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        printer.println(String.format("%s was deleted", automat.getMessage()));
    }

}
