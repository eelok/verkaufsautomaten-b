package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.KuchenParser;
import automat.apps.console.service.StringUtils;
import automat.mainlib.AutomatInterface;

public class AddModeInputListener implements InputEventListener {

    private AutomatInterface automat;
    private Printer printer;
    private ConsoleReader consoleReader;

    public AddModeInputListener(AutomatInterface automat, Printer printer, ConsoleReader consoleReader) {
        this.automat = automat;
        this.printer = printer;
        this.consoleReader = consoleReader;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (":a".equalsIgnoreCase(event.getText().trim())) {
            printer.println("add mode active");
            printer.println("enter: manufacturer <name> | information about kuchen <Obstkuchen 2.5 Alex Gluten,Haselnuss 1400 24 Sahne>");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new AddManufacturerInputListener(new StringUtils(), automat, printer));
            eventHandler.add(new AddKuchenInputListener(new KuchenParser(), automat, printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
