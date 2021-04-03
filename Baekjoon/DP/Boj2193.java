package Baekjoon.DP;

import java.util.Scanner;

/**
 * Title : 이친수
 * desc : 
 *  0과 1로 이뤄진 이진수 중에서 다음 조건을 만족하는 수를 '이친수'라고 한다.
 *      - 앞자리의 수는 0으로 시작할 수 없다. ex) 0101, 0010 은 제외
 *      - 1이 연속해서 붙어 있으면 안 된다.   ex) 11, 1101 은 제외
 *  이 때, N자리 이친수의 개수를 구하시오.
 * 
 * Type: 다이나믹 프로그래밍
 * 1. 문제 정의
 *     dp[n][i] : n자리 이친수 중 i로 끝나는 수
 * 2. 큰 문제를 작은 문제로 해결
 *     dp[n][i]는 i에 따라서 앞에 올 수 있는 수가 달라진다.
 *         - (i==1)
 *           1이 연속으로 올 수 없으므로 
 *            n-1자리 이친수 중 0으로 끝나는 수(dp[n-1][0])에 1을 붙이는 것만 가능하다.
 *         - (i==0)
 *           0은 연속으로 올 수 있으므로 
 *            n-1자리 이친수 중 0으로 끝나는 수(dp[n-1][0])에 0을 붙이는 경우 
 *            + n-1자리 이친수 중 1으로 끝나는 수(dp[n-1][1])에 0을 붙이는 경우
 *           가 가능하다.
 * 3. 점화식
 *     dp[n][i] = dp[n-1][0] (i==1) 
 *                  || dp[n-1][0] + dp[n-1][1] (i==0)
 * 
 * 4. 조건에 따른 초기값 설정
 *     1) 계산하지 않은 dp 값 = -1
 *     2) 앞자리에 0이 차지하지 않은 상태로 계산을 해나가야하므로
 *        1자리 이친수에서 0이 나오는 경우는 count에서 제외
 *        dp[1][0] = 0
 */

public class Boj2193 {

    static long[][] dp; // dp table, dp[n][i] = n자리 이친수 중 i로 끝나는 수
    

    /**
     * calcCount : n자리 이친수 중 endNum으로 끝나는 수의 개수
     */
    static long calcCount(int n, int endNum){

        /* 종료 조건 : 이미 이전에 계산된 값인 경우 */
        if(dp[n][endNum] != -1) return dp[n][endNum]; /* 이전 값 그대로 사용 */
        
        long result = 0;
        if(endNum == 1) {
        /* 끝자리가 1인 이친수 개수를 구할 때 */

            result += calcCount(n-1, 0);
        } else if(endNum == 0) {
        /* 끝자리가 0인 이친수 개수를 구할 때 */

            result += calcCount(n-1, 0) + calcCount(n-1,1);
        }
        
        /* 계산한 개수 중복 계산하지 않기 위해 dp에 저장 */
        dp[n][endNum] = result;
        
        /* 계산한 값 반환 */
        return dp[n][endNum];
        
    }
    
    static void initDp(){
        for(int i=0; i<dp.length; i++){
            for(int j=0; j<dp[i].length; j++){
                dp[i][j] = -1;
            }
        }
        dp[1][0] = 0;
        dp[1][1] = 1;
    }
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        dp = new long[n+1][2];

        /* dp 값 문제 조건에 맞게 설정 */
        initDp();
        
        /* n자리 이친수의 개수 출력 */
        System.out.print(calcCount(n,0) + calcCount(n,1));
    }   
}

