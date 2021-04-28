package Programmers.Level1;

import java.util.HashSet;

/**
 * Title : 폰켓몬
 * URI : https://programmers.co.kr/learn/courses/30/lessons/1845
 */

public class Proj1845 {

    public int solution(int[] nums) {
        int answer = 0;
        
        HashSet<Integer> countPerMonsterCategory = new HashSet<>();
        for(int category : nums){
            countPerMonsterCategory.add(category);
        }
        
        int m = countPerMonsterCategory.size();
        if(m <= nums.length/2){
            answer = m;
        } else {
            answer = nums.length/2;
        }
        
        return answer;
    }
    
}
