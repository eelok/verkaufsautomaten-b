package automat.apps.fx.model;

import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.KremkuchenImplementation;
import automat.mainlib.kuchen.ObstkuchenImplementation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class Obstkuchen extends ObstkuchenImplementation implements KuchenFx {

    public Obstkuchen(BigDecimal price, Hersteller hersteller, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, String obstsorte) {
        super(price, hersteller, allergens, naehrwert, haltbarkeit, obstsorte);
    }

    @Override
    public StringProperty getHaltbarkeitProperty(){
        return new SimpleStringProperty(this.getHaltbarkeit().toDays() + " days");
    }

}
