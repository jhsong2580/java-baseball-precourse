package baseball.domain;

import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MyModelTest {
    MyModel myModel = new MyModel();

    @BeforeEach
    public void init(){
        myModel.initGame();
    }

    @Test
    @DisplayName("initGame 후에 answer엔 1~9숫자 3개로 이루어진 값이 들어있고, isEnd엔 false가 들어가있다.")
    public void initGameTest (){
        //given
        String answer = myModel.getAnswer();
        boolean isEnd = myModel.isEnd();
        //then
        Assertions.assertThat(isEnd).isFalse();
        Assertions.assertThat(Integer.parseInt(myModel.getAnswer()))//answer은 111~999내 정의가 된다.
                .isGreaterThanOrEqualTo(111)
                .isLessThanOrEqualTo(999);
        Assertions.assertThat(answer)
                .doesNotContain("0");
    }
}