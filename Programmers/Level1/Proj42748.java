package Programmers.Level1;

import java.util.Arrays;

/**
 * Title : K번째 수
 * URI : https://programmers.co.kr/learn/courses/30/lessons/42748
 * 
 * Type : 정렬
 */

public class Proj42748 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i=0; i<commands.length; i++){
            int start = commands[i][0]-1;
            int end = commands[i][1]-1;
            int size = end-start+1;
            
            int[] temp = new int[size];
            for(int j=0; j<temp.length; j++){
                temp[j] = array[start+j];
            }
            
            Arrays.sort(temp);
            
            int k = commands[i][2]-1;
            answer[i] = temp[k];
        }
        
        return answer;
    }
}