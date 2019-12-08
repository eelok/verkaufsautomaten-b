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
import automat.mainlib.Automat;

import java.util.ArrayList;

public class AppMvc {

    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();

        consoleReader.setHandler(initializeEventHandler());

        consoleReader.start();
    }

    private static InputEventHandler initializeEventHandler() {
        Automat automat = new Automat(3, new ArrayList<>(), new ArrayList<>());
        automat.registerAddNewHerstellerObserver(new AddNewHerstellerObserver());
        automat.registerAddNewKuchenObserver(new AddNewKuchenObserver());
        automat.registerRemoveHarstellerObserver(new RemoveHarstellerObserver());
        automat.registerRemoveKuchenObserver(new RemoveKuchenObserver());
        InputEventHandler eventHandler = new InputEventHandler();
        eventHandler.add(new AddModeInputListener(automat));
        eventHandler.add(new ListModeInputListener(automat));
        eventHandler.add(new DeleteModeInputListener(automat));

        return eventHandler;
    }
}
