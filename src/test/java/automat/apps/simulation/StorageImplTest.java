package automat.apps.simulation;

import automat.apps.simulation.service.UmlagerungService;
import automat.mainlib.Automat;
import automat.mainlib.kuchen.Kuchen;
import automat.mainlib.kuchen.ObstkuchenImplementation;
import automat.mainlib.kuchen.TypeOfKuchen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StorageImplTest {
    private StorageImpl storageimpl;
    private Automat freshKuchenAutomat;
    private UmlagerungService umlagerungService;

    @BeforeEach
    void setUp(){
        freshKuchenAutomat = mock(Automat.class);
        umlagerungService = mock(UmlagerungService.class);
        this.storageimpl = new StorageImpl(freshKuchenAutomat, umlagerungService);
    }
    @Test
    void put_should_call_method_addKuchen() throws InterruptedException {
        Kuchen obsttorte = mock(ObstkuchenImplementation.class);
        when(freshKuchenAutomat.getName()).thenReturn("fresh kuchen automat");
        when(obsttorte.getHaltbarkeit()).thenReturn(Duration.ofDays(2L));
        when(obsttorte.getType()).thenReturn(TypeOfKuchen.Obsttorte.toString());
        when(freshKuchenAutomat.isFull()).thenReturn(false);

        storageimpl.put(obsttorte);

        verify(freshKuchenAutomat).addKuchen(eq(obsttorte), any(LocalDateTime.class));
    }

    @Test
    void poll_should_call_method_umlagernKuchen() throws InterruptedException {
        when(freshKuchenAutomat.isFull()).thenReturn(true);

        storageimpl.poll();

        verify(umlagerungService).umlagernKuchen();
    }



}