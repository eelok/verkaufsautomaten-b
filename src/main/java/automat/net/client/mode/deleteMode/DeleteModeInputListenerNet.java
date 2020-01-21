package automat.net.client.mode.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.mainlib.AutomatInterface;

public class DeleteModeInputListenerNet implements InputEventListener {

    private StringUtils stringUtils;
    private Printer printer;
    private ConsoleReader consoleReader;
    private AutomatInterface automat;

    public DeleteModeInputListenerNet(
            StringUtils stringUtils,
            Printer printer,
            ConsoleReader consoleReader,
            AutomatInterface automat
    ) {
        this.stringUtils = stringUtils;
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && ":d".equalsIgnoreCase(event.getText())) {
            printer.println("delete mode active");
            printer.println("Expected input: name of manufacturer / f<fachnummer>");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new DeleteHerstellerInputListenerNet(printer, automat));
            eventHandler.add(new DeleteKuchenInputListenerNet(stringUtils, printer, automat));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
