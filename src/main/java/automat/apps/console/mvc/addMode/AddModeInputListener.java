package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.service.StringUtils;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.mainlib.Automat;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.service.KuchenParser;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddModeInputListener implements InputEventListener {

    private Automat automat;
    private Printer printer;
    private ConsoleReader consoleReader;
    private Socket socketConnection;
    private ObjectOutputStream clientOutputStream;
    private ObjectInputStream clientInputStream;

    public AddModeInputListener(
            Automat automat,
            Printer printer,
            ConsoleReader consoleReader,
            Socket socketConnection,
            ObjectOutputStream clientOutputStream,
            ObjectInputStream clientInputStream
    ) {
        this.automat = automat;
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.socketConnection = socketConnection;
        this.clientOutputStream = clientOutputStream;
        this.clientInputStream = clientInputStream;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":a")) {
            printer.println("add mode active");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new AddManufacturerInputListener(new StringUtils(), new Printer(), socketConnection, clientOutputStream, clientInputStream));
            eventHandler.add(new AddKuchenInputListener(new KuchenParser(), automat, printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
