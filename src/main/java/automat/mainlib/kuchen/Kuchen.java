package automat.mainlib.kuchen;

import automat.mainlib.hersteller.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public interface Kuchen {

    BigDecimal getPrice();

    Hersteller getHersteller();

    Collection<Allergen> getAllergenes();

    int getNaehrwert();

    Duration getHaltbarkeit();

    String getType();
}
