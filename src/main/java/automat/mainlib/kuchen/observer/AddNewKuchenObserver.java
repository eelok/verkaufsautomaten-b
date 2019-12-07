package automat.mainlib.kuchen.observer;

import automat.apps.console.Message;
import automat.apps.console.Observer;

public class AddNewKuchenObserver implements Observer {

    @Override
    public void handleEvent(Message message) {
        AddNewKuchenMessage addNewKuchenMessage = (AddNewKuchenMessage)message;
        System.out.println(String.format(
                "%s added for %s at f%s",
                addNewKuchenMessage.getEinlagerungEntry().getKuchen(),
                addNewKuchenMessage.getEinlagerungEntry().getKuchen().getHersteller().getName(),
                addNewKuchenMessage.getEinlagerungEntry().getFachnummer()
        ));
        int storageCapacity = addNewKuchenMessage.getStorageCapacity();
        int numberOfKuchenInStorage = addNewKuchenMessage.getNumberOfKuchenInStorage();
        if((storageCapacity - numberOfKuchenInStorage) == 1){
            System.out.println("only one place in Automat left");
        }
    }

}
