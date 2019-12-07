package automat.mainlib.hersteller.observer;

import automat.apps.console.Message;

public class AddNewHerstellerMessage implements Message {

    private String name;

    public AddNewHerstellerMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
