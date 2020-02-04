package automat.net.client.mode.deleteMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;
import automat.net.common.Command;
import automat.net.client.connection.DataSender;

import java.io.IOException;

public class DeleteHerstellerInputListenerNet implements InputEventListener {

    private Printer printer;
    private DataSender dataSender;

    public DeleteHerstellerInputListenerNet(Printer printer, DataSender dataSender) {
        this.printer = printer;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String inputData = event.getText().toLowerCase().trim();
        if (inputData.startsWith("manufacturer:")) {
            try {
                dataSender.sendDataToServer(inputData, Command.DELETE_HERSTELLER);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
