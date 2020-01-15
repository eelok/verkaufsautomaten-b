package automat.net.listModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.net.Command;
import automat.net.client.DataSender;

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
