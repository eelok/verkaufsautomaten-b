package automat.net.listModeNet;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;

public class ListModeInfoNet implements InputEventListener {

    private Printer printer;

    public ListModeInfoNet(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().equals("manufacturer") && !event.getText().equals("kuchen")) {
            printer.println("List Mode input: expected format: manufacturer / kuchen / :q<back to main menu>");
        }
    }
}
