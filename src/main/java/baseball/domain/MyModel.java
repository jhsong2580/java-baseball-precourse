package baseball.domain;

import java.util.*;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class MyModel {
    private  int BASEBALL_MAX ;
    private  int BASEBALL_MIN ;
    private int BASEBALL_ANSWER_SIZE;
    private int checkDupNumber[] = new int[10]; //checkDupNumber[i] -> 0 : i는 현재 선출되지 않음. / 1 -> i는 이미 선출되어 answer에 추가하면 안됨.
    private String answer="";
    private boolean isEnd=false;


    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void initGame(){
        setAnswerCondition(3);
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

    public List<Integer> calcBallStrikeCount(String input){
        ArrayList<Integer> result = new ArrayList<>();
        int matchCount = calcMatchCount(input, BASEBALL_ANSWER_SIZE - 1);
        int strikeCount = calcStrikeCount(input, BASEBALL_ANSWER_SIZE - 1);
        int isNothing =1- (int) Math.ceil(matchCount/ (double)BASEBALL_ANSWER_SIZE);
        result.add(matchCount - strikeCount); //ball Count
        result.add(strikeCount); //strikeCount
        result.add(isNothing); //isNothing
        return result;
    }

    private void setAnswerCondition(int size){
        BASEBALL_ANSWER_SIZE = size;
        BASEBALL_MAX = (int)Math.pow(10,size) -1;
        BASEBALL_MIN = (int)Math.pow(10,size-1);
    }
    private int calcStrikeCount(String input,int index){
        if (index <0)
            return 0;
        int isStrike  = 1 & Boolean.hashCode(input.charAt(index) == answer.charAt(index)) >> 1;
        return isStrike + calcStrikeCount(input, index -1);
    }
    private int calcMatchCount(String input,int index){
        if(index<0)
            return 0;
        char[] answerToChar = answer.toCharArray();
        int matchingCount=BASEBALL_ANSWER_SIZE;
        for(int i=0;i<answerToChar.length;i++){

            matchingCount -=(int)Math.ceil(Math.abs(answerToChar[i] - input.charAt(index)) / 8.0);
        }
        return matchingCount + calcMatchCount(input,index-1);
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
        return checkInputWithRange(input) && checkInputWithoutZero(input,BASEBALL_ANSWER_SIZE-1) && checkInputDuplicateNumber(input);
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
        for(int i=0;i<BASEBALL_ANSWER_SIZE;i++)
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
        for(int i=0;i<BASEBALL_ANSWER_SIZE;i++)
            sourceCharacters.add(source.charAt(i));
        return sourceCharacters.size()==BASEBALL_ANSWER_SIZE;
    }

}
