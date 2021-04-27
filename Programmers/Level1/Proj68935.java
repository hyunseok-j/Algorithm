package Programmers.Level1;

/**
 * Title : 3진법 뒤집기
 * URI : https://programmers.co.kr/learn/courses/30/lessons/68935
 * 
 * Type : 단순 구현(진법 관련 - Integer.pasreInt(s,base) base진법->10진법, Integer.toString(i, base) 10진법->base진법)
 */

public class Proj68935 {

    public int solution(int n) {
        int answer = 0;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(Integer.toString(n,3));
        
        sb.reverse();
        
        answer = Integer.parseInt(sb.toString(),3);
        
        
        return answer;
    }
    
}
