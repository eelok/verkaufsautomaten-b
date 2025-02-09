package automat.net.client.mode.listModeNet;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;

public class ListModeInfoNet implements InputEventListener {

    private Printer printer;

    public ListModeInfoNet(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().equalsIgnoreCase("manufacturer") && !event.getText().equalsIgnoreCase("kuchen")) {
            printer.println("Input example:\n" +
                    "manufacturer\n" +
                    "kuchen\n" +
                    ":q -back to main menu");
        }
    }
}
