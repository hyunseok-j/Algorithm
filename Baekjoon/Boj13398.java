package Baekjoon;

import java.util.Scanner;

/**
 * Title : 연속합 2
 * desc : 
 *  n개의 정수로 이루어진 임의의 수열이 주어진다. 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중
 *  가장 큰 합을 구하시오.
 * 
 *  단, 수는 한 개 이상 선택해야 한다.
 *  또, 수열에서 수를 하나 제거할 수 있다. (제거하지 않아도 된다.)
 * 
 *  [ex] 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 
 *    -> 수를 제거하지 않았을 때의 답 = 12+21 = 33
 *    -> -35를 제거한 경우의 답 = 10-4+3+1+5+6+12+21 = 54
 * 
 * 
 *  Type : 다이나믹 프로그래밍
 * 
 *  dp[i][j] = 수열의 i번째 수를 연속구간의 마지막 수로 결정하고 
 *              연속 구간 중 j 개의 수를 제거했을 때의 최대합
 * 
 *  조건에서 수는 한 개 이상은 선택해야 한다는 조건이 있으므로 
 *  마지막에서 dp[1][1]의 값을 a[1]로 바꾸고 
 *  전체 dp[i][j] 중 최대 값을 구한다. 
 */

public class Boj13398 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n+1];
        long[][] dp = new long[n+1][2];

        for(int i=1; i<=n; i++){
            a[i] = sc.nextInt();
        }

        dp[1][0] = a[1];
        dp[1][1] = 0;

        for(int i=2; i<=n; i++){
            dp[i][0] = Math.max(dp[i-1][0] + a[i], a[i]);
            dp[i][1] = Math.max(dp[i-1][1] + a[i], dp[i-1][0]);
        }

        long max = dp[1][0];

        for(int i=1; i<=n; i++){
            for(int j=0; j<2; j++){
                if(max < dp[i][j]) max = dp[i][j];
            }
        }

        System.out.print(max);
    }    
}
