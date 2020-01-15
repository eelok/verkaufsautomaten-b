package automat.net.client;

import automat.net.Command;
import automat.net.helper.ConnectionHelper;

import java.io.IOException;

public class DataSender {

    public DataSender() {
    }

    public void sendDataToServer(String inputData, Command command) throws IOException, ClassNotFoundException {
        ConnectionHelper connectionHelper = ConnectionHelper.getConnectionHelperSingleton();

        String dataForTransport = command + "/" + inputData;

        connectionHelper.getClientOutputStream().writeObject(dataForTransport);
        Object replyFromServer = connectionHelper.getClientInputStream().readObject();
        System.out.println(replyFromServer);
    }
}
