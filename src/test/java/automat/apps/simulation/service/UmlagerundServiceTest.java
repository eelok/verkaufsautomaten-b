package automat.apps.simulation.service;

import automat.apps.simulation.CreateAutomatService;
import automat.apps.simulation.service.UmlagerungService;
import automat.mainlib.Automat;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.ObstkuchenImplementation;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UmlagerundServiceTest {


    @Mock
    private Automat freshKuchenAutomat;
    @Mock
    private CreateAutomatService automatFactory;

    @Test
    void should_call_removeKuchenFromAutomat_and_addKuchen() {
        UmlagerungService umlagerungService = new UmlagerungService(freshKuchenAutomat, automatFactory);
        int fachNum = 1;
        Kuchen obstKuchen = mock(ObstkuchenImplementation.class);
        when(obstKuchen.getHaltbarkeit()).thenReturn(Duration.ofDays(2L));
        EinlagerungEntry einlagerungEntry = mock(EinlagerungEntry.class);
        when(einlagerungEntry.getKuchen()).thenReturn(obstKuchen);
        when(freshKuchenAutomat.findKuchenWithSmallestHaltbarkeit()).thenReturn(einlagerungEntry);
        when(einlagerungEntry.getFachnummer()).thenReturn(fachNum);
        when(freshKuchenAutomat.removeKuchenFromAutomat(fachNum)).thenReturn(einlagerungEntry);
        Automat oldKuchenAutomat = mock(Automat.class);
        when(automatFactory.createAutomat(anyInt())).thenReturn(oldKuchenAutomat);

        umlagerungService.umlagernKuchen();

        verify(freshKuchenAutomat).removeKuchenFromAutomat(fachNum);
        verify(oldKuchenAutomat).addKuchen(eq(obstKuchen), any(LocalDateTime.class));
    }


}