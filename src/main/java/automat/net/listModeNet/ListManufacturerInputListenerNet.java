package automat.net.listModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.net.command.CommandListHersteller;
import automat.net.DataSender;

import java.io.IOException;

public class ListManufacturerInputListenerNet implements InputEventListener {
    private DataSender dataSender;

    public ListManufacturerInputListenerNet(DataSender dataSender) {
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if ("manufacturer".equalsIgnoreCase(event.getText())) {
            try {
                dataSender.sendDataToServer(new CommandListHersteller());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
