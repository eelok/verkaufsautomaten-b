package automat.apps.console.mvc.listMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;
import automat.net.DataSender;

import java.io.IOException;
import java.util.List;

public class ListManufacturerInputListener implements InputEventListener {
    private Automat automat;
    private Printer printer;
    private DataSender dataSender;

    public ListManufacturerInputListener(Automat automat, Printer printer, DataSender dataSender) {
        this.automat = automat;
        this.printer = printer;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if ("manufacturer".equalsIgnoreCase(event.getText())) {
            try {
                dataSender.sendDataToServer(event.getText().trim());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
