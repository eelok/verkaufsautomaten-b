package automat.net.client.mode.listModeNet;

import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;
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
            try {
                dataSender.sendDataToServer(inputData, Command.LIST_HERSTELLER);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
