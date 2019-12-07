package automat.mainlib.hersteller.observer;

import automat.apps.console.Message;
import automat.apps.console.Observer;

public class RemoveHarstellerObserver implements Observer {

    @Override
    public void handleEvent(Message message) {
        RemoveHerstellerMessage removeHerstellerMessage = (RemoveHerstellerMessage)message;
        System.out.println(String.format("%s removed", removeHerstellerMessage.getName()));
    }

}
