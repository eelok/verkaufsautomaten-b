package automat.apps.console.mvc.deleteMode;

import automat.mainlib.Verwaltung;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;

public class DeleteHerstellerInputListener implements InputEventListener {
    private Verwaltung verwaltung;

    public DeleteHerstellerInputListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().matches("^f.[0-9]*$")) {
            try {
                verwaltung.deleteHersteller(event.getText().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
