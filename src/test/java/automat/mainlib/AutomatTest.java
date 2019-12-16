package automat.mainlib;

import automat.mainlib.exceptions.AutomatIsFullException;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class AutomatTest {


    @Test
    void should_add_hersteller() {
        Automat automat = new Automat(3);
        Hersteller newHersteller = mock(Hersteller.class);

        automat.addHersteller(newHersteller);

        assertThat(automat.getHerstellerList()).isEqualTo(Arrays.asList(newHersteller));
    }

    @Test
    void should_throw_exception_when_add_the_same_hersteller() {
        Automat automat = new Automat(3);
        Hersteller donna = mock(Hersteller.class);
        ArrayList<Hersteller> allHersteller = new ArrayList<>();
        allHersteller.add(donna);
        automat.setHerstellerList(allHersteller);
        when(donna.getName()).thenReturn("donna");

        assertThatThrownBy(() -> automat.addHersteller(donna)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void should_delete_the_hersteller() {
        Automat automat = new Automat(2);
        Hersteller alex = mock(Hersteller.class);
        Hersteller donna = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");

        automat.addHersteller(alex);
        automat.addHersteller(donna);

        automat.deleteHersteller("alex");

        assertThat(automat.getHerstellerList()).isEqualTo(Arrays.asList(donna));
    }

    @Test
    void should_throw_exception_when_delete_nonexistenting_hersteller() {
        Automat automat = new Automat(1);

        assertThatThrownBy(() -> automat.deleteHersteller("donna")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_add_kuchen() {
        Automat automat = new Automat(1);
        Hersteller alex = mock(Hersteller.class);
        Kuchen kuchen = mock(Kuchen.class);

        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);
        when(kuchen.getHersteller()).thenReturn(alex);
        automat.addKuchen(kuchen, LocalDateTime.now());

        assertThat(automat.getStorage().get(0).getKuchen()).isEqualTo(kuchen);
    }

    @Test
    void addKuchen_should_throw_exception_when_no_hersteller() {
        Automat automat = new Automat(1);
        Kuchen kremKuchen = mock(Kremkuchen.class);

        when(kremKuchen.getHersteller()).thenReturn(new HerstellerImplementation("apple"));

        assertThatThrownBy(() -> automat.addKuchen(kremKuchen, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void addKuchen_should_assign_fachnummer() {
        Automat automat = new Automat(1);
        Hersteller alex = mock(HerstellerImplementation.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);
        Kuchen kuchen = mock(KuchenImplementation.class);

        when(kuchen.getHersteller()).thenReturn(alex);
        automat.addKuchen(kuchen, LocalDateTime.now());

        assertThat(automat.getStorage().get(0).getFachnummer()).isEqualTo(0);
    }

    @Test
    void addKuchen_should_throw_exception_when_no_place_left() {
        Automat automat = new Automat(0);
        Hersteller alex = mock(HerstellerImplementation.class);
        when(alex.getName()).thenReturn("alex");

        List<Hersteller> allhersteller = new ArrayList<>();
        allhersteller.add(alex);
        automat.setHerstellerList(allhersteller);

        Kuchen kuchen = mock(KuchenImplementation.class);
        when(kuchen.getHersteller()).thenReturn(alex);

        assertThatThrownBy(() -> automat.addKuchen(kuchen, LocalDateTime.now()))
                .isInstanceOf(AutomatIsFullException.class)
                .hasMessage("Der Automat ist voll");
    }

    @Test
    void addKuchen_should_allocate_date(){
        Automat automat = new Automat(1);
        Hersteller alex = mock(HerstellerImplementation.class);
        when(alex.getName()).thenReturn("alex");
        List<Hersteller> allhersteller = new ArrayList<>();
        allhersteller.add(alex);
        automat.setHerstellerList(allhersteller);

        Kuchen kuchen = mock(KuchenImplementation.class);
        when(kuchen.getHersteller()).thenReturn(alex);

        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        automat.addKuchen(kuchen, now);
        assertThat(automat.getStorage().get(0).getEinlagerungsDatum()).isEqualTo(now);
    }


    @Test
    void should_return_all_kuchen_in_automat(){
        Automat automat = new Automat(2);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(KuchenImplementation.class);
        when(kuchen.getHersteller()).thenReturn(alex);
        Kuchen kremKuchen = mock(KuchenImplementation.class);
        when(kremKuchen.getHersteller()).thenReturn(alex);

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(automat.getAllEingelagertenKuchen()).isEqualTo(Arrays.asList(
                kuchen, kremKuchen
        ));
    }

    @Test
    void should_get_list_with_kuchen_of_certain_type() {
        Automat automat = new Automat(3);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        Kuchen kremKuchen = mock(Kremkuchen.class);
        when(kuchen.getHersteller()).thenReturn(alex);
        when(kremKuchen.getHersteller()).thenReturn(alex);

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(automat.getKuchenOfType(Kremkuchen.class)).isEqualTo(Arrays.asList(kremKuchen, kremKuchen));
    }

    @Test
    void should_return_number_of_Kuchen_from_one_hersteller() {
        Automat automat = new Automat(3);
        Hersteller alex = mock(Hersteller.class);
        Hersteller donna = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        when(donna.getName()).thenReturn("donna");
        automat.addHersteller(alex);
        automat.addHersteller(donna);

        Kuchen kuchen = mock(Kuchen.class);
        Kuchen kremKuchen = mock(Kremkuchen.class);
        Kuchen obstKuchen = mock(Obstkuchen.class);
        when(kuchen.getHersteller()).thenReturn(donna);
        when(kremKuchen.getHersteller()).thenReturn(donna);
        when(obstKuchen.getHersteller()).thenReturn(alex);

        List<Kuchen> kuchens = Arrays.asList(kuchen, kremKuchen, obstKuchen);
        kuchens.forEach(k -> automat.addKuchen(k, LocalDateTime.now()));

        assertThat(automat.getAnzahlKuchenZuHersteller("donna")).isEqualTo(2);
    }

    @Test
    void should_return_fachnum_for_kuchen() {
        Automat automat = new Automat(2);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        Kuchen kremKuchen = mock(Kremkuchen.class);
        when(kuchen.getHersteller()).thenReturn(alex);
        when(kremKuchen.getHersteller()).thenReturn(alex);

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(automat.getFachnummerZuBestimmtenKuchen(kremKuchen)).isEqualTo(1);
    }

    @Test
    void should_throw_exception_when_look_up_for_fachnummer_for_kuchen_which_is_not_in_Automat() {
        Automat automat = new Automat(1);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        when(kuchen.getHersteller()).thenReturn(alex);
        automat.addKuchen(kuchen, LocalDateTime.now());

        Kuchen kremKuchen = mock(Kuchen.class);
        when(kremKuchen.getHersteller()).thenReturn(alex);

        assertThatThrownBy(() -> automat.getFachnummerZuBestimmtenKuchen(kremKuchen))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Der Kuchen gibt es nicht");
    }

    @Test
    void should_get_rest_haltbarkeit_zu_bestimmten_kuchen() {
        Automat automat = new Automat(1);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        when(kuchen.getHersteller()).thenReturn(alex);
        when(kuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(4L));

        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));

        automat.addKuchen(kuchen, now.minusDays(2L));

        assertThat(automat.getRestHaltbarkeitZuBestimmtenKuchen(kuchen, now))
                .isEqualTo(Duration.ofDays(2));
    }

    @Test
    void should_remove_kuchen() {
        Automat automat = new Automat(2);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        Kuchen kremKuchen = mock(Kremkuchen.class);
        when(kuchen.getHersteller()).thenReturn(alex);
        when(kremKuchen.getHersteller()).thenReturn(alex);

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        int fachnummer = automat.getFachnummerZuBestimmtenKuchen(kremKuchen);
        automat.removeKuchenFromAutomat(fachnummer);

        assertThat(automat.getAllEingelagertenKuchen()).isEqualTo(Arrays.asList(kuchen));
    }

    @Test
    void should_throw_exception_when_remove_kuchen() {
        Automat automat = new Automat(2);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        Kuchen kremKuchen = mock(Kremkuchen.class);
        when(kuchen.getHersteller()).thenReturn(alex);
        when(kremKuchen.getHersteller()).thenReturn(alex);

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        int fachnummer = 10;

        assertThatThrownBy(() -> automat.removeKuchenFromAutomat(fachnummer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("fachnumber does not exist");
    }

    @Test
    void should_return_all_allergene_in_automat() {
        Automat automat = new Automat(2);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        Kuchen kremKuchen = mock(Kremkuchen.class);

        when(kuchen.getHersteller()).thenReturn(alex);
        when(kremKuchen.getHersteller()).thenReturn(alex);
        when(kuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(kremKuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten, Allergen.Haselnuss));

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(automat.getAllergenenInAutomat()).isEqualTo(Arrays.asList(Allergen.Gluten, Allergen.Haselnuss));
    }

    @Test
    void should_return_allergene_not_in_Automat() {
        Automat automat = new Automat(2);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        Kuchen kremKuchen = mock(Kremkuchen.class);

        when(kuchen.getHersteller()).thenReturn(alex);
        when(kremKuchen.getHersteller()).thenReturn(alex);
        when(kuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten));
        when(kremKuchen.getAllergenes()).thenReturn(Arrays.asList(Allergen.Gluten, Allergen.Haselnuss));

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(automat.getAllergenenNotInAutomat()).isEqualTo(Arrays.asList(Allergen.Erdnuss, Allergen.Sesamsamen));
    }

    @Test
    void should_find_kuchen_with_smallest_haltbarkeit(){
        Automat automat = new Automat(2);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        Kuchen kremKuchen = mock(Kremkuchen.class);
        when(kuchen.getHersteller()).thenReturn(alex);
        when(kuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(3L));
        when(kremKuchen.getHersteller()).thenReturn(alex);
        when(kremKuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(1L));

        automat.addKuchen(kuchen, LocalDateTime.now());
        automat.addKuchen(kremKuchen, LocalDateTime.now());

        assertThat(automat.findKuchenWithSmallestHaltbarkeit().getKuchen()).isEqualTo(kremKuchen);
    }

    @Test
    void should_return_true_when_automat_is_full(){
        Automat automat = new Automat(1);
        Hersteller alex = mock(Hersteller.class);
        when(alex.getName()).thenReturn("alex");
        automat.addHersteller(alex);

        Kuchen kuchen = mock(Kuchen.class);
        when(kuchen.getHersteller()).thenReturn(alex);
        automat.addKuchen(kuchen, LocalDateTime.now());

        assertThat(automat.isFull()).isTrue();
    }

    @Test
    void should_return_false_when_automat_is_not_full(){
        Automat automat = new Automat(1);

        assertThat(automat.isFull()).isFalse();
    }

}