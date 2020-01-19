package automat.net.client.mode.addModeNet;

import automat.net.common.Printer;
import automat.net.client.mode.ConsoleReader;
import automat.net.client.mode.inputEvent.InputEvent;
import automat.net.client.mode.inputEvent.InputEventHandler;
import automat.net.client.mode.inputEvent.InputEventListener;
import automat.net.common.KuchenParser;
import automat.net.common.StringUtils;
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
            eventHandler.add(new AddManufacturerInputListenerNet(new StringUtils(), dataSender));
            eventHandler.add(new AddKuchenInputListenerNet(new KuchenParser(), dataSender));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
