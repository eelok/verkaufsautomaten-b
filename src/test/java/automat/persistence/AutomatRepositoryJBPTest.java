package automat.persistence;

import automat.mainlib.Automat;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    void should_read_from_file(){
        Automat automat = mock(Automat.class);
        XMLDecoder decoder = mock(XMLDecoder.class);
        when(decoder.readObject()).thenReturn(automat);

        assertThat(automatRepositoryJBP.readFromFile(decoder)).isEqualTo(automat);
    }

}