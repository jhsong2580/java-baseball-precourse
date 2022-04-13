package baseball.view;

import java.util.ArrayList;
import java.util.List;

public class MyView {
    public void printGuessResult(int strike, int ball, int isNothing) {
        ArrayList<String> results = new ArrayList<>();
        getPrintBallResult(results,ball);
        getPrintStrikeResult(results,strike);
        getPrintNothingResult(results,isNothing);

        System.out.println(String.join(" ", results));
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
