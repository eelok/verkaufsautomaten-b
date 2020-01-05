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
    private Collection<Allergen> allergens;
    private Duration haltbarkeit;

    public KuchenImplementation() {
    }

    public KuchenImplementation(
            BigDecimal price,
            Hersteller hersteller,
            Collection<Allergen> allergens,
            int naehrwert,
            Duration haltbarkeit) {
        this.price = price;
        this.hersteller = hersteller;
        this.naehrwert = naehrwert;
        this.allergens = allergens;
        this.haltbarkeit = haltbarkeit;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Hersteller getHersteller() {
        return hersteller;
    }

    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    public Collection<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(Collection<Allergen> allergens) {
        this.allergens = allergens;
    }

    @Override
    public int getNaehrwert() {
        return naehrwert;
    }

    public void setNaehrwert(int naehrwert) {
        this.naehrwert = naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return haltbarkeit;
    }

    public void setHaltbarkeit(Duration haltbarkeit) {
        this.haltbarkeit = haltbarkeit;
    }

    @Override
    public String getType() {
        return TypeOfKuchen.Kuchen.toString();
    }

    @Override
    public String toString() {
        return "KuchenImplementation{" +
                "price=" + price +
                ", hersteller=" + hersteller +
                ", naehrwert=" + naehrwert +
                ", allergenes=" + allergens +
                ", haltbarkeit=" + haltbarkeit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KuchenImplementation that = (KuchenImplementation) o;
        return naehrwert == that.naehrwert &&
                Objects.equals(price, that.price) &&
                Objects.equals(hersteller, that.hersteller) &&
                Objects.equals(allergens, that.allergens) &&
                Objects.equals(haltbarkeit, that.haltbarkeit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, hersteller, naehrwert, allergens, haltbarkeit);
    }
}
