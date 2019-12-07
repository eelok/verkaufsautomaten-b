package hersteller.observer;

import automat.Message;
import automat.Observer;

public class RemoveHarstellerObserver implements Observer {

    @Override
    public void handleEvent(Message message) {
        RemoveHerstellerMessage removeHerstellerMessage = (RemoveHerstellerMessage)message;
        System.out.println(String.format("%s removed", removeHerstellerMessage.getName()));
    }

}
