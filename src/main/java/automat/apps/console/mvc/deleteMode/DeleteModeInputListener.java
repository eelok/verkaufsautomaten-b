package automat.apps.console.mvc.deleteMode;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.infoCommon.InfoForAddandDelete;
import automat.mainlib.Automat;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.InputEventListener;

public class DeleteModeInputListener implements InputEventListener {

    private Automat automat;
    private Printer printer;
    private ConsoleReader consoleReader;

    public DeleteModeInputListener(Automat automat, Printer printer, ConsoleReader consoleReader) {
        this.automat = automat;
        this.printer = printer;
        this.consoleReader = consoleReader;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (":d".equalsIgnoreCase(event.getText().trim())) {
            printer.println("delete mode active");
            printer.println("Input example:\nManufacturer: Alex\nKuchen: <fachnummer>");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new InfoForAddandDelete(printer));
            eventHandler.add(new DeleteHerstellerInputListener(automat, printer));
            eventHandler.add(new DeleteKuchenInputListener(automat, printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
