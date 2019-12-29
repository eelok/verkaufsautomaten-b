package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.mainlib.exceptions.ManufacturerExistException;
import automat.mainlib.hersteller.HerstellerImplementation;

public class AddManufacturerInputListener implements InputEventListener {

    private StringUtils stringUtils;
    private Automat automat;
    private Printer printer;

    public AddManufacturerInputListener(StringUtils stringUtils, Automat automat, Printer printer) {
        this.stringUtils = stringUtils;
        this.automat = automat;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String userInput = event.getText().toLowerCase().trim();
        if (stringUtils.isOneWord(userInput)) {
            try {
                automat.addHersteller(new HerstellerImplementation(userInput));
            } catch (ManufacturerExistException ex) {
                printer.println(ex.getMessage());
            }
        }
    }
}
