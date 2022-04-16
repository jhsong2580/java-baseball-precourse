package baseball.domain.validator;

public class ValidatorInputWithoutZero implements MyValidator {
    private final int BASEBALL_ANSWER_SIZE;

    public ValidatorInputWithoutZero(int BASEBALL_ANSWER_SIZE) {
        this.BASEBALL_ANSWER_SIZE = BASEBALL_ANSWER_SIZE;
    }

    @Override
    public boolean canValidate(boolean isEnd) {//게임이 진행중일때 검증 가능
        return !isEnd;
    }

    @Override
    public boolean validate(int input) {
        boolean isWithoutZero = true;
        String inputCastString = String.valueOf(input);
        for (int i = 0; i < BASEBALL_ANSWER_SIZE; i++) {
            isWithoutZero = isWithoutZero && (inputCastString.charAt(i) != '0');
        }
        return isWithoutZero;
    }


}
