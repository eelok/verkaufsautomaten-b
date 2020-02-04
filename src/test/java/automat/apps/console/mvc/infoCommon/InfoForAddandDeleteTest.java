package automat.apps.console.mvc.infoCommon;

import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.service.Printer;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfoForAddandDeleteTest {

    @Mock
    private Printer printer;
    @Mock
    private InputEvent inputEvent;

    @Test
    void should_call_method_println(){
        InfoForAddandDelete infoForAddandDelete = new InfoForAddandDelete(printer);
        when(inputEvent.getText()).thenReturn("some text");

        infoForAddandDelete.onInputEvent(inputEvent);

        verify(printer).println("Wrong input");
    }
}