package automat.mvc.deleteMode;

import automat.StringUtils;
import automat.Verwaltung;
import automat.mvc.ConsoleReader;
import automat.mvc.InputEvent;
import automat.mvc.InputEventHandler;
import automat.mvc.InputEventListener;

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
