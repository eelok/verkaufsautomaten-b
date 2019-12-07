package automat.mvc.deleteMode;

import automat.Verwaltung;
import automat.mvc.InputEvent;
import automat.mvc.InputEventListener;

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
