package automat.apps.simulation.service;

import automat.mainlib.Automat;
import automat.mainlib.hersteller.HerstellerImplementation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateAutomatServiceTest {

    @Test
    void should_return_automat(){
        CreateAutomatService createAutomatService = new CreateAutomatService();

        Automat expected = new Automat(0);
        expected.addHersteller(new HerstellerImplementation("Alex"));
        expected.addHersteller(new HerstellerImplementation("Donna"));
        assertThat(createAutomatService.createAutomat(0)).isEqualTo(expected);
    }

}