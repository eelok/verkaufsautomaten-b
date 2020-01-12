package automat.apps.console.mvc.listMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;
import automat.net.DataSender;

public class ListModeInputListener implements InputEventListener {

    private Automat automat;
    private Printer printer;
    private ConsoleReader consoleReader;
    private DataSender dataSender;

    public ListModeInputListener(Automat automat, Printer printer, ConsoleReader consoleReader, DataSender dataSender) {
        this.automat = automat;
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":l")) {
            printer.println("list mode active");
            printer.println("Enter command: manufacturer / kuchen / :q<back to main menu> ");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new ListManufacturerInputListener(automat, printer, dataSender));
            eventHandler.add(new ListKuchenInputListener(automat, printer, dataSender));
            eventHandler.add(new ListModeInfo(printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
