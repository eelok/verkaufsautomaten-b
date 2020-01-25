package automat.apps.simulation.service;

import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomKuchenServiceTest {

    private RandomKuchenService randomKuchenService;

    @Mock
    private Random random;

    @BeforeEach
    void setUp(){
        randomKuchenService  = new RandomKuchenService(random);
    }

    @Test
    void should_return_random_kuchen() {
        when(random.nextInt(TypeOfKuchen.values().length)).thenReturn(0);

        Kuchen kuchen = new KuchenImplementation(
                new BigDecimal("15.99"),
                new HerstellerImplementation("Alex"),
                Collections.singletonList(Allergen.Gluten),
                1000,
                Duration.ofDays(random.nextInt(10) + 1)
        );

        assertThat(randomKuchenService.getRandomKuchen()).isEqualTo(kuchen);
    }

    @Test
    void should_return_random_kremkuchen() {
        when(random.nextInt(TypeOfKuchen.values().length)).thenReturn(1);

        Kremkuchen kremkuchenImplementation = new KremkuchenImplementation(
                new BigDecimal("15.60"),
                new HerstellerImplementation("Alex"),
                Arrays.asList(Allergen.Gluten, Allergen.Erdnuss, Allergen.Haselnuss),
                1000,
                Duration.ofDays(random.nextInt(10) + 1),
                "Sahne"
        );

        assertThat(randomKuchenService.getRandomKuchen()).isEqualTo(kremkuchenImplementation);
    }

    @Test
    void should_return_random_obstkuchen() {
        when(random.nextInt(TypeOfKuchen.values().length)).thenReturn(2);

        Obstkuchen obstkuchen = new ObstkuchenImplementation(
                new BigDecimal("19.30"),
                new HerstellerImplementation("Donna"),
                Arrays.asList(Allergen.Gluten, Allergen.Erdnuss),
                780,
                Duration.ofDays(random.nextInt(10) + 1),
                "Himbeeren"
        );

        assertThat(randomKuchenService.getRandomKuchen()).isEqualTo(obstkuchen);
    }

    @Test
    void should_return_random_obsttorte() {
        when(random.nextInt(TypeOfKuchen.values().length)).thenReturn(3);

        Obsttorte obsttorte = new ObsttorteImplementation(
                new BigDecimal("22.10"),
                new HerstellerImplementation("Donna"),
                Arrays.asList(Allergen.Gluten, Allergen.Erdnuss, Allergen.Haselnuss),
                1000,
                Duration.ofDays(random.nextInt(10) + 1),
                "Blaubeeren",
                "Butter"
        );

        assertThat(randomKuchenService.getRandomKuchen()).isEqualTo(obsttorte);
    }

}