package baseball.domain;

import java.util.*;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class MyModel {
    private  int BASEBALL_MAX ;
    private  int BASEBALL_MIN ;
    private int BASEBALL_ANSWER_SIZE;
    private final int CONTINUE_GAME = 1;
    private final int END_GAME = 2;
    private String answer="";
    private boolean isEnd=false;



    public void initGame(){
        setAnswerCondition();
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
        int matchCount = calcMatchCount(input);
        int strikeCount = calcStrikeCount(input);
        int isNothing =1- (int) Math.ceil(matchCount / (double)BASEBALL_ANSWER_SIZE);
        result.add(matchCount - strikeCount); //ball Count
        result.add(strikeCount); //strikeCount
        result.add(isNothing); //isNothing
        return result;
    }

    public boolean checkAllStrike(int strikeCount){
        return strikeCount == BASEBALL_ANSWER_SIZE;
    }

    private void setAnswerCondition(){
        answer = "";
        BASEBALL_MAX = (int)Math.pow(10,BASEBALL_ANSWER_SIZE) -1;
        BASEBALL_MIN = (int)Math.pow(10,BASEBALL_ANSWER_SIZE-1);
    }
    private int calcStrikeCount(String input){
        int strikeCount = 0;
        for(int i=0;i<BASEBALL_ANSWER_SIZE;i++){
            strikeCount += 1 & Boolean.hashCode(input.charAt(i) == answer.charAt(i)) >> 1;
        }
        return strikeCount;
    }


    private int calcMatchCount(String input){
        int[] numbersOfInput = new int[10];
        int matchCount = 0;
        for ( int i = 0; i < BASEBALL_ANSWER_SIZE; i++){
            numbersOfInput[answer.charAt(i) - (int)'0'] += 1;
        }
        for ( int i = 0; i < BASEBALL_ANSWER_SIZE; i++){
            matchCount += 1&Boolean.hashCode((numbersOfInput[input.charAt(i) - (int)'0'] + 1)==2) >> 1;
        }
        return matchCount;
    }


    private boolean validateInputNotInGame(int input){
         return !isEnd || checkInputForGameContinue(input);
    }

    // TODO: 2022-04-15 1,2 define & method명 변경  
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
        boolean[] checkDupNumber = initDupCheckList();
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
    private boolean[] initDupCheckList(){
        boolean checkDupNumber[] = new boolean[10];
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
    }
}
