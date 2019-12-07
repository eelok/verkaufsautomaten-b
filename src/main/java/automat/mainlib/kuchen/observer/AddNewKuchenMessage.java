package automat.mainlib.kuchen.observer;

import automat.mainlib.EinlagerungEntry;
import automat.apps.console.Message;

public class AddNewKuchenMessage implements Message {

    private EinlagerungEntry einlagerungEntry;
    private int storageCapacity;
    private int numberOfKuchenInStorage;

    public AddNewKuchenMessage(EinlagerungEntry einlagerungEntry, int storageCapacity, int numberOfKuchenInStorage) {
        this.einlagerungEntry = einlagerungEntry;
        this.storageCapacity = storageCapacity;
        this.numberOfKuchenInStorage = numberOfKuchenInStorage;
    }

    public EinlagerungEntry getEinlagerungEntry() {
        return einlagerungEntry;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public int getNumberOfKuchenInStorage() {
        return numberOfKuchenInStorage;
    }
}
