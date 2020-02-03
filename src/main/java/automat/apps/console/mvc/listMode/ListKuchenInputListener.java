package automat.apps.console.mvc.listMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;
import automat.mainlib.Automat;

import java.util.List;

public class ListKuchenInputListener implements InputEventListener {
    private Automat automat;
    private Printer printer;

    public ListKuchenInputListener(Automat automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if ("kuchen".equalsIgnoreCase(event.getText().trim())) {
            List<String> allKuchenWithFachNum = automat.getAllKuchenWithFachNum();
            if (allKuchenWithFachNum.isEmpty()) {
                printer.println("No Kuchen Available in the Automat");
            } else {
                allKuchenWithFachNum.forEach(line -> printer.println(line));
            }
        }
    }
}
