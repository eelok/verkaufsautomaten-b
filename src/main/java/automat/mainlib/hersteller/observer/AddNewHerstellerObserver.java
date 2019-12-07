package automat.mainlib.hersteller.observer;

import automat.apps.console.Message;
import automat.apps.console.Observer;

public class AddNewHerstellerObserver implements Observer {

    @Override
    public void handleEvent(Message message) {
        AddNewHerstellerMessage addNewHerstellerMessage = (AddNewHerstellerMessage) message;
        System.out.println(String.format("manufacturer %s added", addNewHerstellerMessage.getName()));
    }

}
