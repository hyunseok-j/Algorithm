package Programmers.Level1;

/**
 * Title : 소수 만들기
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12977
 * 
 * Type : 브루트포스 O((N^3)*(sqrt(M))) N<=50, M<=1000
 */

public class Prog12977 {
    public int solution(int[] nums) {
        int answer = 0;

        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                for(int k=j+1; k<nums.length; k++){
                    if(isPrime(nums[i] + nums[j] + nums[k])){
                        answer += 1;
                    }
                }
            }
        }
        
        return answer;
    }
    
    public boolean isPrime(int number){
        boolean ret = true;
        for(int i=2; Math.pow(i,2)<=number; i++){
            if(number%i==0){
                ret = false;
                break;
            }
        }
        return ret;
    }
}
