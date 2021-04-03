package Baekjoon.DP;

import java.util.Scanner;

/**
 * Title : 제곱수의 합
 * desc :
 *  자연수 N이 주어졌을 때, N은 제곱수의 합으로 표현이 가능하다. 
 *  예를 들면 11 = 3^2 + 1^2 + 1^2 = 2^2 + 2^2 + 2^2 + 1^2 + 1^2 와 같이 표현이 가능하다.
 *  이 때 각 제곱표현을 항이라고 할 때, N을 제곱수의 합으로 표현했을 때의 최소항 개수를 구하시오.
 * 
 * Type : 다이나믹 프로그래밍
 * dp[i] = i를 제곱수의 합으로 표현했을 때 최소항의 개수 라고 생각해보자.
 * 그리고 dp[i]를 계산할 때 마지막 제곱수를 놓는 경우를 나열해보면
 *     (i-(1^2)) + 1^2
 *     (i-(2^2)) + 2^2
 *     (i-(3^2)) + 3^2
 *     ...
 *     (i-(i^(1/2))^2) + (i^(1/2))^2
 *  와 같다.
 * 
 *  따라서 dp[i]는 자연수 (i-(j^2))을 제곱수의 합으로 표현하기 위해 사용한 최소항 개수(dp[i-(j^2)]) 와
 *      마지막에 놓은 항 개수(1)를 더한 값으로 볼 수 있다.
 * 
 *  이를 토대로 점화식을 세우면
 *   dp[i] = max(dp[i-(j^2)] + 1) (j=1, 2, 3, ..., i^(1/2)) 
 *  이다.
 * 
 *  세운 점화식을 Bottom-up방식으로 구현해서
 *  정답인 dp[n]을 구했다.
 */

public class Boj1699 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        
        /* 문제의 조건에 맞게 초기값 설정 */
        dp[0] = 0;

        /* DP로 자연수 i를 제곱수의 합으로 표현할 때 최소항 개수 계산 */
        for(int i=1; i<=n; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j=1; j<=(int)Math.sqrt(i); j++){
                if(dp[i] > dp[i-(int)Math.pow(j,2)] + 1){
                    dp[i] = dp[i-(int)Math.pow(j,2)] + 1;
                }
            }
        }
        
        /* 정답 출력 */
        System.out.print(dp[n]);
    }
}