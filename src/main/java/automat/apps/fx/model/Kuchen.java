package automat.apps.fx.model;

import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.KremkuchenImplementation;
import automat.mainlib.kuchen.KuchenImplementation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class Kuchen extends KuchenImplementation implements KuchenFx {

    public Kuchen(BigDecimal price, Hersteller hersteller, Collection<Allergen> allergenes, int naehrwert, Duration haltbarkeit) {
        super(price, hersteller, allergenes, naehrwert, haltbarkeit);
    }

    @Override
    public StringProperty getHaltbarkeitProperty(){
        return new SimpleStringProperty(this.getHaltbarkeit().toDays() + " days");
    }

}
