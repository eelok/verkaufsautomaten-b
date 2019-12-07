package hersteller.observer;

import automat.Message;

public class RemoveHerstellerMessage implements Message {

    private String name;

    public RemoveHerstellerMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
