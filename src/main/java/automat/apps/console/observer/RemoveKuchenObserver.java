package automat.apps.console.observer;

import automat.apps.console.service.Printer;
import automat.mainlib.Automat;
import automat.mainlib.interfaceSubBeob.Beobachter;

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
