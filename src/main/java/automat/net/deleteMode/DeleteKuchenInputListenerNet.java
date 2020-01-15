package automat.net.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.net.Command;
import automat.net.client.DataSender;

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
