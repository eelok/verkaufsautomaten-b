package automat.apps.fx.model;

import automat.mainlib.hersteller.Hersteller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class HerstellerFX implements Hersteller {

    private StringProperty name;

    public HerstellerFX(String name) {
        this.name = new SimpleStringProperty(name);
    }

    @Override
    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.get());
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
