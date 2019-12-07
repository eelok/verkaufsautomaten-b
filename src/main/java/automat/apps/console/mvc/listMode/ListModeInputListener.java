package automat.apps.console.mvc.listMode;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Verwaltung;

public class ListModeInputListener implements InputEventListener {

    private Verwaltung verwaltung;

    public ListModeInputListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":l")) {
            System.out.println("list mode active");
            ConsoleReader consoleReader = new ConsoleReader();
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new ListManufacturerInputListener(verwaltung));
            eventHandler.add(new ListKuchenInputListener(verwaltung));

            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
