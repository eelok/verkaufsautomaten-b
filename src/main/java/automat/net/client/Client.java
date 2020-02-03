package automat.net.client;

import automat.apps.console.service.Printer;
import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.InputEventHandler;
import automat.net.client.connection.ConnectionHelper;
import automat.net.client.connection.DataSender;
import automat.net.client.mode.InfoCommandModeNet;
import automat.net.client.mode.addModeNet.AddModeInputListenerNet;
import automat.net.client.mode.deleteMode.DeleteModeInputListenerNet;
import automat.net.client.mode.listModeNet.ListModeInputListenerNet;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        ConnectionHelper connectionHelper = ConnectionHelper.getConnectionHelperSingleton();

        DataSender dataSender = new DataSender(connectionHelper);
        ConsoleReader consoleReader = new ConsoleReader();
        InputEventHandler eventHandler = new InputEventHandler();
        consoleReader.setHandler(eventHandler);
        Printer printer = new Printer();
        AddModeInputListenerNet addModeInputListenerNet = new AddModeInputListenerNet(printer, new ConsoleReader(), dataSender);
        ListModeInputListenerNet listModeInputListenerNet = new ListModeInputListenerNet(printer, new ConsoleReader(), dataSender);
        DeleteModeInputListenerNet deleteModeInputListenerNet = new DeleteModeInputListenerNet(printer, new ConsoleReader(), dataSender);
        InfoCommandModeNet InfoCommandModeNet = new InfoCommandModeNet(printer);

        eventHandler.add(InfoCommandModeNet);
        eventHandler.add(addModeInputListenerNet);
        eventHandler.add(listModeInputListenerNet);
        eventHandler.add(deleteModeInputListenerNet);
        printer.println("Expected input: :a <input mode> | :l <list mode> | :d <delete mode>");
        consoleReader.start();
    }
}
