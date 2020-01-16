package automat.net.server;

import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import automat.mainlib.kuchen.*;
import automat.net.Command;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    void should_add_kuchen_when_command_ADD_KUCHEN_and_kuchen_data(){
        String inputCommand = "ADD_KUCHEN";
        String recivedData = "kremkuchen 2.5 Donna Sesamsamen,Haselnuss 1400 24 Sahne";

        Kremkuchen kremkuchen = mock(KremkuchenImplementation.class);
        Hersteller donna = mock(HerstellerImplementation.class);
        when(kremkuchen.getHersteller()).thenReturn(donna);
        when(kremkuchen.getType()).thenReturn("kremkuchen");
        when(kremkuchen.getAllergens()).thenReturn(Arrays.asList(Allergen.Sesamsamen, Allergen.Haselnuss));
        when(kremkuchen.getNaehrwert()).thenReturn(1400);
        when(kremkuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(1));
        when(kremkuchen.getKremsorte()).thenReturn("Sahne");

        when(kuchenParser.getKuchenInfo(recivedData)).thenReturn(kremkuchen);
        when(automat.getHerstellerList()).thenReturn(Collections.singletonList(donna));
        dataHandler.handleData(inputCommand, recivedData);

        verify(automat).addKuchen(eq(kremkuchen), any(LocalDateTime.class));
    }

    @Test
    void should_list_hersteller_when_command_LIST_HERSTELLER(){
        String inputCommand = "LIST_HERSTELLER";
        String recivedData = "";

        dataHandler.handleData(inputCommand, recivedData);

        verify(automat).getHerstellerWithNumberOfKuchen();
    }

    @Test
    void should_list_kuchen_when_command_LIST_KUCHEN(){
        String inputCommand = "LIST_KUCHEN";
        String recivedData = "";

        dataHandler.handleData(inputCommand, recivedData);

        verify(automat).getAllKuchenWithFachNum();
    }

}