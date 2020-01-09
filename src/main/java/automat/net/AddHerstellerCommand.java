package automat.net;

import automat.mainlib.hersteller.Hersteller;

import java.io.Serializable;

public class AddHerstellerCommand implements Serializable {
    private Command command;
    private String data;

    public AddHerstellerCommand() {
    }

    public AddHerstellerCommand(Command command, String data) {
        this.command = command;
        this.data = data;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
