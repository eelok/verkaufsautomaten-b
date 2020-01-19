package automat.net.client.mode.deleteMode;

import automat.net.common.Printer;
import automat.net.client.mode.ConsoleReader;
import automat.net.client.mode.inputEvent.InputEvent;
import automat.net.client.mode.inputEvent.InputEventHandler;
import automat.net.client.mode.inputEvent.InputEventListener;
import automat.net.common.StringUtils;
import automat.net.client.connection.DataSender;

public class DeleteModeInputListenerNet implements InputEventListener {

    private StringUtils stringUtils;
    private Printer printer;
    private ConsoleReader consoleReader;
    private DataSender dataSender;

    public DeleteModeInputListenerNet(
            StringUtils stringUtils,
            Printer printer,
            ConsoleReader consoleReader,
            DataSender dataSender
    ) {
        this.stringUtils = stringUtils;
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && ":d".equalsIgnoreCase(event.getText())) {
            printer.println("delete mode active");
            printer.println("Expected input: name of manufacturer / f<fachnummer>");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new DeleteHerstellerInputListenerNet(printer, dataSender));
            eventHandler.add(new DeleteKuchenInputListenerNet(stringUtils, printer, dataSender));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
