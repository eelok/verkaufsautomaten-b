package automat.apps.console.mvc.deleteMode;

import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;

public class DeleteModeInputListener implements InputEventListener {

    private Automat automat;

    public DeleteModeInputListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":d")) {
            System.out.println("delete mode active");
            ConsoleReader consoleReader = new ConsoleReader();
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new DeleteHerstellerInputListener(automat));
            eventHandler.add(new DeleteKuchenInputListener(new StringUtils(), automat));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
