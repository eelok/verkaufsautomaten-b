package automat.net.client.connection;

import automat.net.common.Command;

import java.io.IOException;

public class DataSender {
    ConnectionHelper connectionHelper;

    public DataSender(ConnectionHelper connectionHelper) {
      this.connectionHelper = connectionHelper;
    }

    public void sendDataToServer(String inputData, Command command) throws IOException, ClassNotFoundException {

        String dataForTransport = command + "/" + inputData;

        connectionHelper.getClientOutputStream().writeObject(dataForTransport);
        Object replyFromServer = connectionHelper.getClientInputStream().readObject();
        System.out.println(replyFromServer);
    }
}
