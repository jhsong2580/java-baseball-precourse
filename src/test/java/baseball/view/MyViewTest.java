package baseball.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

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
    public void testPrintGuessResult(String ball, String strike, String isNothing, String output) {
        int ball_ = Integer.parseInt(ball);
        int strike_ = Integer.parseInt(strike);
        int isNothing_ = Integer.parseInt(isNothing);

        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        myView.printGuessResult(strike_, ball_, isNothing_);
        Assertions.assertThat(output.trim()).isEqualTo(out.toString().trim());
    }
}