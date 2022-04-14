package baseball.view;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class MyView {
    public void printGuessResult(int strike, int ball, int isNothing) {
        ArrayList<String> results = new ArrayList<>();
        getPrintBallResult(results,ball);
        getPrintStrikeResult(results,strike);
        getPrintNothingResult(results,isNothing);

        System.out.println(String.join(" ", results));
    }
    public String actionAfterGameEnd(int wordSize) {
        System.out.println(wordSize+"개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        return readLine();
    }

    private void getPrintStrikeResult(ArrayList result,int strike){
        if(strike>0)
            result.add(strike + "스트라이크");
    }
    private void getPrintBallResult(ArrayList result,int ball){
        if(ball>0)
            result.add(ball+"볼") ;
    }
    private void getPrintNothingResult(ArrayList result,int isNothing){
        if(isNothing>0)
            result.add("낫싱") ;
    }
}
