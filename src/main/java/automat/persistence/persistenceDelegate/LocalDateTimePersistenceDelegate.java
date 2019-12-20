package automat.persistence.persistenceDelegate;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.time.Duration;
import java.time.LocalDateTime;

public class LocalDateTimePersistenceDelegate extends PersistenceDelegate {

    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        return oldInstance.equals(newInstance);
    }

    protected Expression instantiate(Object oldInstance, Encoder out) {
        LocalDateTime localDateTime = (LocalDateTime) oldInstance;
        return new Expression(localDateTime, localDateTime.getClass(), "parse",
                new Object[] { localDateTime.toString() });
    }
}
