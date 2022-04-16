package baseball.domain.validator;

public interface MyValidator {

    boolean canValidate(boolean isEnd); /*게임 종료 여부에 따라 검증 가능 여부 반환*/

    boolean validate(int input); /*입력값 분류 결과 반환*/
}
