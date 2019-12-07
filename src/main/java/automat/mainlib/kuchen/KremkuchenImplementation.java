package automat.mainlib.kuchen;

import automat.mainlib.hersteller.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class KremkuchenImplementation implements Kremkuchen {

    private BigDecimal price;
    private Hersteller hersteller;
    private Collection<Allergen> allergens;
    private int naehrwert;
    private Duration haltbarkeit;

    private String kremsorte;

    public KremkuchenImplementation(
            BigDecimal price,
            Hersteller hersteller,
            Collection<Allergen> allergens,
            int naehrwert,
            Duration haltbarkeit,
            String kremsorte
    ) {
        this.price = price;
        this.hersteller = hersteller;
        this.allergens = allergens;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte() {
        return kremsorte;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public Hersteller getHersteller() {
        return hersteller;
    }

    @Override
    public Collection<Allergen> getAllergenes() {
        return allergens;
    }

    @Override
    public int getNaehrwert() {
        return naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return haltbarkeit;
    }

    @Override
    public String toString() {
        return "Kremkuchen";
    }
}
