package Programmers.Level1;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12925
 * Title : 문자열 정수로 바꾸기
 *  단순 구현 문제, 바로 Integer.parseInt() 사용해도 되나 문제 의도와 다른 것 같아 아래와 같이 작성
 */

public class Proj12925 {
    public int solution(String s) {
        int answer = 0;
        int searchedPos = 0;
        char sign = 'n';
        
        char firstChar = s.charAt(searchedPos);
        
        if(firstChar == '+' || firstChar == '-'){
            sign = firstChar;
            searchedPos++;
        }
        
        while(searchedPos < s.length()){
            answer = answer * 10;
            answer += (int)(s.charAt(searchedPos) - '0');
            searchedPos++;
        }
        
        if(sign == '-') answer = -answer;
           
        return answer;
    }
}
