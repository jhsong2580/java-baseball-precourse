package baseball.domain.answerChecker;

import java.util.HashMap;

public interface Checker {
    HashMap<String, Integer> calcBallStrikeCount(String answer, String input);

    boolean checkAllStrike(int strikeCount);
}
