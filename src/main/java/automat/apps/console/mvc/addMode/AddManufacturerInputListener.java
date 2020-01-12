package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.net.DataSender;

import java.io.IOException;

public class AddManufacturerInputListener implements InputEventListener {

    private StringUtils stringUtils;
    private Printer printer;
    private DataSender dataSender;

    public AddManufacturerInputListener(StringUtils stringUtils, Printer printer, DataSender dataSender) {
        this.stringUtils = stringUtils;
        this.printer = printer;
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
