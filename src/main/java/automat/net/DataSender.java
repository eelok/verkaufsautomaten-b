package automat.net;

import automat.net.heplers.ConnectionHelper;

import java.io.IOException;

public class DataSender {

    public DataSender() {
    }

    //todo change Object
    public void sendDataToServer(Object inputData) throws IOException, ClassNotFoundException {
        ConnectionHelper connectionHelper = ConnectionHelper.getConnectionHelperSingleton();

//        String dataForTransport = "";
//        dataForTransport = Command.addH + "/" + inputData;
//        dataForTransport = Command.addK + "/" + inputData;
//        dataForTransport = Command.listH + "/" + inputData;
//        dataForTransport = Command.listK + "/" + inputData;
//        dataForTransport = Command.delH + "/" + inputData;
//        dataForTransport = Command.delK + "/" + inputData;
//        connectionHelper.getClientOutputStream().writeObject(dataForTransport);
//        System.out.println("******************************");

        connectionHelper.getClientOutputStream().writeObject(inputData);
        Object replyFromServer = connectionHelper.getClientInputStream().readObject();

        System.out.println(replyFromServer);
    }
}
