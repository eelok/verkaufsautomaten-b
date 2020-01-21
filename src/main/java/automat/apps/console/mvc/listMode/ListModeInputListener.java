package automat.apps.console.mvc.listMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.AutomatInterface;

public class ListModeInputListener implements InputEventListener {

    private AutomatInterface automat;
    private Printer printer;
    private ConsoleReader consoleReader;

    public ListModeInputListener(AutomatInterface automat, Printer printer, ConsoleReader consoleReader) {
        this.automat = automat;
        this.printer = printer;
        this.consoleReader = consoleReader;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (":l".equalsIgnoreCase(event.getText().trim())) {
            printer.println("list mode active");
            printer.println("Enter command: manufacturer / kuchen / :q<back to main menu> ");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new ListManufacturerInputListener(automat, printer));
            eventHandler.add(new ListKuchenInputListener(automat, printer));
            eventHandler.add(new ListModeInfo(printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
