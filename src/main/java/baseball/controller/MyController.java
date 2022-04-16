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

    private void initGame() {
        myModel.initGame();
        int strikeCount = -1;
        do {
            String input = myModel.validateInput(myView.getInput());
            HashMap<String, Integer> ballStrikeResult = myModel.calcBallStrikeCount(input);
            myView.printResult(ballStrikeResult);
            strikeCount = ballStrikeResult.get("strike");
        } while (!myModel.checkAllStrike(strikeCount));
        myModel.endGame();
    }
}
