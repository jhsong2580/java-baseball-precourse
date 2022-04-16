package baseball.domain.generator;

import java.util.Arrays;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class RandomNumberGenerator implements Generator {
    private final int BASEBALL_ANSWER_SIZE;

    public RandomNumberGenerator(int BASEBALL_ANSWER_SIZE) {
        this.BASEBALL_ANSWER_SIZE = BASEBALL_ANSWER_SIZE;
    }

    @Override
    public String generateRandomNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean[] checkDupNumber = initBooleanArrayDefaultFalse(10);
        for (int i = 0; i < BASEBALL_ANSWER_SIZE; i++)
            stringBuilder.append(getNumberNotDuplicate(checkDupNumber));
        return new String(stringBuilder);
    }

    private boolean[] initBooleanArrayDefaultFalse(int size) {
        boolean checkDupNumber[] = new boolean[size]; /* 각 숫자별 선출된 기록을 저장 (ex : checkDupNumber[1] = true -> 1은 이미 선출되었다 */
        Arrays.fill(checkDupNumber, false);
        checkDupNumber[0] = true; /* 0은 선출될수 없으므로 true로 셋팅 */
        return checkDupNumber;
    }

    private int getNumberNotDuplicate(boolean[] checkDupNumber) {
        int value = 0;
        while (checkDupNumber[value]) { /* 뽑은 숫자가 이미 선출된 기록이 있으면 재선출 */
            value = pickNumberInRange(1, 9);
        }
        checkDupNumber[value] = true;
        return value;
    }

}
