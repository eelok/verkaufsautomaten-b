package automat.net;

import java.io.Serializable;

public class AddHersteller implements Serializable {

    private String data;

    public AddHersteller() {
    }

    public AddHersteller(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
