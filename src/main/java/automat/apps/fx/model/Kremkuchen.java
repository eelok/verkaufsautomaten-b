package automat.apps.fx.model;

import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.KremkuchenImplementation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class Kremkuchen extends KremkuchenImplementation implements KuchenFx {

    public Kremkuchen(BigDecimal price, Hersteller hersteller, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, String kremsorte) {
        super(price, hersteller, allergens, naehrwert, haltbarkeit, kremsorte);
    }

    @Override
    public StringProperty getHaltbarkeitProperty(){
        return new SimpleStringProperty(this.getHaltbarkeit().toDays() + " days");
    }

}
