package automat.apps.simulation.service;

import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.Obstkuchen;
import automat.mainlib.kuchen.ObstkuchenImplementation;
import automat.mainlib.kuchen.TypeOfKuchen;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RandomKuchenServiceTest {

    @Test
    void should_return_random_kuchen() {
        Random random = mock(Random.class);
        when(random.nextInt(TypeOfKuchen.values().length)).thenReturn(2);
        RandomKuchenService randomKuchenService = new RandomKuchenService(random);

        Obstkuchen obstkuchen = new ObstkuchenImplementation(
                new BigDecimal(19.30),
                new HerstellerImplementation("Donna"),
                Arrays.asList(Allergen.Gluten, Allergen.Erdnuss),
                780,
                Duration.ofDays(random.nextInt(10) + 1),
                "Himbeeren"
        );

        assertThat(randomKuchenService.getRandomKuchen()).isEqualTo(obstkuchen);
    }
}