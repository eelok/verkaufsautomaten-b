package automat.apps.console.mvc.deleteMode;

import automat.mainlib.Automat;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;

public class DeleteHerstellerInputListener implements InputEventListener {
    private Automat automat;

    public DeleteHerstellerInputListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().matches("^f.[0-9]*$")) {
            try {
                automat.deleteHersteller(event.getText().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
