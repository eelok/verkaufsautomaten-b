package automat.net.client.mode.listModeNet;

import automat.net.client.mode.inputEvent.InputEvent;
import automat.net.client.mode.inputEvent.InputEventListener;
import automat.net.common.Command;
import automat.net.client.connection.DataSender;

import java.io.IOException;

public class ListKuchenInputListenerNet implements InputEventListener {
    private DataSender dataSender;

    public ListKuchenInputListenerNet(DataSender dataSender) {
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String inputData = event.getText().trim();
        if ("kuchen".equalsIgnoreCase(inputData)) {
            try {
                dataSender.sendDataToServer(inputData, Command.LIST_KUCHEN);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
