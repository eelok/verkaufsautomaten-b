package automat.apps.fx.model;

import automat.mainlib.kuchen.Kuchen;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

import java.time.LocalDateTime;

public class EinlagerungEntry extends automat.mainlib.EinlagerungEntry implements EinlagerungEntryFX {

    private ObjectProperty<Kuchen> kuchen;
    private IntegerProperty fachnummer;

    public EinlagerungEntry(LocalDateTime einlagerungsDatum, Kuchen kuchen, int fachnummer) {
        super(einlagerungsDatum, kuchen, fachnummer);
    }
//todo добавила <Kuchen> из mainlib
    public ObjectProperty<Kuchen> getKuchenProperty(){
        return this.kuchen;
    }

    public IntegerProperty getFachProperty(){
        return this.fachnummer;
    }
}
