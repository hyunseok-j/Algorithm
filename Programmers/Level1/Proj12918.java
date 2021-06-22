package Programmers.Level1;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12918
 * Title : 문자열 다루기 기본
 *  길이는 length() 함수를 사용
 *  숫자인 문자를 포함하는지는 0과 9사이의 아스키 값을 가지는 문자는 숫자, 아니면 숫자가 아닌 경우로 판단
 */

public class Proj12918 {

    public boolean solution(String s) {
        boolean answer = true;
        int len = s.length();
        
        if((len != 4 && len != 6) || !isAllNumber(s)){
            answer = false;
        }
        
        return answer;
    }
    
    public boolean isAllNumber(String s){
        boolean ret = true;
        
        for(int i=0; i<s.length(); i++){
            char target = s.charAt(i);
            if('0' > target || target > '9') {
                ret = false;
                break;
            }
        }
        
        return ret;
    }
    
}
