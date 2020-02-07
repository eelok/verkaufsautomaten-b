package automat.persistence.persistenceDelegate;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.time.Duration;

public class DurationPersistenceDelegate extends PersistenceDelegate {

    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        return oldInstance.equals(newInstance);
    }

    protected Expression instantiate(Object oldInstance, Encoder out) {
        Duration duration = (Duration) oldInstance;
        return new Expression(duration, duration.getClass(), "parse",
                new Object[] { duration.toString() });
    }
}
