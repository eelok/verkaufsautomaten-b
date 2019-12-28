package automat.apps.console.mvc.listMode;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;

public class ListModeInfo implements InputEventListener {

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().equals("manufacturer") && !event.getText().equals("kuchen")) {
            System.out.println("List Mode input: expected format: manufacturer / kuchen / :q<back to main menu>");
        }
    }
}
