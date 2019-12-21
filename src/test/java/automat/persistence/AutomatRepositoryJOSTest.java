package automat.persistence;

import automat.mainlib.Automat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

        automatRepositoryJOS.saveToFile(out, automat);

        verify(out).writeObject(automat);
    }

    @Test
    void should_throw_Exception() throws IOException {
        Automat automat = mock(Automat.class);
        ObjectOutput out = mock(ObjectOutput.class);

        Mockito.doThrow(new IOException()).when(out).writeObject(automat);

        assertThatThrownBy(() -> {
            automatRepositoryJOS.saveToFile(out, automat);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_read_from_File() throws IOException, ClassNotFoundException {
        ObjectInput ois = mock(ObjectInput.class);

        automatRepositoryJOS.readFromFile(ois);

        verify(ois).readObject();
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