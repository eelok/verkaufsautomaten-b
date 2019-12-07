package automat;

import automat.mvc.ConsoleReader;
import automat.mvc.InputEventHandler;
import automat.mvc.addMode.AddModeInputListener;
import automat.mvc.deleteMode.DeleteModeInputListener;
import automat.mvc.listMode.ListModeInputListener;
import hersteller.observer.AddNewHerstellerObserver;
import hersteller.observer.RemoveHarstellerObserver;
import kuchen.observer.AddNewKuchenObserver;
import kuchen.observer.RemoveKuchenObserver;

public class AppMvc {

    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();

        consoleReader.setHandler(initializeEventHandler());

        consoleReader.start();
    }

    private static InputEventHandler initializeEventHandler() {
        Verwaltung verwaltung = new Verwaltung(3);
        verwaltung.registerAddNewHerstellerObserver(new AddNewHerstellerObserver());
        verwaltung.registerAddNewKuchenObserver(new AddNewKuchenObserver());
        verwaltung.registerRemoveHarstellerObserver(new RemoveHarstellerObserver());
        verwaltung.registerRemoveKuchenObserver(new RemoveKuchenObserver());
        InputEventHandler eventHandler = new InputEventHandler();
        eventHandler.add(new AddModeInputListener(verwaltung));
        eventHandler.add(new ListModeInputListener(verwaltung));
        eventHandler.add(new DeleteModeInputListener(verwaltung));

        return eventHandler;
    }
}
