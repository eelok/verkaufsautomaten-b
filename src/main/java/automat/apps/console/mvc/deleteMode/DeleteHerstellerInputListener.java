package automat.apps.console.mvc.deleteMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;
import automat.mainlib.Automat;

public class DeleteHerstellerInputListener implements InputEventListener {
    private Automat automat;
    private Printer printer;


    public DeleteHerstellerInputListener(Automat automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String userInput = event.getText().toLowerCase();
        if (userInput.startsWith("manufacturer:")) {
            String name = "";
            name = userInput.replace("manufacturer:", "");
            try {
                automat.deleteHersteller(name.trim());
            } catch (IllegalArgumentException e) {
                printer.println(e.getMessage());
            }
        }
    }

}
