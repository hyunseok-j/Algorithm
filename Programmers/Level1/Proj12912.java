package Programmers.Level1;

/**
 * Title : 두 정수 사이의 합
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12912
 */

public class Proj12912 {
    
    public long solution(int a, int b) {
        long answer = 0;
        if(a == b){
            answer = a;
        } else {
            long len = Math.abs(a-b)+1;
            if (len % 2 == 0) {
                answer = (a+b)*(len/2);
            } else {
                answer = (a+b)*(len/2) + (a+b)/2;
            }
        } 
        
        return answer;
    }
}
