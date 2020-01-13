package automat.net.listModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.net.DataSender;
import automat.net.command.CommandListKuchen;

import java.io.IOException;

public class ListKuchenInputListenerNet implements InputEventListener {
    private DataSender dataSender;

    public ListKuchenInputListenerNet(DataSender dataSender) {
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if ("kuchen".equalsIgnoreCase(event.getText().trim())) {
            try {
                dataSender.sendDataToServer(new CommandListKuchen());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
