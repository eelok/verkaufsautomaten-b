package kuchen.observer;

import automat.Message;
import automat.Observer;

public class RemoveKuchenObserver implements Observer {

    @Override
    public void handleEvent(Message message) {
        RemoveKuchenMessage removeKuchenMessage = (RemoveKuchenMessage)message;
        System.out.println(String.format("%s removed", removeKuchenMessage.getKuchen()));
    }

}
