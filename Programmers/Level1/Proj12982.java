package Programmers.Level1;

import java.util.Arrays;

/**
 * Title : 예산
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12982
 */
public class Proj12982 {

    public int solution(int[] d, int budget) {
        int answer = 0;
        int sum = 0;
        Arrays.sort(d);
        
        for(int i=0; i<d.length; i++){
            sum += d[i];
            if(sum <= budget){
                answer++;
            } else {
                break;
            }
        }
        
        return answer;
    }
}
