package automat.net;

public abstract class NetworkConnection {

    protected abstract boolean isServer();

    protected abstract String getIP();

    protected abstract int getPort();
}
