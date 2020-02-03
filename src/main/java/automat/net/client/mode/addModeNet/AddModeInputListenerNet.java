package automat.net.client.mode.addModeNet;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.apps.console.mvc.event.InputEventListener;
import automat.apps.console.service.KuchenParser;
import automat.net.client.connection.DataSender;

public class AddModeInputListenerNet implements InputEventListener {

    private Printer printer;
    private ConsoleReader consoleReader;
    private DataSender dataSender;

    public AddModeInputListenerNet(Printer printer, ConsoleReader consoleReader, DataSender dataSender) {
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && ":a".equalsIgnoreCase(event.getText())) {
            printer.println("add mode active");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new AddManufacturerInputListenerNet(dataSender));
            eventHandler.add(new AddKuchenInputListenerNet(new KuchenParser(), dataSender));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
