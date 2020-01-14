package automat.net;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.service.StringUtils;
import automat.net.addModeNet.AddModeInputListenerNet;
import automat.net.deleteMode.DeleteModeInputListenerNet;
import automat.net.listModeNet.ListModeInputListenerNet;

public class MainAppNet {
    public static void main(String[] args) {
        DataSender dataSender = new DataSender();

        ConsoleReader consoleReader = new ConsoleReader();
        InputEventHandler eventHandler = new InputEventHandler();
        consoleReader.setHandler(eventHandler);

        Printer printer = new Printer();

//        todo change this code
//        AddHerstellerObserver addHerstellerObserver = new AddHerstellerObserver(automat, printer);
//        DeleteHerstellerObserver deleteHerstellerObserver = new DeleteHerstellerObserver(automat, printer);
//        AddNewKuchenObserver addNewKuchenObserver = new AddNewKuchenObserver(automat, printer);
//        RemoveKuchenObserver removeKuchenObserver = new RemoveKuchenObserver(automat, printer);

        StringUtils stringUtils = new StringUtils();

        AddModeInputListenerNet addModeInputListenerNet = new AddModeInputListenerNet(printer, new ConsoleReader(), dataSender);
        ListModeInputListenerNet listModeInputListenerNet = new ListModeInputListenerNet(printer, new ConsoleReader(), dataSender);
        DeleteModeInputListenerNet deleteModeInputListenerNet = new DeleteModeInputListenerNet(stringUtils, printer, new ConsoleReader(), dataSender);

        InfoCommandModeNet InfoCommandModeNet = new InfoCommandModeNet(printer);

        eventHandler.add(InfoCommandModeNet);
        eventHandler.add(addModeInputListenerNet);
        eventHandler.add(listModeInputListenerNet);
        eventHandler.add(deleteModeInputListenerNet);

        consoleReader.start();
    }
}
