package automat.net.listModeNet;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.net.DataSender;

import java.io.IOException;

public class ListManufacturerInputListenerNet implements InputEventListener {
    private DataSender dataSender;

    public ListManufacturerInputListenerNet(DataSender dataSender) {
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String inputData = event.getText().trim();
        if ("manufacturer".equalsIgnoreCase(inputData)) {
//            try {
//                dataSender.sendDataToServer(inputData);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
        }
    }
}
