package Programmers.Level1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Title : 나누어 떨어지는 숫자 배열
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12910
 */

public class Proj12910 {

    public int[] solution(int[] arr, int divisor) {
        int[] answer = null;
        ArrayList<Integer> answerList = new ArrayList<>();
        
        Arrays.sort(arr);
        
        for(int element : arr){
            if(element%divisor == 0){
                answerList.add(element);
            }
        }
        
        if(answerList.size() == 0){
            answer = new int[]{-1};
        } else {
            answer = answerList.stream().mapToInt(Integer::intValue).toArray();
        }
        
        return answer;
    }
    
}
