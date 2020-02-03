package automat.apps.simulation.service;

import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RandomKuchenService {

    private Random random;

    public RandomKuchenService(Random random) {
        this.random = random;
    }

    public Kuchen getRandomKuchen() {
        int index = random.nextInt(TypeOfKuchen.values().length);
        switch (TypeOfKuchen.values()[index]) {
            case Kuchen:
                return createKuchen();
            case Kremkuchen:
                return createKremkuchen();
            case Obstkuchen:
                return createObstkuchen();
            case Obsttorte:
                return createObsttorte();
            default:
                return null;
        }
    }

    private Kuchen createKuchen() {
        return new KuchenImplementation(
                new BigDecimal("15.99"),
                new HerstellerImplementation("Alex"),
                Collections.singletonList(Allergen.GLUTEN),
                1000,
                Duration.ofDays(random.nextInt(10) + 1)
        );
    }

    private Obsttorte createObsttorte() {
        return new ObsttorteImplementation(
                new BigDecimal("22.10"),
                new HerstellerImplementation("Donna"),
                Arrays.asList(Allergen.GLUTEN, Allergen.ERDNUSS, Allergen.HASELNUSS),
                1000,
                Duration.ofDays(random.nextInt(10) + 1),
                "Blaubeeren",
                "Butter"
        );
    }

    private Obstkuchen createObstkuchen() {
        return new ObstkuchenImplementation(
                new BigDecimal("19.30"),
                new HerstellerImplementation("Donna"),
                Arrays.asList(Allergen.GLUTEN, Allergen.ERDNUSS),
                780,
                Duration.ofDays(random.nextInt(10) + 1),
                "Himbeeren");
    }

    private Kremkuchen createKremkuchen() {
        return new KremkuchenImplementation(
                new BigDecimal("15.60"),
                new HerstellerImplementation("Alex"),
                Arrays.asList(Allergen.GLUTEN, Allergen.ERDNUSS, Allergen.HASELNUSS),
                1000,
                Duration.ofDays(random.nextInt(10) + 1),
                "Sahne");
    }
}
