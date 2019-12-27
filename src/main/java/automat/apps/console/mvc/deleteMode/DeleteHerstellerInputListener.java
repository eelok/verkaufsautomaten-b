package automat.apps.console.mvc.deleteMode;

import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;

public class DeleteHerstellerInputListener implements InputEventListener {
    private Automat automat;


    public DeleteHerstellerInputListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if(event.getText() == null){
            return;
        }
        if (!event.getText().matches("^f.[0-9]*$")) {
            try {
                automat.deleteHersteller(event.getText().toLowerCase().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
