package automat.mainlib.kuchen.observer;

import automat.mainlib.Automat;
import automat.mainlib.Beobachter;

public class AddNewKuchenObserver implements Beobachter {

    private Automat automat;

    public AddNewKuchenObserver(Automat automat) {
        this.automat = automat;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        int automatCapacity = automat.getPlatzImAutomat();
        int numberOfKuchenInAutomat = automat.getAllEingelagertenKuchen().size();
        if (automatCapacity - numberOfKuchenInAutomat == 1) {
            System.out.println("There is only one place available left");
        }
        System.out.println(String.format("%s was added", automat.getMessage()));
    }

}
