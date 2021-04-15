package Baekjoon;

import java.util.Scanner;


/**
 * Title : 가징 긴 바이토닉 부분 수열
 * desc : 
 *  길이가 n인 주어진 수열에서 아래의 조건을 만족하는 부분 수열을 바이토닉 부분 수열이라고 부른다.
 *   - 수열 내의 원소의 대소 관계가 S1 < S2 < ... < Sk > Sk+1 > Sk+2 > ... > Sn 을 만족
 *  주어진 수열로 만들 수 있는 바이토닉 부분 수열 중 가장 길이가 긴 바이토닉 부분 수열의 길이를 구하시오.
 * 
 *  Type : 다이나믹 프로그래밍
 *  바이토닉 부분 수열의 구성을 보면 증가하는 구간과 감소하는 구간으로 분리해서 생각할 수 있다. 
 *  그래서 j로 현재 부분 수열의 상태가 증가하는 구간인지 감소하는 구간인지 분리해서 값을 계산해 볼 수 있다.
 *   - j=0 : 현재 부분 수열의 상태가 증가하는 구간
 *   - j=1 : 현재 부분 수열의 상태가 감소하는 구간
 *   - dp[i][j] : i번째 수를 부분 수열의 원소로 선택하고 현재 부분 수열의 상태가 j일 때의 최대 길이
 */

public class Boj11054 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n];
        for(int i=0; i<n; i++){
            a[i] = sc.nextInt();
        }

        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;

        for(int i=1; i<n; i++){
            for(int j=0; j<2; j++){

                int maxLen = 0;
                if (j == 0) {
                    for(int k=i-1; k>=0; k--){
                        if(a[k] < a[i] && maxLen < dp[k][j]){
                            maxLen = dp[k][j];
                        }
                    }

                } else {
                    for(int k=i-1; k>=0; k--){
                        if(a[k] > a[i] && maxLen < Math.max(dp[k][0], dp[k][1])){
                            maxLen = Math.max(dp[k][0], dp[k][1]);
                        }
                    }

                }

                dp[i][j] = maxLen + 1;
            }
        }

        int answer = -1;
        for(int i=0; i<n; i++){
            for(int j=0; j<2; j++){
                if(answer < dp[i][j]){
                    answer = dp[i][j];
                }
            }
        }

        System.out.println(answer);
    }
    
}
