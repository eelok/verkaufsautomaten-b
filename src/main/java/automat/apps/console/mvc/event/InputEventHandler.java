package automat.apps.console.mvc.event;

import java.util.LinkedList;
import java.util.List;

public class InputEventHandler {
    private List<InputEventListener> listenerList = new LinkedList<>();

    public void add(InputEventListener listener) {
        this.listenerList.add(listener);
    }

    public void handle(InputEvent event) {
        for (InputEventListener inputEventListener : listenerList){
            inputEventListener.onInputEvent(event);
        }
    }

}
