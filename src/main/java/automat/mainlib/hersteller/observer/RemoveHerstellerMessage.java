package automat.mainlib.hersteller.observer;

import automat.apps.console.Message;

public class RemoveHerstellerMessage implements Message {

    private String name;

    public RemoveHerstellerMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
