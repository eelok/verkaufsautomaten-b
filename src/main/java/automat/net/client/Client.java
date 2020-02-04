package automat.net.client;

import automat.apps.console.mvc.ConsoleReader;
import automat.apps.console.mvc.event.InputEventHandler;
import automat.apps.console.service.Printer;
import automat.net.client.connection.ConnectionHelper;
import automat.net.client.connection.DataSender;
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

        eventHandler.add(addModeInputListenerNet);
        eventHandler.add(listModeInputListenerNet);
        eventHandler.add(deleteModeInputListenerNet);
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
