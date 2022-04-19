package baseball.studyJunit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.withPrecision;

public class NumberTest {
    @Test
    @DisplayName("소숫점 체크하기")
    public void testDecimal (){
        //given
        double source = 3.5;
        //when
        Assertions.assertThat(source)
                .isEqualTo(3,withPrecision(5d));
        //then
    }
}
