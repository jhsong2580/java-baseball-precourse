package baseball.domain;

import java.util.*;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class MyModel {
    private final int BASEBALL_MAX ;
    private final int BASEBALL_MIN ;
    private final int BASEBALL_ANSWER_SIZE;
    private final int CONTINUE_GAME = 1;
    private final int END_GAME = 2;
    private final int ZERO_ASCII = (int)'0';
    private String answer="";
    private boolean isEnd=false;



    public void initGame(){
        isEnd = false;
        answer = "";
        generateRandomNumber();
    }

    public void endGame(){
        isEnd = true;
    }

    public boolean validateInput(String input){
        int inputCast = parseStringToInteger(input);
        boolean validateResult = validateInputNotInGame(inputCast) && validateInputInGame(inputCast);
        if(!validateResult){
            System.out.println("게임 종료");
            throw new IllegalArgumentException();
        }
        return true;

    }

    public HashMap<String, Integer> calcBallStrikeCount(String input){
        HashMap<String, Integer> result = new HashMap<>();

        int matchCount = calcMatchCount(input);
        result.put("strike", calcStrikeCount(input));
        result.put("ball",matchCount - result.get("strike"));
        result.put("nothing",getIntFromBoolean(matchCount==0));
        return result;
    }

    public boolean checkAllStrike(int strikeCount){
        return strikeCount == BASEBALL_ANSWER_SIZE;
    }

    private int calcStrikeCount(String input){
        int strikeCount = 0;
        for(int i=0;i<BASEBALL_ANSWER_SIZE;i++){
            strikeCount += getIntFromBoolean(input.charAt(i) == answer.charAt(i));
        }
        return strikeCount;
    }

    private int getIntFromBoolean(boolean flag){
        return 1 & Boolean.hashCode(flag) >> 1;
    }
    private int calcMatchCount(String input){
        boolean[] numbersOfInput = initBooleanArrayDefaultFalse(10);
        int matchCount = 0;
        for ( int i = 0; i < BASEBALL_ANSWER_SIZE; i++){
            numbersOfInput[answer.charAt(i) - ZERO_ASCII] =true;
        }
        for ( int i = 0; i < BASEBALL_ANSWER_SIZE; i++){
            matchCount += getIntFromBoolean(numbersOfInput[input.charAt(i) - ZERO_ASCII] );
        }
        return matchCount;
    }

    private boolean validateInputNotInGame(int input){
         return !isEnd || checkInputForGameContinue(input);
    }

    private boolean checkInputForGameContinue(int input){
        return input == CONTINUE_GAME || input == END_GAME;
    }

    private boolean validateInputInGame(int input){
        return isEnd || (checkInputWithRange(input) && checkInputWithoutZero(input) && checkInputDuplicateNumber(input));
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

    private boolean checkInputWithoutZero(int input){
        boolean isWithoutZero = true;
        String inputCastString = String.valueOf(input);
        for(int i=0;i<BASEBALL_ANSWER_SIZE;i++){
            isWithoutZero = isWithoutZero && (inputCastString.charAt(i)!= '0');
        }
        return isWithoutZero;
    }

    private void generateRandomNumber(){
        StringBuilder stringBuilder = new StringBuilder();
        boolean[] checkDupNumber = initBooleanArrayDefaultFalse(10);
        for(int i=0;i<BASEBALL_ANSWER_SIZE;i++)
            stringBuilder.append(getNumberNotDuplicate(checkDupNumber));
        answer = new String(stringBuilder);
    }

    private int getNumberNotDuplicate(boolean[] checkDupNumber){
        int value=0;
        while(checkDupNumber[value]){
            value = pickNumberInRange(1,9);
        }
        checkDupNumber[value] = true;
        return value;
    }

    private boolean[] initBooleanArrayDefaultFalse(int size){
        boolean checkDupNumber[] = new boolean[size];
        Arrays.fill(checkDupNumber,false);
        checkDupNumber[0] = true;
        return checkDupNumber;
    }

    private boolean checkInputDuplicateNumber(int input){
        String source = String.valueOf(input);
        Set<Character> sourceCharacters = new HashSet<>();
        for(int i=0;i<BASEBALL_ANSWER_SIZE;i++)
            sourceCharacters.add(source.charAt(i));
        return sourceCharacters.size()==BASEBALL_ANSWER_SIZE;
    }

    public int getBASEBALL_MAX() {
        return BASEBALL_MAX;
    }

    public int getBASEBALL_MIN() {
        return BASEBALL_MIN;
    }

    public int getBASEBALL_ANSWER_SIZE() {
        return BASEBALL_ANSWER_SIZE;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean getIsEnd() {
        return isEnd;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public MyModel(int BASEBALL_ANSWER_SIZE) {
        this.BASEBALL_ANSWER_SIZE = BASEBALL_ANSWER_SIZE;
        this.BASEBALL_MAX = (int)Math.pow(10,BASEBALL_ANSWER_SIZE) -1;
        this.BASEBALL_MIN = (int)Math.pow(10,BASEBALL_ANSWER_SIZE-1);
    }
}
