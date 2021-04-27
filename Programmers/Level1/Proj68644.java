package Programmers.Level1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Title : 두 개 뽑아서 더하기
 * URI : https://programmers.co.kr/learn/courses/30/lessons/68644/
 * 
 * Type : 브루트포스, 중복없게 처리
 */

public class Proj68644 {
    public int[] solution(int[] numbers) {
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] check = new boolean[201];
        
        for(int i=0; i<numbers.length; i++){
            for(int j=i+1; j<numbers.length; j++){
                int sum = numbers[i] + numbers[j];
                if(check[sum] == false){
                    list.add(sum);
                    check[sum] = true;
                }
            }
        }
        
        Collections.sort(list);
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
