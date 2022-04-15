package baseball.controller;

import baseball.domain.MyModel;
import baseball.view.MyView;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class MyController {
    private final MyModel myModel;
    private final MyView myView;

    public MyController(MyModel myModel, MyView myView) {
        this.myModel = myModel;
        this.myView = myView;
    }

}
