package automat.mvc.addMode;

import automat.StringUtils;
import automat.Verwaltung;
import automat.mvc.ConsoleReader;
import automat.mvc.InputEvent;
import automat.mvc.InputEventHandler;
import automat.mvc.InputEventListener;
import kuchen.KuchenParser;

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
