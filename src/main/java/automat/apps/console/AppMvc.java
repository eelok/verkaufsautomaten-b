package automat.apps.console;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.addMode.AddModeInputListener;
import automat.apps.console.mvc.deleteMode.DeleteModeInputListener;
import automat.apps.console.mvc.listMode.ListModeInputListener;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.mainlib.hersteller.observer.AddHerstellerObserver;
import automat.mainlib.hersteller.observer.DeleteHarstellerObserver;
import automat.mainlib.kuchen.observer.AddNewKuchenObserver;
import automat.mainlib.kuchen.observer.RemoveKuchenObserver;

public class AppMvc {
    public static void main(String[] args) {

        Automat automat = new Automat(5);
        ConsoleReader consoleReader = new ConsoleReader();
        InputEventHandler eventHandler = new InputEventHandler();
        consoleReader.setHandler(eventHandler);

        AddHerstellerObserver addHerstellerObserver = new AddHerstellerObserver(automat);
        DeleteHarstellerObserver deleteHarstellerObserver = new DeleteHarstellerObserver(automat);
        AddNewKuchenObserver addNewKuchenObserver = new AddNewKuchenObserver(automat);
        RemoveKuchenObserver removeKuchenObserver = new RemoveKuchenObserver(automat);

        StringUtils stringUtils = new StringUtils();

        AddModeInputListener addModeInputListener = new AddModeInputListener(automat);
        ListModeInputListener listModeInputListener = new ListModeInputListener(automat);
        DeleteModeInputListener deleteModeInputListener = new DeleteModeInputListener(automat, stringUtils);
        eventHandler.add(addModeInputListener);
        eventHandler.add(listModeInputListener);
        eventHandler.add(deleteModeInputListener);

        consoleReader.start();
    }
}
