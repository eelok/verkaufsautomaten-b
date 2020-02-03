package automat.apps.console.mvc.addMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;
import automat.mainlib.Automat;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.HerstellerImplementation;

public class AddManufacturerInputListener implements InputEventListener {

    private Automat automat;
    private Printer printer;

    public AddManufacturerInputListener(Automat automat, Printer printer) {
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
                automat.addHersteller(new HerstellerImplementation(name.trim()));
            } catch (ManufacturerExistException | IllegalArgumentException ex) {
                printer.println(ex.getMessage());
            }
        }
    }
}
