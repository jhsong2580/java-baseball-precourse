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
        for (String key : ballStrikeResult.keySet()) {
            formatPrintSource(key, ballStrikeResult.get(key),results);
        }
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

    private void formatPrintSource(String key,int data,ArrayList result){
        if(data>0)
            result.add(String.format(printSources.get(key),data));
    }
    public MyView() {
        printSources = new HashMap<>();
        printSources.put("ball", "%d볼");
        printSources.put("strike", "%d스트라이크");
        printSources.put("nothing", "낫싱");
    }
}
