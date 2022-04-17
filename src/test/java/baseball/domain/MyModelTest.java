package baseball.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.entry;

class MyModelTest {
    MyModel myModel = new MyModel(3);

    @BeforeEach
    public void init() {
        myModel.initGame();
    }

    @RepeatedTest(20)
    @DisplayName("initGame 후에 answer엔 1~9숫자 3개로 이루어진 값이 들어있고, isEnd엔 false가 들어가있다. 각자리 숫자는 모두 다르다.")
    public void initGameTest() {
        //given
        myModel.initGame();
        String answer = myModel.getAnswer();
        boolean isEnd = myModel.getIsEnd();
        //then
        Assertions.assertThat(isEnd).isFalse();
        Assertions.assertThat(Integer.parseInt(myModel.getAnswer()))//answer은 111~999내 정의가 된다.
                .isGreaterThanOrEqualTo((int) Math.pow(10, myModel.getBASEBALL_ANSWER_SIZE() - 1))
                .isLessThanOrEqualTo((int) Math.pow(10, myModel.getBASEBALL_ANSWER_SIZE()) - 1);
        Assertions.assertThat(answer)
                .doesNotContain("0");
        Assertions.assertThat(convertStringToCharSet(answer).size())
                .isEqualTo(3);

    }

    @ParameterizedTest
    @DisplayName("게임중일때 입력값이 111~999숫자이고 0이 들어가지 않으면 정상")
    @ValueSource(strings = {"142", "346", "0921", "426", "987", "123", "972", "184"})
    public void validateNormalInputWhileGaming(String input) {
        Assertions.assertThat(myModel.validateInput(input)).isEqualTo(String.valueOf(Integer.parseInt(input)));
    }

    @ParameterizedTest
    @DisplayName("게임중일때 입력값이 111~999숫자가 아니고 0이 들어가있거나 중복된 숫자가 들어가있으면 비정상")
    @ValueSource(strings = {"100", "99", "1000", "1111", "203", "410", "013", "010", "870", "a", "-1", "113", "442", "101", "111", "114", "999"})
    public void validateAbnormalInputWhileGaming(String input) {
        Assertions.assertThatThrownBy(() -> myModel.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("게임끝났을떄일때 입력값이 숫자1,2면 정상")
    @ValueSource(strings = {"1", "2"})
    public void validateNormalInputWhileNotGaming(String input) {
        myModel.endGame();
        Assertions.assertThat(myModel.validateInput(input)).isEqualTo(input);
    }

    @ParameterizedTest
    @DisplayName("게임끝났을떄일때 입력값이 숫자1,2가 아니면 비정상")
    @ValueSource(strings = {"0", "3", "4", "12", "-1", "a", "999"})
    public void validateAbnormalInputWhileNotGaming(String input) {
        myModel.endGame();
        Assertions.assertThatThrownBy(() -> myModel.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("낫싱&볼&스트라이크 테스트")
    @CsvSource(value = {"143:1:0:0", "253:1:0:0", "972:0:2:0", "472:0:3:0", "195:0:0:1"}, delimiter = ':')
    public void calcBallStrikeCountTest(String input, String ballCount, String strikeCount, String nothing) {
        myModel.setAnswer("472");
        HashMap<String, Integer> result = myModel.calcBallStrikeCount(input);
        Assertions.assertThat(result)
                .isNotEmpty()
                .containsExactly(
                        entry("ball", Integer.parseInt(ballCount)),
                        entry("strike", Integer.parseInt(strikeCount)),
                        entry("nothing", Integer.parseInt(nothing))
                );
    }

    @Test
    @DisplayName("strike갯수와 정답 자릿수가 같으면 true를 반환한다.")
    public void checkAllStrikeTest() {
        //given
        myModel.setAnswer("472");

        //when
        HashMap<String, Integer> trueResult = myModel.calcBallStrikeCount("472");
        boolean isAllStrikeExpectTrue = myModel.checkAllStrike(trueResult.get("strike"));
        HashMap<String, Integer> falseResult = myModel.calcBallStrikeCount("473");
        boolean isAllStrikeExpectFalse = myModel.checkAllStrike(falseResult.get("strike"));

        //then
        Assertions.assertThat(isAllStrikeExpectTrue).isTrue();
        Assertions.assertThat(isAllStrikeExpectFalse).isFalse();
    }

    private Set<Character> convertStringToCharSet(String source) {

        Set<Character> result = new HashSet();
        for (int i = 0; i < source.length(); i++) {
            result.add(source.charAt(i));
        }
        return result;
    }
}