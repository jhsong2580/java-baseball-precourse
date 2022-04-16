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
            numbersOfInput[answer.charAt(i) - ZERO_ASCII] = true; /* answer에 있는 숫자 칸마다 true로 변경 */
        }
        for (int i = 0; i < BASEBALL_ANSWER_SIZE; i++) {
            matchCount += getIntFromBoolean(numbersOfInput[input.charAt(i) - ZERO_ASCII]); /* input에 있는 숫자 칸이 true라면 matching으로 판단 */
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

    private int getIntFromBoolean(boolean flag) {/* 반환값 : 1(flag = true), 0(flag = false) */
        return 1 & Boolean.hashCode(flag) >> 1;
    }

    private boolean[] initBooleanArrayDefaultFalse(int size) {
        boolean checkDupNumber[] = new boolean[size]; /* 각 숫자별 선출된 기록을 저장 (ex : checkDupNumber[1] = true -> 1은 이미 선출되었다 */
        Arrays.fill(checkDupNumber, false);
        checkDupNumber[0] = true; /* 0은 선출될수 없으므로 true로 셋팅 */
        return checkDupNumber;
    }

}
