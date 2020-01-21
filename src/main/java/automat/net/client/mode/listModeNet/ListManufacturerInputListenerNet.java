package automat.net.client.mode.listModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.net.common.Command;
import automat.net.client.connection.DataSender;

import java.io.IOException;

public class ListManufacturerInputListenerNet implements InputEventListener {
    private DataSender dataSender;

    public ListManufacturerInputListenerNet(DataSender dataSender) {
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String inputData = event.getText().trim();
        if ("manufacturer".equalsIgnoreCase(inputData)) {
            dataSender.getAllKuchenWithFachNum();
        }
    }
}
