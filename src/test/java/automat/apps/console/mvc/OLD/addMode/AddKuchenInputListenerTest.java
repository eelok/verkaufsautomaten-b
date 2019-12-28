//package automat.mvc.addMode;
//
//import automat.apps.console.mvc.OLD.addMode.AddKuchenInputListener;
//import automat.mainlib.Automat;
//import automat.apps.console.mvc.InputEvent;
//import automat.mainlib.kuchen.Kuchen;
//import automat.apps.console.service.KuchenParser;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//class AddKuchenInputListenerTest {
//
//    @Test
//    void should_call_addKuchen(){
//        Automat automat = mock(Automat.class);
//        KuchenParser kuchenParser = mock(KuchenParser.class);
//        AddKuchenInputListener addKuchenInputListener = new AddKuchenInputListener(kuchenParser, automat);
//        InputEvent inputEvent = mock(InputEvent.class);
//        Kuchen kuchen = mock(Kuchen.class);
//        when(kuchenParser.getKuchenInfo("text")).thenReturn(kuchen);
//        when(inputEvent.getText()).thenReturn("text");
//
//        addKuchenInputListener.onInputEvent(inputEvent);
//
//        verify(automat).addKuchen(eq(kuchen), any(LocalDateTime.class));
//    }
//
//}
