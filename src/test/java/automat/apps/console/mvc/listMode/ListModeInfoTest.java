package automat.apps.console.mvc.listMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListModeInfoTest {

    @Mock
    private Printer printer;
    @Mock
    private InputEvent event;

    @Test
    void should_call_method_println(){
        ListModeInfo listModeInfo = new ListModeInfo(printer);
        when(event.getText()).thenReturn("something");

        listModeInfo.onInputEvent(event);

        verify(printer).println("List Mode input: expected format: manufacturer / kuchen / :q<back to main menu>");
    }

}