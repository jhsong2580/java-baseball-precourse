package baseball.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MyViewTest {
    MyView myView;
    @BeforeEach
    public void init(){
        myView = new MyView();
    }



    @ParameterizedTest
    @DisplayName("추측 결과에 대한 출력 확인")
    @CsvSource(value = {
            "0:0:1:낫싱",
            "1:0:0:1볼",
            "1:1:0:1볼 1스트라이크",
            "1:2:0:1볼 2스트라이크",
            "3:0:0:3볼",
            "0:3:0:3스트라이크"
    }, delimiter = ':')
    public void testPrintGuessResult(String ball, String strike, String nothing, String output) {
        //given
        HashMap<String, Integer> ballStrikeResult = new HashMap<>();
        ballStrikeResult.put("ball", Integer.parseInt(ball));
        ballStrikeResult.put("strike", Integer.parseInt(strike));
        ballStrikeResult.put("nothing", Integer.parseInt(nothing));

        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        //when
        myView.printResult(ballStrikeResult);

        //then
        Assertions.assertThat(output.trim()).isEqualTo(out.toString().trim());
    }

    @Test
    public void testActionAfterGameEnd(){
        //given
        int wordSize = 3 ;
        String input = "2";
        InputStream in = generateInputStream(input);
        System.setIn(in);

        //when
        String actionResult = myView.actionAfterGameEnd(wordSize);

        Assertions.assertThat(actionResult).isEqualTo(input);


    }

    @Test
    @DisplayName("readline으로 들어온 값을 반환한다. ")
    public void testGetInput (){
        //given
        String input = "1";
        InputStream in = generateInputStream(input);
        System.setIn(in);
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        //when
        String inputResult = myView.getInput();

        //then
        Assertions.assertThat(inputResult).isEqualTo(input);
        Assertions.assertThat(out.toString()).isEqualTo("숫자를 입력해주세요:");
    }
    private InputStream generateInputStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}