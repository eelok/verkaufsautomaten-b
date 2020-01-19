package automat.net.common;

import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.KremkuchenImplementation;
import automat.net.common.KuchenParser;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class KuchenParserTest {

    @Test
    void test(){
        String str = " Kremkuchen 2.5 Alex Erdnuss,Haselnuss 1400 24 Sahne";
        KuchenParser kuchenParser = new KuchenParser();

        assertThat(kuchenParser.getKuchenInfo(str)).isEqualTo(new KremkuchenImplementation(
                BigDecimal.valueOf(2.50),
                new HerstellerImplementation("alex"),
                Arrays.asList(Allergen.Erdnuss, Allergen.Haselnuss),
                1400,
                Duration.ofHours(24),
                "Sahne"
        ));
    }

}