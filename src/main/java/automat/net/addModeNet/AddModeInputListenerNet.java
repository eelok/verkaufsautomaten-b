package automat.net.addModeNet;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.KuchenParser;
import automat.apps.console.service.StringUtils;
import automat.net.DataSender;

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
        if (event.getText() != null && ":a".equals(event.getText())) {
            printer.println("add mode active");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new AddManufacturerInputListenerNet(new StringUtils(), dataSender));
            eventHandler.add(new AddKuchenInputListenerNet(new KuchenParser(), printer, dataSender));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
