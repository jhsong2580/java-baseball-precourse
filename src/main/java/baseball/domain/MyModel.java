package baseball.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class MyModel {
    private final int BASEBALL_MAX = 999;
    private final int BASEBALL_MIN = 111;
    private int checkDupNumber[] = new int[10]; //checkDupNumber[i] -> 0 : i는 현재 선출되지 않음. / 1 -> i는 이미 선출되어 answer에 추가하면 안됨.
    private String answer="";
    private boolean isEnd=false;


    public void initGame(){
        initDupCheckList();
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
        return checkInputWithRange(input) && checkInputWithoutZero(input,2) && checkInputDuplicateNumber(input);
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
            answer+=String.valueOf(getNumberNotDuplicate());
    }
    private int getNumberNotDuplicate(){
        int value=0;
        while(checkDupNumber[value] !=0){
            value = pickNumberInRange(1,9);
        }
        checkDupNumber[value]++;
        return value;
    }
    private void initDupCheckList(){
        Arrays.fill(checkDupNumber,0);
        checkDupNumber[0] = 1; // 0은 선출되면 안되므로 1로 Setting
    }
    private boolean checkInputDuplicateNumber(int input){
        String source = String.valueOf(input);
        Set<Character> sourceCharacters = new HashSet<>();
        for(int i=0;i<source.length();i++)
            sourceCharacters.add(source.charAt(i));
        return sourceCharacters.size()==3;
    }

}
