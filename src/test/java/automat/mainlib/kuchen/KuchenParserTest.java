package automat.mainlib.kuchen;

import automat.apps.console.service.KuchenParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KuchenParserTest {

    private KuchenParser kuchenParser;

    @BeforeEach
    void setUp() {
        kuchenParser = new KuchenParser();
    }

    @Test
    void getKuchenInfo_should_return_Kremkuchen() {
        String userInput = "Kremkuchen 2.5 Alex Erdnuss,Haselnuss 1400 24 Sahne";

        assertThat(kuchenParser.getKuchenInfo(userInput)).isInstanceOf(Kremkuchen.class);
    }

}
