package automat.mainlib.kuchen;

import automat.mainlib.Automat;
import automat.mainlib.Beobachter;

public class AddKuchenObserverNEW implements Beobachter {

    private Automat automat;

    public AddKuchenObserverNEW(Automat automat) {
        this.automat = automat;
        this.automat.meldeAb(this);
    }

    @Override
    public void aktualisiere() {
        int automatCapacity = automat.getStorage().size();
        int numberOfKuchenInAutomat = automat.getAllEingelagertenKuchen().size();
        if(automatCapacity - numberOfKuchenInAutomat == 1){
            System.out.println("There is only ONE place available left");
        }
    }
}
