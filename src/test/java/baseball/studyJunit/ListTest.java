package baseball.studyJunit;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

public class ListTest {
    private List<String> list;

    @BeforeEach
    public void init() {
        list = Arrays.asList("1", "2", "3");
    }

    @Test
    @DisplayName("List 내 값 확인")
    public void checkListValue() {
        Assertions.assertThat(list).isNotEmpty(); //List가 비어있지 않다.
        Assertions.assertThat(list).contains("1", "2", "3");
        Assertions.assertThat(list).contains("1", "2");
        Assertions.assertThat(list).contains("2", "1");

        Assertions.assertThat(list).startsWith("1");

    }


    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    public void testValueSourceToList(String data) {
        Assertions.assertThat(list).contains(data);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:0", "2:1", "3:2"}, delimiter = ':')
    public void testCsvSourceToList(String data, String index){
        Assertions.assertThat(list.get(Integer.parseInt(index))).isEqualTo(data);
    }
}
