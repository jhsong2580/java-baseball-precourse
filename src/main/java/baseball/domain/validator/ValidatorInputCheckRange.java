package baseball.domain.validator;

public class ValidatorInputCheckRange implements MyValidator {
    private final int BASEBALL_MAX;
    private final int BASEBALL_MIN;

    public ValidatorInputCheckRange(int BASEBALL_ANSWER_SIZE) {
        this.BASEBALL_MAX = (int) Math.pow(10, BASEBALL_ANSWER_SIZE) - 1;
        this.BASEBALL_MIN = (int) Math.pow(10, BASEBALL_ANSWER_SIZE - 1);
    }

    @Override
    public boolean canValidate(boolean isEnd) { //게임이 진행중일때 검증 가능
        return !isEnd;
    } /*게임중에만 검증 가능하다*/

    @Override
    public boolean validate(int input) {
        return input >= BASEBALL_MIN && input <= BASEBALL_MAX;
    }
}
