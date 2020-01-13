package automat.net.addModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.net.DataSender;

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
                dataSender.sendDataToServer(userInput);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
