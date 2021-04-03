package Baekjoon.DP;

import java.util.Scanner;

/**
 * Title : 가장 긴 증가하는 부분 수열
 * desc : 
 *  n개의 수로 된 수열(S)이 주어졌을 때, 
 *  가장 긴 증가하는 부분 수열(LIS)과 LIS의 길이를 구하시오.
 *  ex) n=6, {10,20,10,30,20,50} -> LIS 길이 = 4, LIS = {10,20,30,50}
 * 
 * Type : 다이나믹 프로그래밍
 *  1. 문제 정의
 *   dp[i] : S의 i번째 수(S[i])가 마지막 수인 LIS의 길이
 *   v[i] : S의 i번째 수(S[i])가 마지막 수인 LIS에서 
 *      S[i] 바로 앞에 와야 하는 수의 (S에서의) index
 *      = LIS 상에서 마지막 바로 앞에 올 수의 index
 *  2. 최종 정답 계산
 *   1) dp[i] = (j<i && S[j]<S[i])를 만족하는 dp[j] 중에 max값 + 1
 *   2) v[i] = 위에서 구한 max(dp[j])일 때의 j
 *   3) dp[1], dp[2], ... , dp[n] 중 max 값을 찾아 S의 LIS의 길이를 구하고
 *   4) dp[i]가 max일 때의 i 값과 v를 통해 역추적하면서 LIS 전체 원소 출력
 */

public class Boj14002 {

    /**
     * printLis : LIS의 구성원소 처음부터 마지막순으로 출력
     * @param index : 현재 출력할 수가 input의 몇 번째인지를 의미
     * @param v     : LIS에서 바로 앞에 있는 수의 index
     * @param input : 처음 입력으로 주어진 수열
     */
    static void printLis(int index, int[] v, int[] input){
        if(index == -1) return;
        printLis(v[index], v, input);
        System.out.print(input[index]+" ");
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        int[] input = new int[n+1];
        int[] v = new int[n+1];
        

        for(int i=1; i<=n; i++){
            input[i] = sc.nextInt();
        }

        /* dp, v 문제 조건에 맞게 초기값 설정 */
        dp[1] = 1;
        v[1] = -1;

        /* 다이나믹 프로그래밍 통해 dp, v 계산 */
        for(int i=2; i<=n; i++){
            int maxValue = -1;
            dp[i] = 1;
            v[i] = -1;
            for(int j=i-1; j>=1; j--){
                if(input[j] < input[i]){
                    if(dp[j] > maxValue){
                        v[i] = j;
                        maxValue = dp[j];
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }

        /* 각 경우에 구한 LIS 길이 중 max값 계산 */
        /* LIS의 마지막 수를 가리키는 index 탐색 */
        int answer = 0;
        int lisLastIndex = -1;
        for(int i=1; i<=n; i++){
            if(answer < dp[i]){
                answer = dp[i];
                lisLastIndex = i;
            }
        }

        /* 주어진 수열 input의 LIS 길이 출력 */
        System.out.println(answer);

        /* v를 통해 LIS의 끝에서부터 인접한 수들을 역추적하며 전체 LIS 원소 출력 */
        printLis(lisLastIndex, v, input);
    }
}
