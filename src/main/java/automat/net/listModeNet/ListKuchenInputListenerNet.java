package automat.net.listModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.net.Command;
import automat.net.DataSender;

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
                dataSender.sendDataToServer(inputData, Command.listK);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
