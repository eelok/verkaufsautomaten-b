package automat.apps.console.service;

import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class KuchenParserTest {

    private KuchenParser kuchenParser;

    @BeforeEach
    void setUp() {
        kuchenParser = new KuchenParser();
    }

    @Test
    void should_return_kuchen() {
        String str = "  kuchen 10.5  Donna Sesamsamen,Haselnuss 1400 24  ";

        assertThat(kuchenParser.getKuchenInfo(str)).isEqualTo(new KuchenImplementation(
                BigDecimal.valueOf(10.5),
                new HerstellerImplementation("donna"),
                Arrays.asList(Allergen.SESAMSAMEN, Allergen.HASELNUSS),
                1400,
                Duration.ofHours(24)
        ));
    }

    @Test
    void should_return_kremkuchen() {
        String str = " Kremkuchen 2.5 Alex Erdnuss,Haselnuss 1400 24 Sahne";

        assertThat(kuchenParser.getKuchenInfo(str)).isEqualTo(new KremkuchenImplementation(
                BigDecimal.valueOf(2.50),
                new HerstellerImplementation("alex"),
                Arrays.asList(Allergen.ERDNUSS, Allergen.HASELNUSS),
                1400,
                Duration.ofHours(24),
                "Sahne"
        ));
    }

    @Test
    void should_return_obstkuchen(){
        String str = "obstkuchen 5.0 Alex Gluten,Haselnuss 1400 48 Himbeeren";

        assertThat(kuchenParser.getKuchenInfo(str)).isEqualTo(new ObstkuchenImplementation(
                BigDecimal.valueOf(5.0),
                new HerstellerImplementation("alex"),
                Arrays.asList(Allergen.GLUTEN, Allergen.HASELNUSS),
                1400,
                Duration.ofHours(48),
                "Himbeeren"
        ));
    }

    @Test
    void should_return_obsttorte(){
        String str = "obsttorte 9.45 Donna Gluten,Haselnuss,Erdnuss 1400 48 Himbeeren Sahne";

        assertThat(kuchenParser.getKuchenInfo(str)).isEqualTo(new ObsttorteImplementation(
        BigDecimal.valueOf(9.45),
                new HerstellerImplementation("donna"),
                Arrays.asList(Allergen.GLUTEN, Allergen.HASELNUSS, Allergen.ERDNUSS),
                1400,
                Duration.ofHours(48),
                "Himbeeren",
                "Sahne"
        ));
    }

    @Test
    void should_return_null_when_kuchetype_ist_wrong(){
        String str = "newkuchen 9.45 Donna Gluten,Haselnuss,Erdnuss 1400 48 Himbeeren Sahne";

        assertThat(kuchenParser.getKuchenInfo(str)).isNull();
    }

    @Test
    void should_return_null_when_input_order_is_wrong(){
        String str = "kuchen Donna 9.0 Gluten,Haselnuss,Erdnuss 1400 48 Himbeeren Sahne";

        assertThat(kuchenParser.getKuchenInfo(str)).isNull();
    }

}