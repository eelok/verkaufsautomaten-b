package automat.net.client.mode.addModeNet;

import automat.net.client.mode.inputEvent.InputEvent;
import automat.net.client.mode.inputEvent.InputEventListener;
import automat.net.common.StringUtils;
import automat.net.common.Command;
import automat.net.client.connection.DataSender;

import java.io.IOException;

public class AddManufacturerInputListenerNet implements InputEventListener {

    private StringUtils stringUtils;
    private DataSender dataSender;

    public AddManufacturerInputListenerNet(StringUtils stringUtils, DataSender dataSender) {
        this.stringUtils = stringUtils;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String userInput = event.getText().toLowerCase().trim();
        if (stringUtils.isOneWord(userInput)) {
            try {
                dataSender.sendDataToServer(userInput, Command.ADD_HERSTELLER);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
