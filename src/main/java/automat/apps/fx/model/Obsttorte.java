package automat.apps.fx.model;

import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.ObsttorteImplementation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class Obsttorte extends ObsttorteImplementation implements KuchenFx {

    public Obsttorte(BigDecimal price, Hersteller hersteller, Collection<Allergen> allergens, int naehrwert, Duration haltbarkeit, String obstsorte, String kremsorte) {
        super(price, hersteller, allergens, naehrwert, haltbarkeit, obstsorte, kremsorte);
    }

    @Override
    public StringProperty getHaltbarkeitProperty(){
        return new SimpleStringProperty(this.getHaltbarkeit().toDays() + " days");
    }

}
