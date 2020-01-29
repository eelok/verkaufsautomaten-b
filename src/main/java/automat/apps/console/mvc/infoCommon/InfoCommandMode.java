package automat.apps.console.mvc.infoCommon;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;

public class InfoCommandMode implements InputEventListener {

    private Printer printer;

    public InfoCommandMode(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().equalsIgnoreCase(":a") && !event.getText().equalsIgnoreCase(":l") && !event.getText().equalsIgnoreCase(":d")){
            printer.println(
                    "Expected input:\n" +
                    ":a -input mode\n" +
                    ":l -list mode\n" +
                    ":d -delete mode"
            );
        }
    }
}

