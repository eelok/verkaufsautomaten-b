package hersteller.observer;

import automat.Message;

public class AddNewHerstellerMessage implements Message {

    private String name;

    public AddNewHerstellerMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
