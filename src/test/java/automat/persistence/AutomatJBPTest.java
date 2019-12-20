package automat.persistence;

import automat.mainlib.Automat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AutomatJBPTest {

    private AutomatJBP automatJBP;

    @BeforeEach
    void setUp() {
        automatJBP = new AutomatJBP();
    }

    @Test
    void should_saveToFile() {
        Automat automat = mock(Automat.class);
        XMLEncoder encoder = mock(XMLEncoder.class);

        automatJBP.saveToFile(encoder, automat);

        verify(encoder).writeObject(automat);

    }

    @Test
    void readFromFile(){
        Automat automat = mock(Automat.class);
        XMLDecoder decoder = mock(XMLDecoder.class);

        automatJBP.readFromFile(decoder);
        verify(decoder).readObject();
    }

}