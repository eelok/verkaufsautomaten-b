package automat.persistence.persistenceDelegate;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.math.BigDecimal;

/***
 * https://www.programcreek.com/java-api-examples/?code=servicecatalog/oscm/oscm-master/oscm-common/javasrc/org/oscm/converter/XMLSerializer.java
 */
public class BigDecimalPersistenceDelegate extends DefaultPersistenceDelegate {


    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        return oldInstance.equals(newInstance);
    }

    protected Expression instantiate(Object oldInstance, Encoder out) {
        BigDecimal bd = (BigDecimal) oldInstance;
        return new Expression(bd, bd.getClass(), "new",
                new Object[] { bd.toString() });
    }
}
