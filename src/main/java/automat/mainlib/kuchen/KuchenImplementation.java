package automat.mainlib.kuchen;

import automat.mainlib.hersteller.Hersteller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Objects;

public class KuchenImplementation implements Kuchen, Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KuchenImplementation that = (KuchenImplementation) o;
        return naehrwert == that.naehrwert &&
                Objects.equals(price, that.price) &&
                Objects.equals(hersteller, that.hersteller) &&
                Objects.equals(allergenes, that.allergenes) &&
                Objects.equals(haltbarkeit, that.haltbarkeit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, hersteller, naehrwert, allergenes, haltbarkeit);
    }
}
