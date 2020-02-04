package automat.apps.console.mvc.listMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;

public class ListModeInfo implements InputEventListener {

    private Printer printer;

    public ListModeInfo(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().equalsIgnoreCase("manufacturer") && !event.getText().equalsIgnoreCase("kuchen")) {
            printer.println(
                    "Input example:\n" +
                    "manufacturer\n" +
                    "kuchen\n" +
                    ":q -back to main menu"
            );
        }
    }
}
