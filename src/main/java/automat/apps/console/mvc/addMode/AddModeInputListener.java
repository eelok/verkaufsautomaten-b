package automat.apps.console.mvc.addMode;

import automat.apps.console.service.StringUtils;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.service.KuchenParser;

public class AddModeInputListener implements InputEventListener {

    private Automat automat;

    public AddModeInputListener(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":a")) {
            System.out.println("add mode active");
            ConsoleReader consoleReader = new ConsoleReader();
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new AddManufacturerInputListener(new StringUtils(), automat));
            eventHandler.add(new AddKuchenInputListener(new KuchenParser(), automat));

            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
