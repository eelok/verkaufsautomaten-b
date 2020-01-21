package automat.mainlib.kuchen;

import automat.mainlib.hersteller.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public interface Kuchen extends KuchenTypeToString {

    BigDecimal getPrice();

    Hersteller getHersteller();

    Collection<Allergen> getAllergens();

    int getNaehrwert();

    Duration getHaltbarkeit();

    String getType();
}
