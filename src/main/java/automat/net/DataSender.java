package automat.net;

import java.io.IOException;

public class DataSender {

    public DataSender() {
    }

    //todo change Object
    public void sendDataToServer(String inputData) throws IOException, ClassNotFoundException {
        ConnectionHelper connectionHelper = ConnectionHelper.getConnectionHelperSingleton();

        String dataForTransport = "";
//        dataForTransport = Command.addH + "/" + inputData;
        dataForTransport = Command.addK + "/" + inputData;

        connectionHelper.getClientOutputStream().writeObject(dataForTransport);
        Object replyFromServer = connectionHelper.getClientInputStream().readObject();

        System.out.println(replyFromServer);
    }
}
