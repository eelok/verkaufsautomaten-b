package automat.net.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.net.DataSender;

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
        if (event.getText() != null && ":d".equals(event.getText())) {
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
