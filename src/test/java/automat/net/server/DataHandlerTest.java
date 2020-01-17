package automat.net.server;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataHandlerTest {

    @Mock
    private Automat automat;

    @Mock
    private KuchenParser kuchenParser;

    private DataHandler dataHandler;

    @BeforeEach
    void serUp(){
        dataHandler = new DataHandler(automat, kuchenParser);
    }

    @Test
    void should_add_hersteller_when_command_ADD_HERSTELLER_and_hersteller_data(){
        String data = "bob";
        Hersteller bob = new HerstellerImplementation("bob");
        when(automat.addHersteller(bob)).thenReturn(true);

        assertThat(dataHandler.handleData("ADD_HERSTELLER", data)).isEqualTo("from server: hersteller bob was added to automat");
    }
    @Test
    void should_add_kuchen_when_command_ADD_KUCHEN_and_kuchen_data(){
        String data = "kremkuchen 2.5 Donna Sesamsamen,Haselnuss 1400 24 Sahne";

        Kremkuchen kremkuchen = mock(KremkuchenImplementation.class);
        when(kremkuchen.getType()).thenReturn(TypeOfKuchen.Kremkuchen.toString());

        when(kuchenParser.getKuchenInfo(data)).thenReturn(kremkuchen);
        when(automat.addKuchen(kremkuchen, LocalDateTime.now())).thenReturn(mock(EinlagerungEntry.class));

        assertThat(dataHandler.handleData("ADD_KUCHEN", data)).
                isEqualTo("from server: kuchen of type %s was added to automat", TypeOfKuchen.Kremkuchen.toString());
    }

    @Test
    void should_return_list_hersteller_with_num_of_kuchen_when_command_LIST_HERSTELLER(){
        List<String> herstellerWihtNumOfKuch = new ArrayList<>();
        herstellerWihtNumOfKuch.add("donna"+ ": " + 0);
        herstellerWihtNumOfKuch.add("tom"+ ": " + 5);
        when(automat.getHerstellerWithNumberOfKuchen()).thenReturn(herstellerWihtNumOfKuch);

        assertThat(dataHandler.handleData("LIST_HERSTELLER", "")).isEqualTo("from server: [donna: 0, tom: 5]");
    }

    @Test
    void should_return_list_hersteller_with_num_of_kuchen_when_command_LIST_HERSTELLER_and_list_is_Empty(){
        List<String> herstellerWihtNumOfKuch = new ArrayList<>();
        when(automat.getHerstellerWithNumberOfKuchen()).thenReturn(herstellerWihtNumOfKuch);

        assertThat(dataHandler.handleData("LIST_HERSTELLER", "")).isEqualTo("form server: there is no manufacturer");
    }
    @Test
    void should_return_list_kuchen_when_command_LIST_KUCHEN(){
        List<String> listWithKuchAndFach = new ArrayList<>();
        listWithKuchAndFach.add("obstkuhen" + ": " + 1);
        listWithKuchAndFach.add("obsttorte" + ": " + 2);
        when(automat.getAllKuchenWithFachNum()).thenReturn(listWithKuchAndFach);

        assertThat(dataHandler.handleData("LIST_KUCHEN", "")).isEqualTo("from server: [obstkuhen: 1, obsttorte: 2]");
    }
    @Test
    void should_return_list_is_empty_empty_command_LIST_KUCHEN(){
        List<String> listWithKuchAndFach = new ArrayList<>();
        when(automat.getAllKuchenWithFachNum()).thenReturn(listWithKuchAndFach);

        assertThat(dataHandler.handleData("LIST_KUCHEN", "")).isEqualTo("from server: No Kuchen Available in the Automat");
    }

    @Test
    void should_return_type_of_kuchen_and_fach_when_command_DELETE_KUCHEN_and_fach(){
        int fach = 0;
        EinlagerungEntry einlagerungEntry = mock(EinlagerungEntry.class);
        Kuchen obsttorte = mock(ObsttorteImplementation.class);
        when(obsttorte.getType()).thenReturn(TypeOfKuchen.Obsttorte.toString());
        when(einlagerungEntry.getKuchen()).thenReturn(obsttorte);
        when(einlagerungEntry.getFachnummer()).thenReturn(0);
        when(automat.removeKuchenFromAutomat(fach)).thenReturn(einlagerungEntry);

        assertThat(dataHandler.handleData("DELETE_KUCHEN", "0")).isEqualTo("from server: Obsttorte from fach 0 was deleted");
    }

    @Test
    void should_throw_exception_when_command_does_not_exist(){

        assertThat(dataHandler.handleData("NEW_COMMAND", "")).isEqualTo("from server: NEW_COMMAND wrong command");
    }

}