package automat.apps.console;

import automat.mainlib.hersteller.observer.AddNewHerstellerMessage;
import automat.mainlib.hersteller.observer.RemoveHerstellerMessage;
import automat.mainlib.kuchen.observer.AddNewKuchenMessage;
import automat.mainlib.kuchen.observer.RemoveKuchenMessage;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private List<Observer> addNewHerstellerObservers = new ArrayList<>();
    private List<Observer> addNewKuchenObservers = new ArrayList<>();
    private List<Observer> removeKuchenObservers = new ArrayList<>();
    private List<Observer> removeHerstellerObserver = new ArrayList<>();

    public void registerAddNewHerstellerObserver(Observer addNewHerstellerObserver) {
        this.addNewHerstellerObservers.add(addNewHerstellerObserver);
    }

    public void notifyAddNewHerstellerObservers(AddNewHerstellerMessage message) {
        this.addNewHerstellerObservers.forEach(o -> o.handleEvent(message));
    }

    public void registerAddNewKuchenObserver(Observer addNewKuchenObserver) {
        this.addNewKuchenObservers.add(addNewKuchenObserver);
    }

    public void notifyAddNewKuchenObservers(AddNewKuchenMessage addNewKuchenMessage) {
        this.addNewKuchenObservers.forEach(o -> o.handleEvent(addNewKuchenMessage));
    }

    public void registerRemoveKuchenObserver(Observer removeKuchenObserver) {
        this.removeKuchenObservers.add(removeKuchenObserver);
    }

    public void notifyRemoveKuchenObservers(RemoveKuchenMessage removeKuchenMessage) {
        this.removeKuchenObservers.forEach(o -> o.handleEvent(removeKuchenMessage));
    }

    public void registerRemoveHarstellerObserver(Observer removeHerstellerObserver) {
        this.removeHerstellerObserver.add(removeHerstellerObserver);
    }

    public void notifyRemoveHerstellerObserver(RemoveHerstellerMessage removeHerstellerMessage) {
        this.removeHerstellerObserver.forEach(o -> o.handleEvent(removeHerstellerMessage));
    }
}
