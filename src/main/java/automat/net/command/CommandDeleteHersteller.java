package automat.net.command;

import automat.mainlib.hersteller.Hersteller;

import java.io.Serializable;

public class CommandDeleteHersteller implements Hersteller, Serializable {
    private String name;

    public CommandDeleteHersteller() {
    }

    public CommandDeleteHersteller(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
