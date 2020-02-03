package automat.apps.console.observer;

import automat.apps.console.service.Printer;
import automat.mainlib.Automat;
import automat.mainlib.Beobachter;

public class AddNewKuchenObserver implements Beobachter {

    private Automat automat;
    private Printer printer;

    public AddNewKuchenObserver(Automat automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        int automatCapacity = automat.getPlatzImAutomat();
        int numberOfKuchenInAutomat = automat.getAllEingelagertenKuchen().size();
        if (automatCapacity - numberOfKuchenInAutomat == 1) {
            printer.println("There is only one place available left");
        }
        printer.println(String.format("%s was added", automat.getMessage()));
    }

}
