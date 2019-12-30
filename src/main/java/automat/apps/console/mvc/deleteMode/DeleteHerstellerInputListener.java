package automat.apps.console.mvc.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
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
        if(event.getText() == null){
            return;
        }
        if (!event.getText().matches("^f.[0-9]*$")) {
            try {
                automat.deleteHersteller(event.getText().toLowerCase().trim());
            } catch (IllegalArgumentException e) {
                printer.println(e.getMessage());
            }
        }
    }

}
