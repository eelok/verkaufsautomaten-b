package automat.net.client.mode.deleteMode;

import automat.apps.console.mvc.infoCommonMode.InfoForAddandDelete;
import automat.apps.console.service.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.event.InputEvent;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.apps.console.mvc.event.InputEventListener;

import automat.net.client.connection.DataSender;

public class DeleteModeInputListenerNet implements InputEventListener {

    private Printer printer;
    private ConsoleReader consoleReader;
    private DataSender dataSender;

    public DeleteModeInputListenerNet(Printer printer, ConsoleReader consoleReader, DataSender dataSender) {
        this.printer = printer;
        this.consoleReader = consoleReader;
        this.dataSender = dataSender;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (":d".equalsIgnoreCase(event.getText().trim())) {
            printer.println("delete mode active");
            printer.println(
                    "Input example:\n" +
                    "Manufacturer: Alex\n" +
                    "Kuchen: <fachnummer>\n" +
                    ":q -back to main menu"
            );
            InputEventHandler eventHandler = new InputEventHandler();
            eventHandler.add(new InfoForAddandDelete(printer));
            eventHandler.add(new DeleteHerstellerInputListenerNet(printer, dataSender));
            eventHandler.add(new DeleteKuchenInputListenerNet(printer, dataSender));
            consoleReader.setHandler(eventHandler);
            consoleReader.start();
        }
    }
}
