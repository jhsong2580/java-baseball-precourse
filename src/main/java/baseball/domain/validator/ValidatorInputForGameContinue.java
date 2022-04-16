package baseball.domain.validator;

public class ValidatorInputForGameContinue implements MyValidator {
    private final int CONTINUE_GAME;
    private final int END_GAME;

    public ValidatorInputForGameContinue(int CONTINUE_GAME, int END_GAME) {
        this.CONTINUE_GAME = CONTINUE_GAME;
        this.END_GAME = END_GAME;
    }

    @Override
    public boolean canValidate(boolean isEnd) { //게임 종료시 Input에 대한 처리 가능
        return isEnd;
    } /*게임이 종료되었을때만 검증 가능하다*/

    @Override
    public boolean validate(int input) {
        return input == CONTINUE_GAME || input == END_GAME;
    }
}
