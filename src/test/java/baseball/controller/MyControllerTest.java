package baseball.controller;

import baseball.Application;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedHashMap;
import java.util.Set;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyControllerTest extends NsTest {

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    @Test
    @DisplayName("Controller 정상동작 케이스")
    public void testNormalCase() {
        //given
        LinkedHashMap<String, String> testCase = new LinkedHashMap<>();
        String baseString = "숫자를 입력해주세요:";
        String restartString = "3개의 숫자를 모두 맞히셨습니다! 게임 종료\r\n" +
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

        testCase.put("179", baseString + "낫싱");
        testCase.put("219", baseString + "1스트라이크");
        testCase.put("825", baseString + "3볼");
        testCase.put("178", baseString + "1스트라이크");
        testCase.put("0852", baseString + "2볼 1스트라이크");
        testCase.put("257", baseString + "2스트라이크");
        testCase.put("0258", baseString + "3스트라이크");
        testCase.put("1", restartString);
        testCase.put("248", baseString + "낫싱");
        testCase.put("321", baseString + "1스트라이크");
        testCase.put("267", baseString + "1스트라이크");
        testCase.put("573", baseString + "3볼");
        testCase.put("375", baseString + "2볼 1스트라이크");
        testCase.put("357", baseString + "3스트라이크");
        testCase.put("2", restartString);

        Set<String> inputs_Set = testCase.keySet();
        String[] inputs = inputs_Set.toArray(new String[inputs_Set.size()]);
        String[] values = testCase.values().toArray(new String[testCase.values().size()]);

        assertRandomNumberInRangeTest(
                () -> {
                    run(inputs);
                    Assertions.assertThat(output().trim())
                            .isEqualTo(
                                    (String.join("\r\n", values) + "\r\n").trim()
                            );
                },
                2, 5, 8, 3, 5, 7
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1597", "109", "118", "1508", "12", "10", "1", "20", "201", "303", "99", "1000"})
    @DisplayName("Controller 이상동작 케이스")
    public void testException(String input) {

        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input))
                        .isInstanceOf(IllegalArgumentException.class)
        );

    }
}