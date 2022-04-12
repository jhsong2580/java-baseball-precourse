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

    @ParameterizedTest
    @DisplayName("게임중일때 입력값이 111~999숫자이고 0이 들어가지 않으면 정상")
    @ValueSource(strings = {"111","999","142","346","677","232","0921"})
    public void validateNormalInputWhileGaming(String input){
        Assertions.assertThat(myModel.validateInput(input)).isTrue();
    }
    
    @ParameterizedTest
    @DisplayName("게임중일때 입력값이 111~999숫자가 아니고 0이 들어가있으면 비정상")
    @ValueSource(strings = {"100","99","1000","1111","203","410","013","010","870","a","-1"})
    public void validateAbnormalInputWhileGaming(String input){
        Assertions.assertThatThrownBy(() -> myModel.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @ParameterizedTest
    @DisplayName("게임끝났을떄일때 입력값이 숫자1,2면 정상")
    @ValueSource(strings = {"1","2"})
    public void validateNormalInputWhileNotGaming(String input){
        myModel.endGame();
        Assertions.assertThat(myModel.validateInput(input)).isTrue();
    }
    @ParameterizedTest
    @DisplayName("게임끝났을떄일때 입력값이 숫자1,2가 아니면 비정상")
    @ValueSource(strings = {"0","3","4","12","-1","a","999"})
    public void validateAbnormalInputWhileNotGaming(String input){
        myModel.endGame();
        Assertions.assertThatThrownBy(() -> myModel.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}