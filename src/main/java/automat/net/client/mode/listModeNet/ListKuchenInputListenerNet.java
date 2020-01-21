package automat.net.client.mode.listModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.AutomatInterface;

public class ListKuchenInputListenerNet implements InputEventListener {
    private AutomatInterface automat;

    public ListKuchenInputListenerNet(AutomatInterface automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String inputData = event.getText().trim();
        if ("kuchen".equalsIgnoreCase(inputData)) {
            automat.getAllKuchenWithFachNum();
        }
    }
}
