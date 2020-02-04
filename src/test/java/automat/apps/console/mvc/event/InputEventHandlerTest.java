package automat.apps.console.mvc.event;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InputEventHandlerTest {

    @Test
    void should_call_onInputEvent(){
        InputEventHandler inputEventHandler = new InputEventHandler();
        InputEvent event = mock(InputEvent.class);
        InputEventListener inputEventListener = mock(InputEventListener.class);
        inputEventHandler.add(inputEventListener);

        inputEventHandler.handle(event);

        verify(inputEventListener).onInputEvent(event);
    }

}