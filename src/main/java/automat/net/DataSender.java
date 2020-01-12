package automat.net;

import java.io.IOException;

public class DataSender {

    public DataSender() {
    }

    public void sendDataToServer(String inputData) throws IOException, ClassNotFoundException {
        ConnectionHelper connectionHelper = ConnectionHelper.getConnectionHelperSingleton();

        String dataForTransport = "";
        dataForTransport = Command.addH + "/" + inputData;

        connectionHelper.getClientOutputStream().writeObject(dataForTransport);
        Object replyFromServer = connectionHelper.getClientInputStream().readObject();

        System.out.println(replyFromServer);
    }
}
