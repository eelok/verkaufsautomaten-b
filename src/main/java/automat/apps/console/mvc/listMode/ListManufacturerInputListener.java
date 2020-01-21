package automat.apps.console.mvc.listMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.AutomatInterface;

import java.util.List;

public class ListManufacturerInputListener implements InputEventListener {
    private AutomatInterface automat;
    private Printer printer;

    public ListManufacturerInputListener(AutomatInterface automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if ("manufacturer".equalsIgnoreCase(event.getText().trim())) {
            List<String> herstellerWithNumberOfKuchen = automat.getHerstellerWithNumberOfKuchen();
            if (herstellerWithNumberOfKuchen.isEmpty()) {
                printer.println("there is no manufacturer");
            } else {
                herstellerWithNumberOfKuchen.forEach(lin -> printer.println(lin));
            }
        }
    }
}
