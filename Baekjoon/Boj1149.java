package Baekjoon;

import java.util.Scanner;

/**
 * Title : RGB거리
 * desc : 
 *  자연수 N이 주어질 때, 1부터 N번째 집을 RGB 중 하나의 색깔로 색칠하려고 한다. 
 *  집을 어떤 색깔로 색칠하는지에 따라 그 비용도 달라진다. 
 *  이 때, 이웃한 집끼리의 색은 같지 않게 칠하는 조건을 만족하면서
 *  1부터 N번째 집을 색칠하는데 드는 최소 비용을 구하시오.  
 */

public class Boj1149 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dp = new int[n+1][3];   // dp[i][j] : i번째 집을 j색으로 칠할 때 1부터 i번째 집까지 색칠하는데 드는 최소 비용
        int[][] cost = new int[n+1][3]; // cost[i][j] : i번째 집을 j색으로 칠할 때 드는 비용
        
        /* i번째 집을 j색으로 칠할 때 드는 비용을 입력 받음 */
        for(int i=1; i<=n; i++){
            for(int j=0; j<3; j++){
                cost[i][j] = sc.nextInt();
            }
        }
        
        /* 첫 집을 색칠하는데 드는 비용 초기값으로 설정 */
        dp[1][0] = cost[1][0];
        dp[1][1] = cost[1][1];
        dp[1][2] = cost[1][2];
        
        /* DP를 통해 1~N까지의 집을 색칠하는데 드는 최소 비용 계산 */
        for(int i=2; i<=n; i++){
            for(int j=0; j<3; j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=0; k<3; k++){
                    if(j!=k){
                    /* i번째 집에 색칠한 j색과 다른 색을 i-1번째 집에 색칠한 경우 */

                        if(dp[i][j] > dp[i-1][k] + cost[i][j]){
                        /* 1부터 i-1번째 집을 색칠하는데 드는 최소 비용 찾기 */

                            dp[i][j] = dp[i-1][k] + cost[i][j];
                        }
                    }
                }
            }
        }
        
        System.out.print(Math.min(Math.min(dp[n][0],dp[n][1]), dp[n][2]));
    }
}