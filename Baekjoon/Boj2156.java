package Baekjoon;

import java.util.Scanner;

/**
 * Title : 포도주 잔
 * desc : 
 *  n개의 포도주로 채워진 잔들이 일렬로 있다. 각 잔들에 담겨진 포도주의 양은 다르고 
 *  연속된 3잔은 마시지 못한다는 조건이 있다. 
 *  위의 조건을 만족하며 마실 수 있는 최대 포도주 양을 알아내시오.
 * 
 *  Type : 다이나믹 프로그래밍
 *  dp[i][j] = 확인한 i번째 잔의 포도주를 마실 지(j=1), 마시지 않을지(j=0) 결정했을 때, 최대 마신 포도주 양
 */

public class Boj2156 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] glasses = new int[n];

        for(int i=0; i<glasses.length; i++){
            glasses[i] = sc.nextInt();
        }

        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = glasses[0];
        
        if(n>=2){
            dp[1][0] = dp[0][1];
            dp[1][1] = dp[0][1] + glasses[1];
        }

        for(int i=2; i<dp.length; i++){
            for(int j=0; j<2; j++){
                if(j==0){
                    // i번째 잔 포도주 마시지 않은 경우
                    dp[i][j] = Math.max(dp[i-1][0], dp[i-1][1]);
                } else {
                    // i번째 잔 포도주 마신 경우
                    dp[i][j] = Math.max(dp[i-1][0] + glasses[i], dp[i-2][0] + glasses[i-1] + glasses[i]);
                }
            }
        }

        int answer = Math.max(dp[n-1][0], dp[n-1][1]);
        System.out.print(answer);
    }
    
}
