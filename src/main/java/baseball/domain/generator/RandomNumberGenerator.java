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
        boolean checkDupNumber[] = new boolean[size];
        Arrays.fill(checkDupNumber, false);
        checkDupNumber[0] = true;
        return checkDupNumber;
    }

    private int getNumberNotDuplicate(boolean[] checkDupNumber) {
        int value = 0;
        while (checkDupNumber[value]) {
            value = pickNumberInRange(1, 9);
        }
        checkDupNumber[value] = true;
        return value;
    }

}
