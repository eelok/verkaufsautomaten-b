package automat.persistence;

import automat.mainlib.Automat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.ObjectOutput;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AutomatRepositoryTest {


    @Test
    void should_save_to_file() throws IOException {
        Automat automat = mock(Automat.class);
        ObjectOutput out = mock(ObjectOutput.class);

        AutomatRepository.saveToFile(out, automat);

        verify(out).writeObject(automat);
    }

    @Test
    void should_throw_Exception() throws IOException {
        Automat automat = mock(Automat.class);
        ObjectOutput out = mock(ObjectOutput.class);

        Mockito.doThrow(new IOException()).when(out).writeObject(automat);
        assertThatThrownBy(() -> { AutomatRepository.saveToFile(out, automat);}).isInstanceOf(IllegalArgumentException.class);
    }


}