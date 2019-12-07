package automat.mainlib.kuchen.observer;

import automat.apps.console.Message;
import automat.apps.console.Observer;

public class RemoveKuchenObserver implements Observer {

    @Override
    public void handleEvent(Message message) {
        RemoveKuchenMessage removeKuchenMessage = (RemoveKuchenMessage)message;
        System.out.println(String.format("%s removed", removeKuchenMessage.getKuchen()));
    }

}
