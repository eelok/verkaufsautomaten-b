package automat.apps.console.mvc.addMode;

import automat.apps.console.StringUtils;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Verwaltung;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.hersteller.HerstellerImplementation;

public class AddManufacturerInputListener implements InputEventListener {

    private StringUtils stringUtils;
    private Verwaltung verwaltung;

    public AddManufacturerInputListener(StringUtils stringUtils, Verwaltung verwaltung) {
        this.stringUtils = stringUtils;
        this.verwaltung = verwaltung;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        if (stringUtils.isOneWord(event.getText())) {
            Hersteller hersteller = new HerstellerImplementation(event.getText());
            try {
                verwaltung.addHersteller(hersteller);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
