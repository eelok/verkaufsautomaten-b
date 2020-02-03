package automat.net.client.mode.deleteMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.net.client.connection.DataSender;

public class DeleteKuchenInputListenerNet implements InputEventListener {

    private Printer printer;
    private DataSender dataSender;

    public DeleteKuchenInputListenerNet(Printer printer, DataSender dataSender) {
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
//            int fachNumber = stringUtils.extractFachNumberFromString(inputData);
//            try {
//                dataSender.sendDataToServer(String.valueOf(fachNumber), Command.DELETE_KUCHEN);
//            } catch (IllegalArgumentException | IOException | ClassNotFoundException ex){
//                printer.println(ex.getMessage());
//            }
        }
    }

}
