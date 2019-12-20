package automat.persistence.persistenceDelegate;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;


public class EnumPersistenceDelegate extends DefaultPersistenceDelegate {


    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        return oldInstance == newInstance;
    }


    protected Expression instantiate(Object oldInstance, Encoder out) {
        Enum<?> e = (Enum<?>) oldInstance;
        return new Expression(e, e.getClass(), "valueOf",
                new Object[] { e.name() });
    }
}
