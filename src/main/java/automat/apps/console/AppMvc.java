package automat.apps.console;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.addMode.AddModeInputListener;
import automat.apps.console.mvc.deleteMode.DeleteModeInputListener;
import automat.apps.console.mvc.listMode.ListModeInputListener;
import automat.mainlib.hersteller.observer.AddNewHerstellerObserver;
import automat.mainlib.hersteller.observer.RemoveHarstellerObserver;
import automat.mainlib.kuchen.observer.AddNewKuchenObserver;
import automat.mainlib.kuchen.observer.RemoveKuchenObserver;
import automat.mainlib.Verwaltung;

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
