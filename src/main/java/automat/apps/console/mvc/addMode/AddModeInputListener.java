package automat.apps.console.mvc.addMode;

import automat.apps.console.StringUtils;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Verwaltung;
import automat.apps.console.mvc.InputEvent;
import automat.mainlib.kuchen.KuchenParser;

public class AddModeInputListener implements InputEventListener {

    private Verwaltung verwaltung;

    public AddModeInputListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":a")) {
            System.out.println("add mode active");
            ConsoleReader consoleReader = new ConsoleReader();
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new AddManufacturerInputListener(new StringUtils(), verwaltung));
            eventHandler.add(new AddKuchenInputListener(new KuchenParser(), verwaltung));

            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
