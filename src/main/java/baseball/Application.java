package baseball;

//import baseball.controller.MyController;
import baseball.controller.MyController;
import baseball.domain.MyModel;
import baseball.view.MyView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        MyModel model = new MyModel(3);
        MyView view = new MyView();
        MyController controller = new MyController(model, view);
 

    }
}
