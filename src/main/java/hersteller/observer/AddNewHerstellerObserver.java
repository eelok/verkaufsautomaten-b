package hersteller.observer;

import automat.Message;
import automat.Observer;

public class AddNewHerstellerObserver implements Observer {

    @Override
    public void handleEvent(Message message) {
        AddNewHerstellerMessage addNewHerstellerMessage = (AddNewHerstellerMessage) message;
        System.out.println(String.format("manufacturer %s added", addNewHerstellerMessage.getName()));
    }

}
