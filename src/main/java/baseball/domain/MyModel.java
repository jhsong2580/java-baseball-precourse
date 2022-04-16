package baseball.domain;

import baseball.domain.validator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class MyModel {
    private final int BASEBALL_ANSWER_SIZE;
    private final int CONTINUE_GAME = 1;
    private final int END_GAME = 2;
    private final int ZERO_ASCII = (int) '0';
    private final List<MyValidator> myValidators;
    private String answer = "";
    private boolean isEnd = false;

    public MyModel(int BASEBALL_ANSWER_SIZE) {
        this.BASEBALL_ANSWER_SIZE = BASEBALL_ANSWER_SIZE;

        myValidators = new ArrayList<>();
        myValidators.add(new ValidatorInputCheckRange(BASEBALL_ANSWER_SIZE));
        myValidators.add(new ValidatorInputWithoutZero(BASEBALL_ANSWER_SIZE));
        myValidators.add(new ValidateInputDuplicateNumber(BASEBALL_ANSWER_SIZE));
        myValidators.add(new ValidateInputForGameContinue(CONTINUE_GAME, END_GAME));
    }

    public void initGame() {
        isEnd = false;
        answer = "";
        generateRandomNumber();
    }

    public void endGame() {
        isEnd = true;
    }

    public boolean validateInput(String input) {
        int inputCast = parseStringToInteger(input);
        boolean validateResult = true;
        for (int i = 0; i < myValidators.size() & validateResult; i++) {
            validateResult = validateResult & validateExecute(myValidators.get(i), inputCast);
        }
        if (!validateResult) {
            System.out.println("게임 종료");
            throw new IllegalArgumentException();
        }
        return validateResult;
    }

    private boolean validateExecute(MyValidator validator, int input) {
        if (validator.canValidate(isEnd)) return validator.validate(input);
        return true;
    }

    public HashMap<String, Integer> calcBallStrikeCount(String input) {
        HashMap<String, Integer> result = new HashMap<>();

        int matchCount = calcMatchCount(input);
        result.put("strike", calcStrikeCount(input));
        result.put("ball", matchCount - result.get("strike"));
        result.put("nothing", getIntFromBoolean(matchCount == 0));
        return result;
    }

    public boolean checkAllStrike(int strikeCount) {
        return strikeCount == BASEBALL_ANSWER_SIZE;
    }

    private int calcStrikeCount(String input) {
        int strikeCount = 0;
        for (int i = 0; i < BASEBALL_ANSWER_SIZE; i++) {
            strikeCount += getIntFromBoolean(input.charAt(i) == answer.charAt(i));
        }
        return strikeCount;
    }

    private int getIntFromBoolean(boolean flag) {
        return 1 & Boolean.hashCode(flag) >> 1;
    }

    private int calcMatchCount(String input) {
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

    private int parseStringToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private void generateRandomNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean[] checkDupNumber = initBooleanArrayDefaultFalse(10);
        for (int i = 0; i < BASEBALL_ANSWER_SIZE; i++)
            stringBuilder.append(getNumberNotDuplicate(checkDupNumber));
        answer = new String(stringBuilder);
    }

    private int getNumberNotDuplicate(boolean[] checkDupNumber) {
        int value = 0;
        while (checkDupNumber[value]) {
            value = pickNumberInRange(1, 9);
        }
        checkDupNumber[value] = true;
        return value;
    }

    private boolean[] initBooleanArrayDefaultFalse(int size) {
        boolean checkDupNumber[] = new boolean[size];
        Arrays.fill(checkDupNumber, false);
        checkDupNumber[0] = true;
        return checkDupNumber;
    }

    public int getBASEBALL_ANSWER_SIZE() {
        return BASEBALL_ANSWER_SIZE;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getIsEnd() {
        return isEnd;
    }
}
