package automat.apps.console.mvc.deleteMode;

import automat.apps.console.StringUtils;
import automat.mainlib.Verwaltung;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;

public class DeleteModeInputListener implements InputEventListener {

    private Verwaltung verwaltung;

    public DeleteModeInputListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":d")) {
            System.out.println("delete mode active");
            ConsoleReader consoleReader = new ConsoleReader();
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new DeleteHerstellerInputListener(verwaltung));
            eventHandler.add(new DeleteKuchenInputListener(new StringUtils(), verwaltung));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
