package Programmers.Level1;

/**
 * Title : 로또의 최고 순위와 최저 순위
 * URI : https://programmers.co.kr/learn/courses/30/lessons/77484
 * 
 * Type : 브루트포스
 */

public class Proj77484 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int zeroCount = 0;
        int winNumCount = 0;
        
        for(int i=0; i<lottos.length; i++){
            if(lottos[i] == 0){
                zeroCount++;
                continue;
            } 
            for(int j=0; j<win_nums.length; j++){
                if(lottos[i] == win_nums[j]){
                    winNumCount++;
                    break;
                }
            }
        }
        
        int max = zeroCount + winNumCount;
        int min = winNumCount;
        
        answer[0] = rank(max); 
        answer[1] = rank(min);
        
        return answer;
    }
    
    public int rank(int count){
        int ret = 0;
        switch(count){
            case 6: ret = 1; break;
            case 5: ret = 2; break;
            case 4: ret = 3; break;
            case 3: ret = 4; break;
            case 2: ret = 5; break;
            default:  ret = 6; break;
        }
            
        return ret;
    }
}
