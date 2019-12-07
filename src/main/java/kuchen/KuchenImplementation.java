package kuchen;

import hersteller.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Objects;

public class KuchenImplementation implements Kuchen {

    private BigDecimal price;
    private Hersteller hersteller;
    private int naehrwert;
    private Collection<Allergen> allergenes;
    private Duration haltbarkeit;

    public KuchenImplementation(
            BigDecimal price,
            Hersteller hersteller,
            Collection<Allergen> allergenes,
            int naehrwert,
            Duration haltbarkeit)
    {
        this.price = price;
        this.hersteller = hersteller;
        this.naehrwert = naehrwert;
        this.allergenes = allergenes;
        this.haltbarkeit = haltbarkeit;
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
        return allergenes;
    }

    @Override
    public int getNaehrwert() {
        return naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return haltbarkeit;
    }

    public String toString(){
        return "kuchen";
    }
}
