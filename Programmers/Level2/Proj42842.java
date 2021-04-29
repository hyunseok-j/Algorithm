package Programmers.Level2;

/**
 * Title : 카펫
 * URI : https://programmers.co.kr/learn/courses/30/lessons/42842
 * 
 * Type : 브루트 포스
 */

public class Proj42842 {

    public int[] solution(int brown, int yellow) {
        int[] answer = {};

        int row = 0;
        int col = 0;
        boolean statisfied = false;
        
        while(!statisfied){
            for(row = 0; row<=col; row++){
                if(isBrownConditionRight(row,col,brown) && isYellowConditionRight(row,col,yellow)){
                    answer = new int[]{col, row};
                    statisfied = true;
                    break;
                }
            }
            col++;
        }
        return answer;
    }
    
    public boolean isBrownConditionRight(int row, int col, int brown){
        boolean ret = false;
        
        if ( 2*col + (row-2)*2 == brown ) {
            ret = true;
        }
        
        return ret;
    }
    public boolean isYellowConditionRight(int row, int col, int yellow){
        boolean ret = false;
        
        if(yellow == (row-2)*(col-2))
            ret = true;
        
        return ret;
    }
    
}
