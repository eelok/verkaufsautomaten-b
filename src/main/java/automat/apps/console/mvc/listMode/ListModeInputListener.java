package automat.apps.console.mvc.listMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;

public class ListModeInputListener implements InputEventListener {

    private Automat automat;
    private Printer printer;
    private ConsoleReader consoleReader;

    public ListModeInputListener(Automat automat, Printer printer, ConsoleReader consoleReader) {
        this.automat = automat;
        this.printer = printer;
        this.consoleReader = consoleReader;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (":l".equalsIgnoreCase(event.getText().trim())) {
            printer.println("list mode active");
            printer.println("Input example:\nmanufacturer\nkuchen\n:q -back to main menu");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new ListManufacturerInputListener(automat, printer));
            eventHandler.add(new ListKuchenInputListener(automat, printer));
            eventHandler.add(new ListModeInfo(printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
