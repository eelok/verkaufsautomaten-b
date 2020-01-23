package automat.net.server.handler;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    private Object data;
    private String error;

    public ServerResponse() {
    }

    public ServerResponse(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
