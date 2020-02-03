package automat.net.client.mode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;

public class InfoCommandModeNet implements InputEventListener {

    private Printer printer;

    public InfoCommandModeNet(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().equalsIgnoreCase(":a") && !event.getText().equalsIgnoreCase(":l") && !event.getText().equalsIgnoreCase(":d")){
            printer.println("Expected input: :a <input mode> | :l <list mode> | :d <delete mode>");
        }
    }
}

