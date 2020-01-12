package automat.net;

import java.io.Serializable;

public class AddHerstellerCommand implements Serializable {

    private String data;

    public AddHerstellerCommand() {
    }

    public AddHerstellerCommand(String data) {
        this.data = data;
    }
}
