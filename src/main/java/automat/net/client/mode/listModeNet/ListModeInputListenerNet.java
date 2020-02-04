package automat.net.client.mode.listModeNet;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.apps.console.mvc.event.InputEventListener;
import automat.net.client.connection.DataSender;

public class ListModeInputListenerNet implements InputEventListener {

    private Printer printer;
    private ConsoleReader consoleReader;
    private DataSender dataSender;

    public ListModeInputListenerNet(Printer printer, ConsoleReader consoleReader, DataSender dataSender) {
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (":l".equalsIgnoreCase(event.getText().trim())) {
            printer.println("list mode active");
            printer.println(
                    "Input example:\n" +
                    "manufacturer\n" +
                    "kuchen\n" +
                    ":q -back to main menu"
            );
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new ListManufacturerInputListenerNet(dataSender));
            eventHandler.add(new ListKuchenInputListenerNet(dataSender));
            eventHandler.add(new ListModeInfoNet(printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
