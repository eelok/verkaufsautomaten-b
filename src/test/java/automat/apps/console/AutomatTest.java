package automat.apps.console;

import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.Allergen;
import automat.mainlib.kuchen.Kremkuchen;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.Obstkuchen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import automat.mainlib.Automat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutomatTest {

    private Automat automat;

    private Hersteller romashka;
    private Hersteller herstellerMandarin;

    private Kuchen kuchen = mock(Kuchen.class);
    private Kuchen kremKuchen = mock(Kremkuchen.class);
    private Kuchen kremKuchen1 = mock(Kremkuchen.class);
    private Kuchen obstKuchen = mock(Obstkuchen.class);


    @BeforeEach
    void setUp() {
        automat = new Automat(4);

        romashka = new HerstellerImplementation("romashka");
        herstellerMandarin = new HerstellerImplementation("herstellerMandarin");
    }

    @Test
    void should_add_hersteller() {
        Hersteller newHersteller = mock(Hersteller.class);

        automat.addHersteller(newHersteller);

        assertThat(automat.getHerstellerList()).hasSize(1);
    }


    @Test
    void should_delete_the_hersteller() {
        automat.addHersteller(romashka);
        automat.addHersteller(herstellerMandarin);

        automat.deleteHersteller("romashka");

        assertThat(automat.getHerstellerList()).hasSize(1);
    }

    //TODO: make it work

//    @Test
//    void should_add_kuchen() {
//        addHerstellers();
//        Kuchen kuchen = mock(Kuchen.class);
//        when(kuchen.getHersteller()).thenReturn(romashka);
//
//        automat.addKuchen(kuchen, LocalDateTime.now());
//
//        assertThat(automat.getEinlagerungList()[0].getKuchen()).isEqualTo(kuchen);
//    }

    @Test
    void addKuchen_should_assign_fachnummer() {
        addHerstellers();
        Kuchen kuchen = mock(Kuchen.class);
        when(kuchen.getHersteller()).thenReturn(romashka);

        assertThat(automat.addKuchen(kuchen, LocalDateTime.now()).getFachnummer()).isEqualTo(0);
    }

    @Test
    void addKuchen_should_assign_date() {
        addHerstellers();
        Kuchen kuchen = mock(Kuchen.class);
        when(kuchen.getHersteller()).thenReturn(herstellerMandarin);

        LocalDateTime date = LocalDateTime.now();

        assertThat(automat.addKuchen(kuchen, date).getEinlagerungsDatum()).isEqualTo(date);
    }

    @Test
    void should_add_kuchen_when_only_one_place_left(){
        automat = new Automat(2);
        addHerstellers();

        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(automat.getAllEingelagertenKuchen()).isEqualTo(
                Arrays.asList(kuchen, kremKuchen)
        );
    }


    @Test
    void addKuchen_should_throw_exception_when_no_hersteller() {
        addHerstellers();

        Kuchen kremKuchen = mock(Kremkuchen.class);
        when(kremKuchen.getHersteller()).thenReturn(new HerstellerImplementation("apple"));

        assertThatThrownBy(() -> automat.addKuchen(kremKuchen, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(
                        "No such manufacturer %s. Please add manufacturer %s.",
                        kremKuchen.getHersteller().getName(),
                        kremKuchen.getHersteller().getName())
                );
    }

    //TODO: make it work
//    @Test
//    void should_add_kuchen_after_a_kuchen_was_deleted(){
//        addHerstellers();
//
//        when(kuchen.getHersteller()).thenReturn(romashka);
//        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);
//        when(kremKuchen1.getHersteller()).thenReturn(herstellerMandarin);
//        when(obstKuchen.getHersteller()).thenReturn(romashka);
//
//        automat.addKuchen(kuchen, LocalDateTime.now());
//        automat.addKuchen(kremKuchen, LocalDateTime.now());
//        automat.addKuchen(kremKuchen1, LocalDateTime.now());
//        automat.addKuchen(obstKuchen, LocalDateTime.now());
//
//        automat.removeKuchenFromAutomat(kremKuchen);
//        automat.removeKuchenFromAutomat(obstKuchen);
//
//        Kuchen newKucnen = mock(Kuchen.class);
//        when(newKucnen.getHersteller()).thenReturn(romashka);
//
//        automat.addKuchen(newKucnen, LocalDateTime.now());
//
//        assertThat(automat.getAllEingelagertenKuchen()).isEqualTo(Arrays.asList(kuchen, newKucnen, kremKuchen1));
//    }



    @Test
    void addKuchen_should_throw_exception_when_no_place_left() {

        automat = new Automat(0);
        addHerstellers();
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);

        assertThatThrownBy(() -> automat.addKuchen(kremKuchen, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Der Automat ist voll");
    }


    @Test
    void should_return_all_kuchen_in_automat(){
        automat = new Automat(2);
        addHerstellers();

        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(automat.getAllEingelagertenKuchen()).isEqualTo(Arrays.asList(
            kuchen, kremKuchen
        ));
    }


    @Test
    void should_get_list_with_kuchen_of_certain_type() {
        automat = new Automat(3);
        addHerstellers();

        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(automat.getKuchenOfType(Kremkuchen.class)).isEqualTo(Arrays.asList(kremKuchen, kremKuchen));
    }

    @Test
    void should_return_number_of_Kuchen_from_one_hersteller() {
        Hersteller romashka = new HerstellerImplementation("romashka");
        Hersteller mandarin = new HerstellerImplementation("herstellerMandarin");

        automat.addHersteller(romashka);
        automat.addHersteller(mandarin);

        List<Kuchen> kuchens = Arrays.asList(kuchen, kremKuchen, kremKuchen1);
        kuchens.forEach(k -> when(k.getHersteller()).thenReturn(romashka));
        kuchens.forEach(k -> automat.addKuchen(k, LocalDateTime.now()));

        when(obstKuchen.getHersteller()).thenReturn(mandarin);

        assertThat(automat.getAnzahlKuchenZuHersteller("romashka")).isEqualTo(3);
    }

    @Test
    void should_return_fachnum() {
        when(obstKuchen.getHersteller()).thenReturn(herstellerMandarin);
        automat.addHersteller(herstellerMandarin);
        automat.addKuchen(obstKuchen, LocalDateTime.now());

        assertThat(automat.getFachnummerZuBestimmtenKuchen(obstKuchen)).isEqualTo(0);
    }


    @Test
    void should_get_rest_haltbarkei_zu_bestimmten_kuchen() {
        addHerstellers();

        when(obstKuchen.getHersteller()).thenReturn(romashka);

        when(obstKuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(4L));
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));

        automat.addKuchen(obstKuchen, now.minusDays(2L));

        assertThat(automat.getRestHaltbarkeitZuBestimmtenKuchen(obstKuchen, now))
                .isEqualTo(Duration.ofDays(2));
    }

    //TODO: make it work
//    @Test
//    void should_remove_kuchen() {
//        addHerstellers();
//
//        when(kuchen.getHersteller()).thenReturn(romashka);
//        when(kremKuchen.getHersteller()).thenReturn(romashka);
//
//        automat.addKuchen(kuchen, LocalDateTime.now());
//        automat.addKuchen(kremKuchen, LocalDateTime.now());
//
//        automat.removeKuchenFromAutomat(kremKuchen);
//
//        assertThat(automat.getAllEingelagertenKuchen()).isEqualTo(Arrays.asList(kuchen));
//    }


    @Test
    void should_return_all_allergene_in_automat() {
        addHerstellers();

        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);
        when(kremKuchen1.getHersteller()).thenReturn(herstellerMandarin);
        when(obstKuchen.getHersteller()).thenReturn(romashka);

        addKuchenToVerwaltung(Arrays.asList(kuchen, kremKuchen, kremKuchen1, obstKuchen));

        when(kuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(kremKuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten, Allergen.Haselnuss));
        when(kremKuchen1.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(obstKuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten, Allergen.Haselnuss));

        assertThat(automat.getAllergenenInAutomat()).isEqualTo(Arrays.asList(Allergen.Gluten, Allergen.Haselnuss));
    }

    @Test
    void should_return_allergene_not_in_Automat() {
        addHerstellers();
        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);
        when(kremKuchen1.getHersteller()).thenReturn(herstellerMandarin);
        when(obstKuchen.getHersteller()).thenReturn(romashka);

        addKuchenToVerwaltung(Arrays.asList(kuchen, kremKuchen, kremKuchen1, obstKuchen));

        when(kuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(kremKuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten, Allergen.Haselnuss));
        when(kremKuchen1.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(obstKuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten, Allergen.Haselnuss));

        assertThat(automat.getAllergenenNotInAutomat()).isEqualTo(Arrays.asList(Allergen.Erdnuss, Allergen.Sesamsamen));
    }

    private void addHerstellers() {
        automat.addHersteller(romashka);
        automat.addHersteller(herstellerMandarin);
    }

    private void addKuchenToVerwaltung(List<Kuchen> kuchenList) {
        kuchenList.forEach(kuchen -> automat.addKuchen(kuchen, LocalDateTime.now()));
    }

}
