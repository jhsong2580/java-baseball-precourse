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
    private final int BASEBALL_ANSWER_SIZE; /* 정답&입력 자릿수 */
    private final int CONTINUE_GAME = 1;
    private final int END_GAME = 2;
    private final List<MyValidator> myValidators; /* 입력값 검증기들 */
    private final Generator myGenerator; /* BASEBALL_ANSWER_SIZE에 맞는 무작위 숫자 생성 */
    private final Checker myChecker; /* AllStrike여부 판단, Ball&Strike 갯수 판단 */
    private String answer = "";
    private boolean isEnd = false;/* 게임중일때 false, 정답을 맞춘 후에 true로 설정됨 */


    public MyModel(int BASEBALL_ANSWER_SIZE) {
        this.BASEBALL_ANSWER_SIZE = BASEBALL_ANSWER_SIZE;
        myValidators = getMyValidators(BASEBALL_ANSWER_SIZE);
        myGenerator = new RandomNumberGenerator(BASEBALL_ANSWER_SIZE);
        myChecker = new BallStrikeChecker(BASEBALL_ANSWER_SIZE);
    }

    private List<MyValidator> getMyValidators(int BASEBALL_ANSWER_SIZE) {
        final List<MyValidator> myValidators;
        myValidators = new ArrayList<>();
        myValidators.add(new ValidatorInputCheckRange(BASEBALL_ANSWER_SIZE));
        myValidators.add(new ValidatorInputWithoutZero(BASEBALL_ANSWER_SIZE));
        myValidators.add(new ValidatorInputDuplicateNumber(BASEBALL_ANSWER_SIZE));
        myValidators.add(new ValidatorInputForGameContinue(CONTINUE_GAME, END_GAME));
        return myValidators;
    }

    public void initGame() { /* 게임중으로 설정하기위해 isEnd를 false로, answer에 무작위 숫자 주입 */
        isEnd = false;
        answer = myGenerator.generateRandomNumber();
    }

    public void endGame() {
        isEnd = true;
    }

    public String validateInput(String input) {
        int inputCast = parseStringToInteger(input); /* parse 에러시 IllegalArgumentException 에러 throw */
        boolean validateResult = true;
        for (int i = 0; i < myValidators.size() & validateResult; i++) { /* 하나라도 validate 실패시 IllegalArgumentException 에러 throw */
            validateResult = validateResult & validateExecute(myValidators.get(i), inputCast);
        }
        if (!validateResult) {
            System.out.println("게임 종료");
            throw new IllegalArgumentException();
        }
        return input; /* 모든 validate 성공시 받았던 input 반환 */
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
