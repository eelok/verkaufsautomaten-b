package automat.net.client.connection;

import automat.mainlib.AutomatInterface;
import automat.mainlib.Beobachter;
import automat.mainlib.EinlagerungEntry;
import automat.mainlib.hersteller.Hersteller;
import automat.mainlib.kuchen.Kuchen;
import automat.net.common.Command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class DataSender implements AutomatInterface {
    ConnectionHelper connectionHelper;

    public DataSender(ConnectionHelper connectionHelper) {
      this.connectionHelper = connectionHelper;
    }

    public ConnectionHelper getConnectionHelper() {
        return connectionHelper;
    }

    @Override
    public void addHersteller(Hersteller hersteller) {
        try {
            sendDataToServer(hersteller.getName(), Command.ADD_HERSTELLER);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EinlagerungEntry addKuchen(Kuchen newKuchen, LocalDateTime date) {
        try {
            sendDataToServer(newKuchen.kuchenTypeToString(), Command.ADD_KUCHEN);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //todo change null
        return null;
    }

    @Override
    public List<String> getHerstellerWithNumberOfKuchen() {
        try {
            sendDataToServer("manufacturer", Command.LIST_HERSTELLER);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //todo change null
        return null;
    }

    @Override
    public List<String> getAllKuchenWithFachNum() {
        try {
            sendDataToServer("kuchen", Command.LIST_KUCHEN);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //todo change null
        return null;
    }


    @Override
    public void deleteHersteller(String name) {
        try {
            sendDataToServer(name, Command.DELETE_HERSTELLER);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EinlagerungEntry removeKuchenFromAutomat(int fachNummer) {
        try {
            sendDataToServer(String.valueOf(fachNummer), Command.DELETE_KUCHEN);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //todo change null
        return null;
    }

    @Override
    public int getPlatzImAutomat() {
        return 0;
    }

    @Override
    public Collection<Kuchen> getAllEingelagertenKuchen() {
        return null;
    }


    //todo make private
    public void sendDataToServer(String inputData, Command command) throws IOException, ClassNotFoundException {

        String dataForTransport = command + "/" + inputData;

        connectionHelper.getClientOutputStream().writeObject(dataForTransport);
        Object replyFromServer = connectionHelper.getClientInputStream().readObject();
        System.out.println(replyFromServer);
    }

    @Override
    public void meldeAn(Beobachter beobachter) {

    }

    @Override
    public void meldeAb(Beobachter beobachter) {

    }

    @Override
    public void benachrichtige(Class<? extends Beobachter> beobachterType) {

    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void setMessage(String message) {

    }
}
