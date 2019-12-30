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

    public DeleteModeInputListener(Automat automat, StringUtils stringUtils, Printer printer) {
        this.automat = automat;
        this.stringUtils = stringUtils;
        this.printer = printer;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() != null && event.getText().equals(":d")) {
            System.out.println("delete mode active");
            System.out.println("Expected input: name of manufacturer / f<fachnummer>");
            ConsoleReader consoleReader = new ConsoleReader();
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new DeleteHerstellerInputListener(automat, printer));
            eventHandler.add(new DeleteKuchenInputListener(automat, stringUtils, printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
