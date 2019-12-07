package automat.mainlib.kuchen;

import automat.mainlib.hersteller.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class ObstkuchenImplementation implements Obstkuchen {

    private BigDecimal price;
    private Hersteller hersteller;
    private Collection<Allergen> allergens;
    private int naehrwert;
    private Duration haltbarkeit;

    private String obstsorte;

    public ObstkuchenImplementation(
            BigDecimal price,
            Hersteller hersteller,
            Collection<Allergen> allergens,
            int naehrwert,
            Duration haltbarkeit,
            String obstsorte
    ) {

        this.price = price;
        this.hersteller = hersteller;
        this.allergens = allergens;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.obstsorte = obstsorte;
    }


    @Override
    public String getObstsorte() {
        return obstsorte;
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

    public String toString(){
        return "Obstkuchen";
    }
}
