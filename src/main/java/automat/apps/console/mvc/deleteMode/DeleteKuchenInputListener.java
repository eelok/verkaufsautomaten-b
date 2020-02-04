package automat.apps.console.mvc.deleteMode;

import automat.apps.console.service.Printer;
import automat.mainlib.Automat;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;

public class DeleteKuchenInputListener implements InputEventListener {
    private Automat automat;
    private Printer printer;

    public DeleteKuchenInputListener(Automat automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String userInput = event.getText().trim().toLowerCase();
        if (userInput.startsWith("kuchen:")) {
            try {
            String fachNumber = userInput.replace("kuchen:", "").trim();
            int fnum = Integer.parseInt(fachNumber);
                automat.removeKuchenFromAutomat(fnum);
            } catch (NumberFormatException nfe) {
                printer.println("Fachnummer should be a number");
            }
            catch (Exception ex) {
                printer.println(ex.getMessage());
            }
        }
    }

}
