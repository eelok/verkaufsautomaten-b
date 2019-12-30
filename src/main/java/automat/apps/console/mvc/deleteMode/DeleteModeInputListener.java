package automat.apps.console.mvc.deleteMode;

import automat.apps.console.Printer;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;

public class DeleteModeInputListener implements InputEventListener {

    private Automat automat;
    private StringUtils stringUtils;
    private Printer printer;
    private ConsoleReader consoleReader;

    public DeleteModeInputListener(Automat automat, StringUtils stringUtils, Printer printer, ConsoleReader consoleReader) {
        this.automat = automat;
        this.stringUtils = stringUtils;
        this.printer = printer;
        this.consoleReader = consoleReader;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":d")) {
            printer.println("delete mode active");
            printer.println("Expected input: name of manufacturer / f<fachnummer>");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new DeleteHerstellerInputListener(automat, printer));
            eventHandler.add(new DeleteKuchenInputListener(automat, stringUtils, printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
