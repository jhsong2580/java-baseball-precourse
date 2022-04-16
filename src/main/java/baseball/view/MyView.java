package baseball.view;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class MyView {
    private final HashMap<String,String> printSources ;
    public void printResult(HashMap<String, Integer> ballStrikeResult) {
        ArrayList<String> results = new ArrayList<>();
        getPrintBallResult(results, ballStrikeResult.get("ball"));
        getPrintStrikeResult(results, ballStrikeResult.get("strike"));
        getPrintNothingResult(results, ballStrikeResult.get("matchCount"));

        System.out.println(String.join(" ", results));
    }
    public String actionAfterGameEnd(int wordSize) {
        System.out.println(wordSize+"개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        return readLine();
    }

    public String getInput(){
        System.out.print("숫자를 입력해주세요:");
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
    public MyView() {
        printSources = new HashMap<>();
        printSources.put("ball", "%d볼");
        printSources.put("strike", "%d스트라이크");
        printSources.put("nothing", "낫싱");
    }
}
