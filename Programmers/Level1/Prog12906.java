package Programmers.Level1;

import java.util.*;

/**
 * Title : 같은 숫자는 싫어
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12906
 * 
 * Type : 브루트포스 O(n)
 */

public class Prog12906 {
    
    public int[] solution(int[] arr){
        ArrayList<Integer> list = new ArrayList<>();        
        
        list.add(arr[0]);
        
        for(int index = 1; index< arr.length; index++){
            if(arr[index-1] != arr[index]){
                list.add(arr[index]);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
