package automat.apps.fx.model;

import automat.mainlib.kuchen.Kuchen;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

public interface EinlagerungEntryFX {

    ////todo добавила <Kuchen> из mainlib
    ObjectProperty<Kuchen> getKuchenProperty();
    IntegerProperty getFachProperty();

}
