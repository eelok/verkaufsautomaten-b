package automat.net.client;

import automat.net.Command;
import automat.net.helper.ConnectionHelper;

import java.io.IOException;

public class DataSender {
    ConnectionHelper connectionHelper;

    public DataSender(ConnectionHelper connectionHelper) {
      this.connectionHelper = connectionHelper;
    }

    public ConnectionHelper getConnectionHelper() {
        return connectionHelper;
    }

    public void sendDataToServer(String inputData, Command command) throws IOException, ClassNotFoundException {

        String dataForTransport = command + "/" + inputData;

        connectionHelper.getClientOutputStream().writeObject(dataForTransport);
        Object replyFromServer = connectionHelper.getClientInputStream().readObject();
        System.out.println(replyFromServer);
    }
}
