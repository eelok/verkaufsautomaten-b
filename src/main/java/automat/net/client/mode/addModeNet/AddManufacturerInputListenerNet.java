package automat.net.client.mode.addModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.mainlib.AutomatInterface;
import automat.mainlib.hersteller.HerstellerImplementation;

public class AddManufacturerInputListenerNet implements InputEventListener {

    private StringUtils stringUtils;
    private AutomatInterface automat;

    public AddManufacturerInputListenerNet(StringUtils stringUtils, AutomatInterface automat) {
        this.stringUtils = stringUtils;
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String userInput = event.getText().toLowerCase().trim();
        if (stringUtils.isOneWord(userInput)) {
            automat.addHersteller(new HerstellerImplementation(userInput));
        }
    }
}
