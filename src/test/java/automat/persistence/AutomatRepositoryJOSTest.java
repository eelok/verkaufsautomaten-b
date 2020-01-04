package automat.persistence;

import automat.mainlib.Automat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class AutomatRepositoryJOSTest {

    private AutomatRepositoryJOS automatRepositoryJOS;

    @BeforeEach
    void setUp() {
        automatRepositoryJOS = new AutomatRepositoryJOS();
    }


    @Test
    void should_call_write_object_on_object_output() throws IOException {
        Automat automat = mock(Automat.class);
        ObjectOutput out = mock(ObjectOutput.class);

        automatRepositoryJOS.writeToFile(out, automat);

        verify(out).writeObject(automat);
    }

    @Test
    void should_throw_Exception_when_writeObject() throws IOException {
        Automat automat = mock(Automat.class);
        ObjectOutput out = mock(ObjectOutput.class);

        Mockito.doThrow(new IOException()).when(out).writeObject(automat);

        assertThatThrownBy(() -> {
            automatRepositoryJOS.writeToFile(out, automat);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_read_from_File() throws IOException, ClassNotFoundException {
        ObjectInput ois = mock(ObjectInput.class);
        Automat automat = mock(Automat.class);
        when(ois.readObject()).thenReturn(automat);

        assertThat(automatRepositoryJOS.readFromFile(ois)).isEqualTo(automat);
    }


    @Test
    void should_throw_exception_when_read_From_File() throws IOException, ClassNotFoundException {
        ObjectInput ois = mock(ObjectInput.class);

        Mockito.doThrow(new IOException()).when(ois).readObject();

        assertThatThrownBy(() -> {
            automatRepositoryJOS.readFromFile(ois);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}