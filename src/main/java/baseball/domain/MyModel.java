package baseball.domain;

import baseball.domain.answerChecker.BallStrikeChecker;
import baseball.domain.answerChecker.Checker;
import baseball.domain.generator.Generator;
import baseball.domain.generator.RandomNumberGenerator;
import baseball.domain.validator.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyModel {
    private final int BASEBALL_ANSWER_SIZE;
    private final int CONTINUE_GAME = 1;
    private final int END_GAME = 2;
    private final List<MyValidator> myValidators;
    private final Generator myGenerator;
    private final Checker myChecker;
    private String answer = "";
    private boolean isEnd = false;

    public MyModel(int BASEBALL_ANSWER_SIZE) {
        this.BASEBALL_ANSWER_SIZE = BASEBALL_ANSWER_SIZE;
        myValidators = new ArrayList<>();
        myValidators.add(new ValidatorInputCheckRange(BASEBALL_ANSWER_SIZE));
        myValidators.add(new ValidatorInputWithoutZero(BASEBALL_ANSWER_SIZE));
        myValidators.add(new ValidateInputDuplicateNumber(BASEBALL_ANSWER_SIZE));
        myValidators.add(new ValidateInputForGameContinue(CONTINUE_GAME, END_GAME));
        myGenerator = new RandomNumberGenerator(BASEBALL_ANSWER_SIZE);
        myChecker = new BallStrikeChecker(BASEBALL_ANSWER_SIZE);
    }

    public void initGame() {
        isEnd = false;
        answer = myGenerator.generateRandomNumber();
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
        return myChecker.calcBallStrikeCount(answer, input);
    }

    public boolean checkAllStrike(int strikeCount) {
        return myChecker.checkAllStrike(strikeCount);
    }

    private int parseStringToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
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
