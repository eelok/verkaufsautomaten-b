package automat.net.command;

import automat.mainlib.hersteller.Hersteller;

import java.io.Serializable;

public class CommandAddHersteller implements Hersteller, Serializable {

    private String name;

    public CommandAddHersteller() {
    }

    public CommandAddHersteller(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
