package baseball.domain;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class MyModel {
    private final int BASEBALL_MAX = 999;
    private final int BASEBALL_MIN = 111;
    private String answer="";
    private boolean isEnd=false;


    public void initGame(){
        generateRandomNumber();
        isEnd = false;
    }


    public String getAnswer() {
        return answer;
    }

    public boolean isEnd() {
        return isEnd;
    }

    private void generateRandomNumber(){
        for(int i=0;i<3;i++)
            answer+=String.valueOf(pickNumberInRange(1,9));
    }

}
