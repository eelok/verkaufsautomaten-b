package automat.apps.simulation;

import automat.mainlib.kuchen.Kuchen;

public interface Storage {
    void put(Kuchen kuchen) throws InterruptedException;
    void poll() throws InterruptedException;
}
