package baseball.domain.validator;

public interface MyValidator {

    boolean canValidate(boolean isEnd);
    boolean validate(int input);
}
