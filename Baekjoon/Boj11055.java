package Baekjoon;

import java.util.Scanner;

/**
 * Title : 가장 큰 증가하는 부분 수열
 * desc : 
 *  크기가 n인 수열이 주어졌을 때, 주어진 수열의 증가 부분 수열 중 원소들의 합이 가장 큰 증가 부분 수열의 합을 출력하시오.
 * 
 * Type : 다이나믹 프로그래밍
 * 
 * dp[i] : 0부터 i번째 수로 증가부분 수열을 만들 때, 
 *     주어진 수열의 i번째 수를 증가 부분 수열의 원소로 선택한 경우 
 *     만들 수 있는 모든 증가 부분 수열의 각각의 합 중 최대값
 */

public class Boj11055 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] orginalArray = new int[n];

        for(int i=0; i<n; i++){
            orginalArray[i] = sc.nextInt();
        }

        int[] dp = new int[n];
        dp[0] = orginalArray[0];

        for(int i=1; i<n; i++){
            int index = -1;
            int dpMax = Integer.MIN_VALUE;

            for(int j=i-1; j>=0; j--){
                if(orginalArray[j] < orginalArray[i] && dpMax < dp[j]){
                    index = j;
                    dpMax = dp[j];  
                }
            }
            if(index != -1) {
                dp[i] = dp[index] + orginalArray[i];
            } else {
                dp[i] = orginalArray[i];
            }
        }


        int answer = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            if(answer < dp[i]) answer = dp[i];
        }
        System.out.print(answer);
    }
    
}
