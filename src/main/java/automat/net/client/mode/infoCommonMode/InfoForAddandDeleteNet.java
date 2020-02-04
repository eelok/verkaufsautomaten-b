package automat.net.client.mode.infoCommonMode;

import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;
import automat.apps.console.service.Printer;

public class InfoForAddandDeleteNet implements InputEventListener {

    private Printer printer;

    public InfoForAddandDeleteNet(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if(!event.getText().toLowerCase().startsWith("manufacturer:") && !event.getText().toLowerCase().startsWith("kuchen:") && !event.getText().toLowerCase().startsWith(":q")){
            printer.println("Wrong input");
        }
    }
}

