package automat.net.client.mode.addModeNet;

import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventListener;
import automat.net.client.connection.DataSender;

public class AddManufacturerInputListenerNet implements InputEventListener {


    private DataSender dataSender;

    public AddManufacturerInputListenerNet(DataSender dataSender) {
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String userInput = event.getText().toLowerCase().trim();
//        if (stringUtils.isOneWord(userInput)) {
//            try {
//                dataSender.sendDataToServer(userInput, Command.ADD_HERSTELLER);
//            } catch (IOException | ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
