package automat.net.client.connection;

import automat.apps.console.service.Printer;
import automat.net.common.Command;

import java.io.IOException;

public class DataSender {
    private ConnectionHelper connectionHelper;
    private Printer printer;

    public DataSender(ConnectionHelper connectionHelper, Printer printer) {
        this.connectionHelper = connectionHelper;
        this.printer = printer;
    }

    public void sendDataToServer(String inputData, Command command) throws IOException, ClassNotFoundException {

        String dataForTransport = command + "/" + inputData;

        connectionHelper.getClientOutputStream().writeObject(dataForTransport);
        Object replyFromServer = connectionHelper.getClientInputStream().readObject();
        printer.println(replyFromServer.toString());
    }
}
