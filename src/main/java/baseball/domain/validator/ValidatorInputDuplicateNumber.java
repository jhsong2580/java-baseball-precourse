package baseball.domain.validator;

import java.util.HashSet;
import java.util.Set;

public class ValidatorInputDuplicateNumber implements MyValidator {
    private final int BASEBALL_ANSWER_SIZE;

    public ValidatorInputDuplicateNumber(int BASEBALL_ANSWER_SIZE) {
        this.BASEBALL_ANSWER_SIZE = BASEBALL_ANSWER_SIZE;
    }

    @Override
    public boolean canValidate(boolean isEnd) {//게임중일때 검증 가능
        return !isEnd;
    } /*게임중에만 검증 가능하다*/

    @Override
    public boolean validate(int input) {
        String source = String.valueOf(input);
        Set<Character> sourceCharacters = new HashSet<>();
        for (int i = 0; i < BASEBALL_ANSWER_SIZE; i++)
            sourceCharacters.add(source.charAt(i));
        return sourceCharacters.size() == BASEBALL_ANSWER_SIZE;
    }
}
