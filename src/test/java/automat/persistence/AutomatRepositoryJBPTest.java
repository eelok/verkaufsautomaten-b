package automat.persistence;

import automat.mainlib.Automat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AutomatRepositoryJBPTest {

    private AutomatRepositoryJBP automatRepositoryJBP;

    @BeforeEach
    void setUp() {
        automatRepositoryJBP = new AutomatRepositoryJBP();
    }

    @Test
    void should_call_method_writeObject() {
        Automat automat = mock(Automat.class);
        XMLEncoder encoder = mock(XMLEncoder.class);

        automatRepositoryJBP.saveToFile(encoder, automat);

        verify(encoder).writeObject(automat);

    }

    @Test
    void readFromFile_should_call_method_readObject(){
        Automat automat = mock(Automat.class);
        XMLDecoder decoder = mock(XMLDecoder.class);

        automatRepositoryJBP.readFromFile(decoder);
        verify(decoder).readObject();
    }

}