package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.KuchenParser;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.net.DataSender;

public class AddModeInputListener implements InputEventListener {

    private Automat automat;
    private Printer printer;
    private ConsoleReader consoleReader;
    private DataSender dataSender;

    public AddModeInputListener(Automat automat, Printer printer, ConsoleReader consoleReader, DataSender dataSender) {
        this.automat = automat;
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":a")) {
            printer.println("add mode active");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new AddManufacturerInputListener(new StringUtils(), new Printer(), dataSender));
            eventHandler.add(new AddKuchenInputListener(new KuchenParser(), automat, printer, dataSender));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
