package automat.net.command;

import java.io.Serializable;

public class CommandDeleteKuchen implements Serializable {

    private int fachNum;

    public CommandDeleteKuchen() {
    }

    public CommandDeleteKuchen(int fachNum) {
        this.fachNum = fachNum;
    }

}
