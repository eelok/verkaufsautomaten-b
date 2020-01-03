package automat.apps.fx;

import automat.mainlib.Automat;
import javafx.collections.FXCollections;

public class AutomatSingleton {

    private static Automat automatInstance = new Automat(
        3,
        FXCollections.observableArrayList(),
        FXCollections.observableArrayList()
    );

    public static Automat getInstance() {
        return automatInstance;
    }

}
