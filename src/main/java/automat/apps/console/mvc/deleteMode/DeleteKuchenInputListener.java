package automat.apps.console.mvc.deleteMode;

import automat.apps.console.service.Printer;
import automat.mainlib.Automat;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;

public class DeleteKuchenInputListener implements InputEventListener {
    private Automat automat;
    private Printer printer;

    public DeleteKuchenInputListener(Automat automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if(event.getText() == null){
            return;
        }
        String userInput = event.getText().trim().toLowerCase();
        if(userInput.startsWith("kuchen:")){
            String infoFroDeleteKuchen = userInput.replace("kuchen:", "");
            String fachNumber = infoFroDeleteKuchen.trim();
            int fnum = Integer.parseInt(fachNumber);
            try {
                automat.removeKuchenFromAutomat(fnum);
            } catch (IllegalArgumentException ex){
                printer.println(ex.getMessage());
            }
        }
    }

}
