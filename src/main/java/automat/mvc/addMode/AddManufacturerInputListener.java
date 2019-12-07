package automat.mvc.addMode;

import automat.StringUtils;
import automat.Verwaltung;
import automat.mvc.InputEvent;
import automat.mvc.InputEventListener;
import hersteller.Hersteller;
import hersteller.HerstellerImplementation;

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
