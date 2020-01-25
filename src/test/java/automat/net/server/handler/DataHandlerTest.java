package automat.net.server.handler;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;
import automat.net.server.handler.DataHandler;
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
    void should_return_when_command_does_not_exist(){
        assertThat(dataHandler.handleData("NEW_COMMAND", ""))
                .isEqualTo("from server: NEW_COMMAND wrong command");
    }

    @Test
    void should_add_hersteller_when_command_ADD_HERSTELLER_and_hersteller_data(){
        String data = "bob";

        assertThat(dataHandler.handleData("ADD_HERSTELLER", data))
                .isEqualTo("from server: hersteller bob was added to automat");
        verify(automat).addHersteller(any(HerstellerImplementation.class));
    }

    @Test
    void should_return_message_when_hersteller_can_not_be_added(){
        String name = "tom";
        doThrow(new ManufacturerExistException(String.format("Manufacturer %s already exists", name)))
                .when(automat).addHersteller(new HerstellerImplementation(name));
        assertThat(dataHandler.handleData("ADD_HERSTELLER", "tom"))
                .isEqualTo("Manufacturer tom already exists");
    }
    @Test
    void should_add_kuchen_when_command_ADD_KUCHEN_and_kuchen_data(){
        String data = "kremkuchen 2.5 Donna Sesamsamen,Haselnuss 1400 24 Sahne";

        Kremkuchen kremkuchen = mock(KremkuchenImplementation.class);
        when(kremkuchen.getType()).thenReturn(TypeOfKuchen.Kremkuchen.toString());

        when(kuchenParser.getKuchenInfo(data)).thenReturn(kremkuchen);
        when(automat.addKuchen(kremkuchen, LocalDateTime.now())).thenReturn(mock(EinlagerungEntry.class));

        assertThat(dataHandler.handleData("ADD_KUCHEN", data)).
            isEqualTo(
                "from server: kuchen of type %s was added to automat",
                TypeOfKuchen.Kremkuchen.toString()
            );
        verify(automat).addKuchen(any(KremkuchenImplementation.class), any(LocalDateTime.class));
    }

    @Test
    void should_return_message_when_kuchen_can_not_be_added(){
        String data = "kremkuchen 2.5 Donna Sesamsamen,Haselnuss 1400 24 Sahne";
        String name = "Donna";

        Kremkuchen kremkuchen = mock(KremkuchenImplementation.class);
        when(kuchenParser.getKuchenInfo(data)).thenReturn(kremkuchen);

        doThrow(
            new IllegalArgumentException(
                String.format("No such manufacturer: %s. Please add manufacturer: %s.", name, name))
        ).when(automat).addKuchen(eq(kremkuchen), any(LocalDateTime.class));

        assertThat(dataHandler.handleData("ADD_KUCHEN", data)).isEqualTo("No such manufacturer: Donna. Please add manufacturer: Donna.");
    }

    @Test
    void should_return_list_hersteller_with_num_of_kuchen_when_command_LIST_HERSTELLER(){
        List<String> herstellerWihtNumOfKuch = new ArrayList<>();
        herstellerWihtNumOfKuch.add("donna"+ ": " + 0);
        herstellerWihtNumOfKuch.add("tom"+ ": " + 5);
        when(automat.getHerstellerWithNumberOfKuchen()).thenReturn(herstellerWihtNumOfKuch);

        assertThat(dataHandler.handleData("LIST_HERSTELLER", ""))
                .isEqualTo("from server: [donna: 0, tom: 5]");
    }

    @Test
    void should_return_message_when_list_with_hersteller_is_Empty(){
        assertThat(dataHandler.handleData("LIST_HERSTELLER", ""))
                .isEqualTo("form server: there is no manufacturer");
        verify(automat).getHerstellerWithNumberOfKuchen();
    }
    @Test
    void should_return_list_kuchen_when_command_LIST_KUCHEN(){
        List<String> listWithKuchAndFach = new ArrayList<>();
        listWithKuchAndFach.add("obstkuhen" + ": " + 1);
        listWithKuchAndFach.add("obsttorte" + ": " + 2);
        when(automat.getAllKuchenWithFachNum()).thenReturn(listWithKuchAndFach);

        assertThat(dataHandler.handleData("LIST_KUCHEN", ""))
                .isEqualTo("from server: [obstkuhen: 1, obsttorte: 2]");
    }
    @Test
    void should_return_message_when_list_kuchen_is_empty(){
        assertThat(dataHandler.handleData("LIST_KUCHEN", ""))
                .isEqualTo("from server: No Kuchen Available in the Automat");
        verify(automat).getAllKuchenWithFachNum();
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

        assertThat(dataHandler.handleData("DELETE_KUCHEN", "0"))
                .isEqualTo("from server: Obsttorte from fach 0 was deleted");
    }
    @Test
    void should_return_message_when_kuchen_can_not_be_deleted_command_DELETE_KUCHEN(){
        doThrow(new IllegalArgumentException("fachnummer does not exist"))
                .when(automat).removeKuchenFromAutomat(10);
        assertThat(dataHandler.handleData("DELETE_KUCHEN", "10"))
                .isEqualTo("fachnummer does not exist");
    }

    @Test
    void should_return_message_when_hersteller_can_not_be_deleted(){
        doThrow(new IllegalArgumentException("Hersteller bob does not exist"))
                .when(automat).deleteHersteller("bob");

        assertThat(dataHandler.handleData("DELETE_HERSTELLER", "bob"))
                .isEqualTo("Hersteller bob does not exist");
    }

    @Test
    void should_return_message_when_hersteller_was_deleted_command_DELETE_HERSTELLER(){
        assertThat(dataHandler.handleData("DELETE_HERSTELLER", "bob"))
                .isEqualTo("from server: hersteller bob was deleted from automat");
        verify(automat).deleteHersteller(anyString());
    }

    @Test
    void should (){
        dataHandler.handleData("Q", "");
        verifyNoInteractions(automat);
    }
}