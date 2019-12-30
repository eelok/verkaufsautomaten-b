package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.service.StringUtils;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.service.KuchenParser;

public class AddModeInputListener implements InputEventListener {

    private Automat automat;
    private Printer printer;
    private ConsoleReader consoleReader;

    public AddModeInputListener(Automat automat, Printer printer, ConsoleReader consoleReader) {
        this.automat = automat;
        this.printer = printer;
        this.consoleReader = consoleReader;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":a")) {
            printer.println("add mode active");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new AddManufacturerInputListener(new StringUtils(), automat, printer));
            eventHandler.add(new AddKuchenInputListener(new KuchenParser(), automat, printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
