package automat;

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
import automat.mainlib.Verwaltung;

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
class VerwaltungTest {

    private Verwaltung verwaltung;

    private Hersteller romashka;
    private Hersteller herstellerMandarin;

    private Kuchen kuchen = mock(Kuchen.class);
    private Kuchen kremKuchen = mock(Kremkuchen.class);
    private Kuchen kremKuchen1 = mock(Kremkuchen.class);
    private Kuchen obstKuchen = mock(Obstkuchen.class);


    @BeforeEach
    void setUp() {
        verwaltung = new Verwaltung(4);

        romashka = new HerstellerImplementation("romashka");
        herstellerMandarin = new HerstellerImplementation("herstellerMandarin");
    }

    @Test
    void should_add_hersteller() {
        Hersteller newHersteller = mock(Hersteller.class);

        verwaltung.addHersteller(newHersteller);

        assertThat(verwaltung.getHerstellerList()).hasSize(1);
    }


    @Test
    void should_delete_the_hersteller() {
        verwaltung.addHersteller(romashka);
        verwaltung.addHersteller(herstellerMandarin);

        verwaltung.deleteHersteller("romashka");

        assertThat(verwaltung.getHerstellerList()).hasSize(1);
    }


    @Test
    void should_add_kuchen() {
        addHerstellers();
        Kuchen kuchen = mock(Kuchen.class);
        when(kuchen.getHersteller()).thenReturn(romashka);

        verwaltung.addKuchen(kuchen, LocalDateTime.now());

        assertThat(verwaltung.getEinlagerungList()[0].getKuchen()).isEqualTo(kuchen);
    }

    @Test
    void addKuchen_should_assign_fachnummer() {
        addHerstellers();
        Kuchen kuchen = mock(Kuchen.class);
        when(kuchen.getHersteller()).thenReturn(romashka);

        assertThat(verwaltung.addKuchen(kuchen, LocalDateTime.now()).getFachnummer()).isEqualTo(0);
    }

    @Test
    void addKuchen_should_assign_date() {
        addHerstellers();
        Kuchen kuchen = mock(Kuchen.class);
        when(kuchen.getHersteller()).thenReturn(herstellerMandarin);

        LocalDateTime date = LocalDateTime.now();

        assertThat(verwaltung.addKuchen(kuchen, date).getEinlagerungsDatum()).isEqualTo(date);
    }

    @Test
    void should_add_kuchen_when_only_one_place_left(){
        verwaltung = new Verwaltung(2);
        addHerstellers();

        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);

        verwaltung.addKuchen(kuchen, LocalDateTime.now());
        verwaltung.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(verwaltung.getAllEingelagertenKuchen()).isEqualTo(
                Arrays.asList(kuchen, kremKuchen)
        );
    }


    @Test
    void addKuchen_should_throw_exception_when_no_hersteller() {
        addHerstellers();

        Kuchen kremKuchen = mock(Kremkuchen.class);
        when(kremKuchen.getHersteller()).thenReturn(new HerstellerImplementation("apple"));

        assertThatThrownBy(() -> verwaltung.addKuchen(kremKuchen, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(
                        "No such manufacturer %s. Please add manufacturer %s.",
                        kremKuchen.getHersteller().getName(),
                        kremKuchen.getHersteller().getName())
                );
    }

    @Test
    void should_add_kuchen_after_a_kuchen_was_deleted(){
        addHerstellers();

        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);
        when(kremKuchen1.getHersteller()).thenReturn(herstellerMandarin);
        when(obstKuchen.getHersteller()).thenReturn(romashka);

        verwaltung.addKuchen(kuchen, LocalDateTime.now());
        verwaltung.addKuchen(kremKuchen, LocalDateTime.now());
        verwaltung.addKuchen(kremKuchen1, LocalDateTime.now());
        verwaltung.addKuchen(obstKuchen, LocalDateTime.now());

        verwaltung.removeKuchenFromAutomat(kremKuchen);
        verwaltung.removeKuchenFromAutomat(obstKuchen);

        Kuchen newKucnen = mock(Kuchen.class);
        when(newKucnen.getHersteller()).thenReturn(romashka);

        verwaltung.addKuchen(newKucnen, LocalDateTime.now());

        assertThat(verwaltung.getAllEingelagertenKuchen()).isEqualTo(Arrays.asList(kuchen, newKucnen, kremKuchen1));
    }



    @Test
    void addKuchen_should_throw_exception_when_no_place_left() {

        verwaltung = new Verwaltung(0);
        addHerstellers();
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);

        assertThatThrownBy(() -> verwaltung.addKuchen(kremKuchen, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Der Automat ist voll");
    }


    @Test
    void should_return_all_kuchen_in_automat(){
        verwaltung = new Verwaltung(2);
        addHerstellers();

        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);

        verwaltung.addKuchen(kuchen, LocalDateTime.now());
        verwaltung.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(verwaltung.getAllEingelagertenKuchen()).isEqualTo(Arrays.asList(
            kuchen, kremKuchen
        ));
    }


    @Test
    void should_get_list_with_kuchen_of_certain_type() {
        verwaltung = new Verwaltung(3);
        addHerstellers();

        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);
        when(kremKuchen.getHersteller()).thenReturn(herstellerMandarin);

        verwaltung.addKuchen(kuchen, LocalDateTime.now());
        verwaltung.addKuchen(kremKuchen, LocalDateTime.now());
        verwaltung.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(verwaltung.getKuchenOfType(Kremkuchen.class)).isEqualTo(Arrays.asList(kremKuchen, kremKuchen));
    }

    @Test
    void should_return_number_of_Kuchen_from_one_hersteller() {
        Hersteller romashka = new HerstellerImplementation("romashka");
        Hersteller mandarin = new HerstellerImplementation("herstellerMandarin");

        verwaltung.addHersteller(romashka);
        verwaltung.addHersteller(mandarin);

        List<Kuchen> kuchens = Arrays.asList(kuchen, kremKuchen, kremKuchen1);
        kuchens.forEach(k -> when(k.getHersteller()).thenReturn(romashka));
        kuchens.forEach(k -> verwaltung.addKuchen(k, LocalDateTime.now()));

        when(obstKuchen.getHersteller()).thenReturn(mandarin);

        assertThat(verwaltung.getAnzahlKuchenZuHersteller("romashka")).isEqualTo(3);
    }

    @Test
    void should_return_fachnum() {
        when(obstKuchen.getHersteller()).thenReturn(herstellerMandarin);
        verwaltung.addHersteller(herstellerMandarin);
        verwaltung.addKuchen(obstKuchen, LocalDateTime.now());

        assertThat(verwaltung.getFachnummerZuBestimmtenKuchen(obstKuchen)).isEqualTo(0);
    }


    @Test
    void should_get_rest_haltbarkei_zu_bestimmten_kuchen() {
        addHerstellers();

        when(obstKuchen.getHersteller()).thenReturn(romashka);

        when(obstKuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(4L));
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));

        verwaltung.addKuchen(obstKuchen, now.minusDays(2L));

        assertThat(verwaltung.getRestHaltbarkeitZuBestimmtenKuchen(obstKuchen, now))
                .isEqualTo(Duration.ofDays(2));
    }

    @Test
    void should_remove_kuchen() {
        addHerstellers();

        when(kuchen.getHersteller()).thenReturn(romashka);
        when(kremKuchen.getHersteller()).thenReturn(romashka);

        verwaltung.addKuchen(kuchen, LocalDateTime.now());
        verwaltung.addKuchen(kremKuchen, LocalDateTime.now());

        verwaltung.removeKuchenFromAutomat(kremKuchen);

        assertThat(verwaltung.getAllEingelagertenKuchen()).isEqualTo(Arrays.asList(kuchen));
    }


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

        assertThat(verwaltung.getAllergenenInAutomat()).isEqualTo(Arrays.asList(Allergen.Gluten, Allergen.Haselnuss));
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

        assertThat(verwaltung.getAllergenenNotInAutomat()).isEqualTo(Arrays.asList(Allergen.Erdnuss, Allergen.Sesamsamen));
    }

    private void addHerstellers() {
        verwaltung.addHersteller(romashka);
        verwaltung.addHersteller(herstellerMandarin);
    }

    private void addKuchenToVerwaltung(List<Kuchen> kuchenList) {
        kuchenList.forEach(kuchen -> verwaltung.addKuchen(kuchen, LocalDateTime.now()));
    }

}
