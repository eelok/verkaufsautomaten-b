package automat.apps.console;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.apps.console.mvc.addMode.AddModeInputListener;
import automat.apps.console.mvc.deleteMode.DeleteModeInputListener;
import automat.apps.console.mvc.listMode.ListModeInputListener;
import automat.apps.console.observer.AddHerstellerObserver;
import automat.apps.console.observer.AddNewKuchenObserver;
import automat.apps.console.observer.DeleteHerstellerObserver;
import automat.apps.console.observer.RemoveKuchenObserver;
import automat.apps.console.service.Printer;
import automat.mainlib.Automat;

public class Main {
    public static void main(String[] args) {

        Automat automat = new Automat(5);
        ConsoleReader consoleReader = new ConsoleReader();
        InputEventHandler eventHandler = new InputEventHandler();
        consoleReader.setHandler(eventHandler);

        Printer printer = new Printer();
        new AddHerstellerObserver(automat, printer);
        new DeleteHerstellerObserver(automat, printer);
        new AddNewKuchenObserver(automat, printer);
        new RemoveKuchenObserver(automat, printer);

        AddModeInputListener addModeInputListener = new AddModeInputListener(automat, printer, new ConsoleReader());
        ListModeInputListener listModeInputListener = new ListModeInputListener(automat, printer, new ConsoleReader());
        DeleteModeInputListener deleteModeInputListener = new DeleteModeInputListener(automat, printer, new ConsoleReader());

        eventHandler.add(addModeInputListener);
        eventHandler.add(listModeInputListener);
        eventHandler.add(deleteModeInputListener);
        eventHandler.add(event -> welcomeMessage(printer));
        welcomeMessage(printer);
        consoleReader.start();
    }

    private static void welcomeMessage(Printer printer) {
        printer.println(
                "You are in main menu\n" +
                "Expected input:\n" +
                ":a -input mode\n" +
                ":l -list mode\n" +
                ":d -delete mode\n" +
                ":q -exit");
    }
}
