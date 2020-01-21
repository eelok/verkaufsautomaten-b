package automat.apps.console;

import automat.apps.console.mvc.addMode.AddKuchenInputListener;
import automat.apps.console.service.KuchenParser;
import automat.mainlib.AutomatInterface;
import automat.net.client.connection.ConnectionHelper;
import automat.net.client.connection.DataSender;

import java.io.IOException;

public class MainNetwork {

    public static void main(String[] args) throws IOException {

        Printer printer = new Printer();
        KuchenParser kuchenParser = new KuchenParser();
        ConnectionHelper connectionHelper = ConnectionHelper.getConnectionHelperSingleton();
        DataSender dataSender = new DataSender(connectionHelper);

        AddKuchenInputListener addKuchenInputListener = new AddKuchenInputListener(kuchenParser, dataSender, printer);

    }
}
