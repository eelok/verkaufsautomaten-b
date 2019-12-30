package automat.apps.console;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InfoCommandMode;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.addMode.AddModeInputListener;
import automat.apps.console.mvc.deleteMode.DeleteModeInputListener;
import automat.apps.console.mvc.listMode.ListModeInputListener;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.apps.console.observer.AddHerstellerObserver;
import automat.apps.console.observer.DeleteHerstellerObserver;
import automat.apps.console.observer.AddNewKuchenObserver;
import automat.apps.console.observer.RemoveKuchenObserver;

public class MainApp {
    public static void main(String[] args) {

        Automat automat = new Automat(5);
        ConsoleReader consoleReader = new ConsoleReader();
        InputEventHandler eventHandler = new InputEventHandler();
        consoleReader.setHandler(eventHandler);

        Printer printer = new Printer();
        AddHerstellerObserver addHerstellerObserver = new AddHerstellerObserver(automat, printer);
        DeleteHerstellerObserver deleteHerstellerObserver = new DeleteHerstellerObserver(automat, printer);
        AddNewKuchenObserver addNewKuchenObserver = new AddNewKuchenObserver(automat, printer);
        RemoveKuchenObserver removeKuchenObserver = new RemoveKuchenObserver(automat, printer);

        StringUtils stringUtils = new StringUtils();

        AddModeInputListener addModeInputListener = new AddModeInputListener(automat, printer, consoleReader);
        ListModeInputListener listModeInputListener = new ListModeInputListener(automat, printer, consoleReader);
        DeleteModeInputListener deleteModeInputListener = new DeleteModeInputListener(automat, stringUtils, printer, consoleReader);
        InfoCommandMode infoCommandMode = new InfoCommandMode(printer);

        eventHandler.add(infoCommandMode);
        eventHandler.add(addModeInputListener);
        eventHandler.add(listModeInputListener);
        eventHandler.add(deleteModeInputListener);

        consoleReader.start();
    }
}
