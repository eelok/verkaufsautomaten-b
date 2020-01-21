package automat.apps.console.mvc.listMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.AutomatInterface;

import java.util.List;

public class ListKuchenInputListener implements InputEventListener {

    private AutomatInterface automat;
    private Printer printer;

    public ListKuchenInputListener(AutomatInterface automat, Printer printer) {
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
