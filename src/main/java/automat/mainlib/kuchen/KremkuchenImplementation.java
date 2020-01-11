package automat.mainlib.kuchen;

import automat.mainlib.hersteller.Hersteller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Objects;

public class KremkuchenImplementation implements Kremkuchen, Serializable {

    private BigDecimal price;
    private Hersteller hersteller;
    private Collection<Allergen> allergens;
    private int naehrwert;
    private Duration haltbarkeit;

    private String kremsorte;

    public KremkuchenImplementation() {
    }

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
    public Collection<Allergen> getAllergens() {
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

    public String getType(){
        return TypeOfKuchen.Kremkuchen.toString();
    }

    @Override
    public String toString() {
        return String.format(
                "{%s, price= %s, hersteller: %s, naehrwert= %s, allergenes: %s, haltbarkeit: %s, kremsorte: %s}",
                getType(),
                getPrice(),
                getHersteller().getName(),
                getNaehrwert(),
                getAllergens(),
                getHaltbarkeit().toDays(),
                getKremsorte()
        );
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }

    public void setAllergens(Collection<Allergen> allergens) {
        this.allergens = allergens;
    }

    public void setNaehrwert(int naehrwert) {
        this.naehrwert = naehrwert;
    }

    public void setHaltbarkeit(Duration haltbarkeit) {
        this.haltbarkeit = haltbarkeit;
    }

    public void setKremsorte(String kremsorte) {
        this.kremsorte = kremsorte;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KremkuchenImplementation that = (KremkuchenImplementation) o;
        return naehrwert == that.naehrwert &&
                Objects.equals(price, that.price) &&
                Objects.equals(hersteller, that.hersteller) &&
                Objects.equals(allergens, that.allergens) &&
                Objects.equals(haltbarkeit, that.haltbarkeit) &&
                Objects.equals(kremsorte, that.kremsorte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, hersteller, allergens, naehrwert, haltbarkeit, kremsorte);
    }
}
