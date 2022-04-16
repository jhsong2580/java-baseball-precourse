package baseball.domain.answerChecker;

import java.util.Arrays;
import java.util.HashMap;

public class BallStrikeChecker implements Checker {
    private final int BASEBALL_ANSWER_SIZE;
    private final int ZERO_ASCII;

    public BallStrikeChecker(int BASEBALL_ANSWER_SIZE) {
        this.BASEBALL_ANSWER_SIZE = BASEBALL_ANSWER_SIZE;
        this.ZERO_ASCII = (int) '0';
    }

    @Override
    public HashMap<String, Integer> calcBallStrikeCount(String answer, String input) {
        HashMap<String, Integer> result = new HashMap<>();
        int matchCount = calcMatchCount(answer, input);
        result.put("strike", calcStrikeCount(answer, input));
        result.put("ball", matchCount - result.get("strike"));
        result.put("nothing", getIntFromBoolean(matchCount == 0));
        return result;
    }

    @Override
    public boolean checkAllStrike(int strikeCount) {
        return strikeCount == BASEBALL_ANSWER_SIZE;
    }

    private int calcMatchCount(String answer, String input) {
        boolean[] numbersOfInput = initBooleanArrayDefaultFalse(10);
        int matchCount = 0;
        for (int i = 0; i < BASEBALL_ANSWER_SIZE; i++) {
            numbersOfInput[answer.charAt(i) - ZERO_ASCII] = true;
        }
        for (int i = 0; i < BASEBALL_ANSWER_SIZE; i++) {
            matchCount += getIntFromBoolean(numbersOfInput[input.charAt(i) - ZERO_ASCII]);
        }
        return matchCount;
    }

    private int calcStrikeCount(String answer, String input) {
        int strikeCount = 0;
        for (int i = 0; i < BASEBALL_ANSWER_SIZE; i++) {
            strikeCount += getIntFromBoolean(input.charAt(i) == answer.charAt(i));
        }
        return strikeCount;
    }

    private int getIntFromBoolean(boolean flag) {
        return 1 & Boolean.hashCode(flag) >> 1;
    }

    private boolean[] initBooleanArrayDefaultFalse(int size) {
        boolean checkDupNumber[] = new boolean[size];
        Arrays.fill(checkDupNumber, false);
        checkDupNumber[0] = true;
        return checkDupNumber;
    }

}
