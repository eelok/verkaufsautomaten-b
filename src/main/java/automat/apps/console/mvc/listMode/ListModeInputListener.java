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

    public ListModeInputListener(Automat automat, Printer printer) {
        this.automat = automat;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":l")) {
            System.out.println("list mode active");
            System.out.println("Enter command: manufacturer / kuchen / :q<back to main menu> ");
            ConsoleReader consoleReader = new ConsoleReader();
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new ListManufacturerInputListener(automat, printer));
            eventHandler.add(new ListKuchenInputListener(automat, printer));
            eventHandler.add(new ListModeInfo(printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
