package automat.net.client.mode.deleteMode;

import automat.net.common.Printer;
import automat.net.client.mode.inputEvent.InputEvent;
import automat.net.client.mode.inputEvent.InputEventListener;
import automat.net.common.StringUtils;
import automat.net.common.Command;
import automat.net.client.connection.DataSender;

import java.io.IOException;

public class DeleteKuchenInputListenerNet implements InputEventListener {

    private StringUtils stringUtils;
    private Printer printer;
    private DataSender dataSender;

    public DeleteKuchenInputListenerNet(StringUtils stringUtils, Printer printer, DataSender dataSender) {
        this.stringUtils = stringUtils;
        this.printer = printer;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if(event.getText() == null){
            return;
        }
        String inputData = event.getText().toLowerCase().trim();
        if(inputData.matches("^f.[0-9]*$")){
            int fachNumber = stringUtils.extractFachNumberFromString(inputData);
            try {
                dataSender.sendDataToServer(String.valueOf(fachNumber), Command.DELETE_KUCHEN);
            } catch (IllegalArgumentException | IOException | ClassNotFoundException ex){
                printer.println(ex.getMessage());
            }
        }
    }

}
