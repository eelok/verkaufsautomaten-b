package automat;

import automat.apps.console.service.StringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    private StringUtils stringUtils = new StringUtils();

    @Test
    void should_be_false_when_user_input_has_2_words() {
        assertThat(stringUtils.isOneWord("Alex Mond")).isFalse();
    }

    @Test
    void should_be_true_when_user_input_1_word() {
        assertThat(stringUtils.isOneWord(" Alex")).isTrue();
    }

    @Test
    void should_be_false_when_user_input_name_and_num() {
        assertThat(stringUtils.isOneWord(" Alex 45")).isFalse();
    }

    @Test
    void should_extract_Number_From_String() {
        assertThat(stringUtils.extractFachNumberFromString("f45")).isEqualTo(45);
    }
}
