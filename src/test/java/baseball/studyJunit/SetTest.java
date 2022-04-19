package baseball.studyJunit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    private Set<Integer> numbers;
    @BeforeEach
    public void init(){
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    @DisplayName("HashSet의 Size를 체크")
    public void checkSize() {
        Assertions.assertThat(numbers.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("HashSet의 데이터 확인")
    public void checkData (){
        Assertions.assertThat(numbers.contains(1)).isTrue();
        Assertions.assertThat(numbers.contains(2)).isTrue();
        Assertions.assertThat(numbers.contains(3)).isTrue();
        Assertions.assertThat(numbers.contains(4)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("ParameterizedTest를 통한 테스트코드 중복 제거")
    public void testValueSource(Integer input) {
        Assertions.assertThat(numbers.contains(input));
    }
    
    @ParameterizedTest
    @CsvSource(value = {"1:true","2:true","3:true","4:false","5:false"},delimiter = ':')
    @DisplayName("CsvSource를 통한 다중 인자 받기")
    public void testCsvSource(String data,String expected){
        Assertions.assertThat(numbers.contains(Integer.parseInt(data)))
                .isEqualTo(Boolean.parseBoolean(expected));
    }
}
