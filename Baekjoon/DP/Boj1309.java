package Baekjoon.DP;

import java.util.Scanner;

public class Boj1309 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] dp = new long[2*(n+1)];
        long answer = 0L;


        dp[2*1+0] = 2;
        dp[2*1+1] = 3;

        if(n>=2){
            dp[2*2+0] = 5;
            dp[2*2+1] = 7;    
        }
        
        for(int i=3; i<=n; i++){
            for(int j=0; j<=1; j++){
                if(j==0) {
                    dp[2*i+j] = (dp[2*(i-2)+1]%9901 + dp[2*(i-2)+0]%9901 + dp[2*(i-1)+1]%9901) % 9901;
                } else {
                    dp[2*i+j] = (dp[2*(i-1)+0]%9901 + dp[2*i+0]%9901) % 9901;
                }
            }
        }

        answer = dp[2*n+1] % 9901;

        System.out.print(answer);

    }
}
