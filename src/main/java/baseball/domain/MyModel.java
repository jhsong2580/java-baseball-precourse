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

    public void endGame(){
        isEnd = true;
    }

    public boolean validateInput(String input){

        int inputCast = parseStringToInteger(input);

        boolean validateResult = validateInputNotInGame(inputCast) && validateInputInGame(inputCast);
        if(!validateResult)
            throw new IllegalArgumentException();
        return true;

    }

    public String getAnswer() {
        return answer;
    }

    public boolean isEnd() {
        return isEnd;
    }
    private boolean validateInputNotInGame(int input){
        if(!isEnd)
            return true;
        return checkInputOneOrTwo(input);
    }
    private boolean checkInputOneOrTwo(int input){
        return input == 1 || input == 2;
    }
    private boolean validateInputInGame(int input){
        if(isEnd)
            return true;
        return checkInputWithRange(input) && checkInputWithoutZero(input,2);
    }
    private int parseStringToInteger(String input){
        try{
            return Integer.parseInt(input);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException();
        }

    }
    private boolean checkInputWithRange(int input){
        return input >= BASEBALL_MIN && input <= BASEBALL_MAX;
    }
    private boolean checkInputWithoutZero(int input,int index){
        if(index == -1)
            return true;
        int divResult = (int)(input / Math.pow(10, index));
        if(divResult == 0)
            throw new IllegalArgumentException();
        input -=divResult*Math.pow(10,index);
        return checkInputWithoutZero(input,--index);
    }

    private void generateRandomNumber(){
        for(int i=0;i<3;i++)
            answer+=String.valueOf(pickNumberInRange(1,9));
    }

}
