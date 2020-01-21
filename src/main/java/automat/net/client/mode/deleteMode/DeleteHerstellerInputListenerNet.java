package automat.net.client.mode.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.AutomatInterface;

public class DeleteHerstellerInputListenerNet implements InputEventListener {

    private Printer printer;
    private AutomatInterface automat;

    public DeleteHerstellerInputListenerNet(Printer printer, AutomatInterface automat) {
        this.printer = printer;
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String inputData = event.getText().toLowerCase().trim();
        if (!inputData.matches("^f.[0-9]*$")) {
            try {
                automat.deleteHersteller(inputData);
            } catch (IllegalArgumentException e) {
                printer.println(e.getMessage());
            }
        }
    }

}
