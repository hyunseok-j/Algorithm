package Programmers.Level2;

/**
 * Title : 타겟 넘버
 * URI : https://programmers.co.kr/learn/courses/30/lessons/43165
 * 
 * Type : DFS/BFS
 */

public class Proj43165 {
    public int solution(int[] numbers, int target) {
        int answer = dfs(numbers, 0, 0, target);
        
        return answer;
    }
    
    public int dfs(int[] numbers, int index, int sum, int target){
        if(index == numbers.length){
            if(sum == target){
                return 1;
            }
            return 0;
        }
        int ret = 0;
        ret += dfs(numbers, index+1, sum-numbers[index], target);
        ret += dfs(numbers, index+1, sum+numbers[index], target);
        return ret;
    }
}
