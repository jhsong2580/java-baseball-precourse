package baseball.domain.answerChecker;

import java.util.HashMap;

public interface Checker {
    HashMap<String, Integer> calcBallStrikeCount(String answer, String input);
    /*
     * 반환값 구조
     * 'strike' : strike갯수
     * 'ball'   : ball갯수
     * 'nothing': 1(ball/strike가 하나도없을때), 0(ball/strike가 하나라도 있을때)
     * */

    boolean checkAllStrike(int strikeCount); /* true(모든 숫자를 맞췄을때), false(1개라도 못맞췄을때) */
}
