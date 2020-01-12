package automat.apps.console;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InfoCommandMode;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.mvc.addMode.AddModeInputListener;
import automat.apps.console.mvc.deleteMode.DeleteModeInputListener;
import automat.apps.console.mvc.listMode.ListModeInputListener;
import automat.apps.console.observer.AddHerstellerObserver;
import automat.apps.console.observer.AddNewKuchenObserver;
import automat.apps.console.observer.DeleteHerstellerObserver;
import automat.apps.console.observer.RemoveKuchenObserver;
import automat.apps.console.service.StringUtils;
import automat.mainlib.Automat;
import automat.net.DataSender;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MainApp {
    public static void main(String[] args) {
//        Socket socketConnection = null;
//        ObjectOutputStream clientOutputStream = null;
//        ObjectInputStream clientInputStream = null;

        DataSender dataSender = new DataSender();

        Automat automat = new Automat(5);
        ConsoleReader consoleReader = new ConsoleReader();
        InputEventHandler eventHandler = new InputEventHandler();
        consoleReader.setHandler(eventHandler);


//        try {
//            socketConnection = new Socket(InetAddress.getLocalHost(), 1234);
//            clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
//            clientInputStream = new ObjectInputStream(socketConnection.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Printer printer = new Printer();
        AddHerstellerObserver addHerstellerObserver = new AddHerstellerObserver(automat, printer);
        DeleteHerstellerObserver deleteHerstellerObserver = new DeleteHerstellerObserver(automat, printer);
        AddNewKuchenObserver addNewKuchenObserver = new AddNewKuchenObserver(automat, printer);
        RemoveKuchenObserver removeKuchenObserver = new RemoveKuchenObserver(automat, printer);

        StringUtils stringUtils = new StringUtils();


        AddModeInputListener addModeInputListener =
                new AddModeInputListener(
                        automat,
                        printer,
                        new ConsoleReader(),
                        dataSender
                );


        ListModeInputListener listModeInputListener = new ListModeInputListener(automat, printer, new ConsoleReader());
        DeleteModeInputListener deleteModeInputListener = new DeleteModeInputListener(automat, stringUtils, printer, new ConsoleReader());
        InfoCommandMode infoCommandMode = new InfoCommandMode(printer);

        eventHandler.add(infoCommandMode);
        eventHandler.add(addModeInputListener);
        eventHandler.add(listModeInputListener);
        eventHandler.add(deleteModeInputListener);

        consoleReader.start();
    }
}
