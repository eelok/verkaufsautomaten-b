package automat.apps.console.mvc.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;

public class DeleteKuchenInputListener implements InputEventListener {
    private Automat automat;
    private StringUtils stringUtils;
    private Printer printer;

    public DeleteKuchenInputListener(Automat automat, StringUtils stringUtils, Printer printer) {
        this.automat = automat;
        this.stringUtils = stringUtils;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if(event.getText() == null){
            return;
        }
        if(event.getText().matches("^f.[0-9]*$")){
            int fachNumber = stringUtils.extractFachNumberFromString(event.getText());
            try {
                automat.removeKuchenFromAutomat(fachNumber);
            } catch (IllegalArgumentException ex){
                printer.println(ex.getMessage());
            }
        }
    }

}
