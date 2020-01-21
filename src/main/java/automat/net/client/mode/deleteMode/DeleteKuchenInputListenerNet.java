package automat.net.client.mode.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.mainlib.AutomatInterface;

public class DeleteKuchenInputListenerNet implements InputEventListener {

    private StringUtils stringUtils;
    private Printer printer;
    private AutomatInterface automat;

    public DeleteKuchenInputListenerNet(StringUtils stringUtils, Printer printer, AutomatInterface automat) {
        this.stringUtils = stringUtils;
        this.printer = printer;
        this.automat = automat;
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
                automat.removeKuchenFromAutomat(fachNumber);
            } catch (IllegalArgumentException ex){
                printer.println(ex.getMessage());
            }
        }
    }

}
