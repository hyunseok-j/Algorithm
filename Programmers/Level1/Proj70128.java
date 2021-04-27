package Programmers.Level1;

/**
 * Title : 내적
 * URI : https://programmers.co.kr/learn/courses/30/lessons/70128
 * 
 * Type : 단순 구현
 */

public class Proj70128 {

    public int solution(int[] a, int[] b) {
        int answer = 0;
        
        for(int i=0; i<a.length; i++){
            answer += a[i]*b[i];
        }
        
        return answer;
    }
    
}
