package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.net.Command;
import automat.net.ConnectionHelper;

import java.io.IOException;

public class AddManufacturerInputListener implements InputEventListener {

    private StringUtils stringUtils;
    private Printer printer;
    private ConnectionHelper connectionHelper;


    public AddManufacturerInputListener(StringUtils stringUtils, Printer printer) {
        this.stringUtils = stringUtils;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String userInput = event.getText().toLowerCase().trim();
        if (stringUtils.isOneWord(userInput)) {
            try {
                sendDataToServer(userInput);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendDataToServer(String userInput) throws IOException, ClassNotFoundException {
        this.connectionHelper = ConnectionHelper.getConnectionHelperSingleton();

        String dataForTransport = "";
        dataForTransport = Command.addH + "/" + userInput;

        this.connectionHelper.getClientOutputStream().writeObject(dataForTransport);
        Object replyFromServer = this.connectionHelper.getClientInputStream().readObject();
        this.printer.println(replyFromServer.toString());
    }
}
