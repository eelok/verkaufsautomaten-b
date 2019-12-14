package automat.mainlib.hersteller;

import java.io.Serializable;
import java.util.Objects;

public class HerstellerImplementation implements Hersteller, Serializable {

    private String name;

    public HerstellerImplementation() {
    }

    public HerstellerImplementation(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HerstellerImplementation that = (HerstellerImplementation) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
