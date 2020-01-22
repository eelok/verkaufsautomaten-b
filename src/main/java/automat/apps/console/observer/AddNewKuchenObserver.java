package automat.apps.console.observer;

import automat.apps.console.Printer;
import automat.mainlib.Automat;
import automat.mainlib.AutomatInterface;
import automat.mainlib.Beobachter;

public class AddNewKuchenObserver implements Beobachter {

    private AutomatInterface automat;
    private Printer printer;

    public AddNewKuchenObserver(AutomatInterface automat, Printer printer) {
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
