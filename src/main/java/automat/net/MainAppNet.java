package automat.net;

import automat.apps.console.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEventHandler;
import automat.apps.console.service.StringUtils;
import automat.net.addModeNet.AddModeInputListenerNet;
import automat.net.client.DataSender;
import automat.net.deleteMode.DeleteModeInputListenerNet;
import automat.net.helper.ConnectionHelper;
import automat.net.listModeNet.ListModeInputListenerNet;

import java.io.IOException;

public class MainAppNet {
    public static void main(String[] args) {

        startClient();
    }

    private static void startClient(){
        ConnectionHelper connectionHelper = null;
        try {
            connectionHelper = ConnectionHelper.getConnectionHelperSingleton();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataSender dataSender = new DataSender(connectionHelper);

        ConsoleReader consoleReader = new ConsoleReader();
        InputEventHandler eventHandler = new InputEventHandler();
        consoleReader.setHandler(eventHandler);

        Printer printer = new Printer();

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
