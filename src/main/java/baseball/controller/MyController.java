package baseball.controller;

import baseball.domain.MyModel;
import baseball.view.MyView;

import java.util.HashMap;


public class MyController {
    private final MyModel myModel;
    private final MyView myView;

    public MyController(MyModel myModel, MyView myView) {
        this.myModel = myModel;
        this.myView = myView;
    }

    public void excute() {
        String input = "";
        do {
            initGame(); /* 게임시작 */
            input = myView.actionAfterGameEnd(myModel.getBASEBALL_ANSWER_SIZE()); /* 모든 정답을 맞추고 게임 지속 여부 확인 */
            myModel.validateInput(input);
        } while (input.equals("1"));
    }

    private void initGame() {
        myModel.initGame(); /* 게임 초기값 세팅 */
        int strikeCount = -1;
        do {
            String input = myModel.validateInput(myView.getInput()); /* 사용자 입력후 검증 */
            HashMap<String, Integer> ballStrikeResult = myModel.calcBallStrikeCount(input); /* Ball,Strike,nothing 획득 */
            myView.printResult(ballStrikeResult); /* 입력에대한 결과값 출력 */
            strikeCount = ballStrikeResult.get("strike");
        } while (!myModel.checkAllStrike(strikeCount));/* 모든 숫자를 맞췄을때 게임 종료 */
        myModel.endGame(); /* 게임 종료를 위한 값 세팅 */
    }
}
