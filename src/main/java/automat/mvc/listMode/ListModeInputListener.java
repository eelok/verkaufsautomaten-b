package automat.mvc.listMode;

import automat.Verwaltung;
import automat.mvc.ConsoleReader;
import automat.mvc.InputEvent;
import automat.mvc.InputEventHandler;
import automat.mvc.InputEventListener;

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
