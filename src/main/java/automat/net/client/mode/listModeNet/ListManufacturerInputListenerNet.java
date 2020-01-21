package automat.net.client.mode.listModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.AutomatInterface;

public class ListManufacturerInputListenerNet implements InputEventListener {
    private AutomatInterface automat;

    public ListManufacturerInputListenerNet(AutomatInterface automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String inputData = event.getText().trim();
        if ("manufacturer".equalsIgnoreCase(inputData)) {
            automat.getAllKuchenWithFachNum();
        }
    }
}
