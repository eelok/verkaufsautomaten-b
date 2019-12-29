package automat.apps.console.mvc.addMode;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AddManufacturerInputListenerTest {

    @Test
    void should_call_addHersteller(){
        Automat automat = mock(Automat.class);
        StringUtils stringUtils = mock(StringUtils.class);
        InputEvent event = mock(InputEvent.class);
        String herstellerName = "alex";

        when(event.getSource()).thenReturn(new Object());
        when(event.getText()).thenReturn(herstellerName);
        when(stringUtils.isOneWord(event.getText())).thenReturn(true);

        AddManufacturerInputListener addManufacturerInputListener = new AddManufacturerInputListener(stringUtils, automat);

        addManufacturerInputListener.onInputEvent(event);

        verify(automat).addHersteller(eq(new HerstellerImplementation(herstellerName)));
    }


}