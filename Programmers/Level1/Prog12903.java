package Programmers.Level1;

/**
 * Title : 가운데 글자 가져오기
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12903
 * 
 * Type : 문자열 
 */


public class Prog12903 {

    public String solution(String s) {
        String answer = "";
        int inputSize = s.length();
        
        if(inputSize%2 == 0){
            answer = s.substring(inputSize/2-1, inputSize/2 + 1);
        } else {
            answer = String.valueOf(s.charAt(inputSize/2));
        }
        
        return answer;
    }
    
}
