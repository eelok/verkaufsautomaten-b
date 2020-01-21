package automat.net.client.mode.addModeNet;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.KuchenParser;
import automat.apps.console.service.StringUtils;
import automat.mainlib.AutomatInterface;

public class AddModeInputListenerNet implements InputEventListener {

    private Printer printer;
    private ConsoleReader consoleReader;
    private AutomatInterface automat;

    public AddModeInputListenerNet(Printer printer, ConsoleReader consoleReader, AutomatInterface automat) {
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && ":a".equalsIgnoreCase(event.getText())) {
            printer.println("add mode active");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new AddManufacturerInputListenerNet(new StringUtils(), automat));
            eventHandler.add(new AddKuchenInputListenerNet(new KuchenParser(), automat));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
