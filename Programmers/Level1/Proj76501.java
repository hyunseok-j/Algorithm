package Programmers.Level1;

/**
 * Title : 음양 더하기 
 * URI : https://programmers.co.kr/learn/courses/30/lessons/76501
 * 
 * Type : 단순 구현
 */

public class Proj76501 {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        
        for(int i=0; i<absolutes.length; i++){
            if(signs[i] == true){
                answer += absolutes[i];
            } else {
                answer += -absolutes[i];
            }
        }
        
        return answer;
    }
}
