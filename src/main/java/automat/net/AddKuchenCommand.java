package automat.net;

import java.io.Serializable;

public class AddKuchenCommand implements Serializable {
    private String inputData;

    public AddKuchenCommand(String inputData) {
        this.inputData = inputData;
    }

    public String getInputData() {
        return inputData;
    }
}
