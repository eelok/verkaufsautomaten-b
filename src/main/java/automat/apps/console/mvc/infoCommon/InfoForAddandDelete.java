package automat.apps.console.mvc.infoCommon;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;

public class InfoForAddandDelete implements InputEventListener {

    private Printer printer;

    public InfoForAddandDelete(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if(!event.getText().toLowerCase().startsWith("manufacturer:") && !event.getText().toLowerCase().startsWith("kuchen:") && !event.getText().toLowerCase().startsWith(":q")){
            printer.println("Wrong input");
        }
    }
}

