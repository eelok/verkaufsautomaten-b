package automat.net.client.mode.addModeNet;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.apps.console.mvc.event.InputEventListener;
import automat.apps.console.service.Printer;
import automat.net.client.connection.DataSender;
import automat.net.client.mode.infoCommonMode.InfoForAddandDeleteNet;

public class AddModeInputListenerNet implements InputEventListener {

    private Printer printer;
    private ConsoleReader consoleReader;
    private DataSender dataSender;

    public AddModeInputListenerNet(Printer printer, ConsoleReader consoleReader, DataSender dataSender) {
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (":a".equalsIgnoreCase(event.getText())) {
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
            eventHandler.add(new InfoForAddandDeleteNet(printer));
            eventHandler.add(new AddManufacturerInputListenerNet(dataSender));
            eventHandler.add(new AddKuchenInputListenerNet(dataSender));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
