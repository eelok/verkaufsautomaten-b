package automat.net.client.mode.listModeNet;

import automat.net.common.Printer;
import automat.net.client.mode.inputEvent.InputEvent;
import automat.net.client.mode.inputEvent.InputEventListener;

public class ListModeInfoNet implements InputEventListener {

    private Printer printer;

    public ListModeInfoNet(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().equalsIgnoreCase("manufacturer") && !event.getText().equalsIgnoreCase("kuchen")) {
            printer.println("List Mode input: expected format: manufacturer / kuchen / :q<back to main menu>");
        }
    }
}
