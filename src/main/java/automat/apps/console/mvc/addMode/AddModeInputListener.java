package automat.apps.console.mvc.addMode;

import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.apps.console.mvc.event.InputEventListener;
import automat.apps.console.service.Printer;
import automat.apps.console.mvc.*;
import automat.apps.console.mvc.infoCommon.InfoForAddandDelete;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.Automat;

public class AddModeInputListener implements InputEventListener {

    private Automat automat;
    private Printer printer;
    private ConsoleReader consoleReader;

    public AddModeInputListener(Automat automat, Printer printer, ConsoleReader consoleReader) {
        this.automat = automat;
        this.printer = printer;
        this.consoleReader = consoleReader;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (":a".equalsIgnoreCase(event.getText().trim())) {
            printer.println("add mode active");
            printer.println(
                    "Input example:\n" +
                    "Manufacturer: Alex\n" +
                    "Kuchen: Kuchen 8.8 Alex Erdnuss 980 48\n"+
                    "Kuchen: Kremkuchen 2.5 Alex Gluten,Haselnuss 1400 24 Sahne\n" +
                    "Kuchen: Obstkuchen 5.0 Alex Gluten,Haselnuss,Sesamsamen 1000 36 Heidelbeeren\n"+
                    "Kuchen: Obsttorte 10.0 Alex Gluten,Haselnuss,Erdnuss 550 25 Heidelbeeren Butter\n"+
                    ":q -back to main menu");
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new InfoForAddandDelete(printer));
            eventHandler.add(new AddManufacturerInputListener(automat, printer));
            eventHandler.add(new AddKuchenInputListener(new KuchenParser(), automat, printer));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
