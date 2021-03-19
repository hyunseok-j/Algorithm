package Baekjoon;

import java.util.Scanner;

/**
 * Title : 2*n 타일링
 * desc : 
 *  세로가 2이고 가로가 n인 직사각형을 1*2, 2*1 타일로 채우는 방법의 수를 구하시오.
 *  방법의 수는 10007의 나머지로 출력하시오.
 *  
 *  Type : 다이나믹 프로그래밍
 *  1. 문제 정의 
 *     d[n] = 2*n 직사각형을 2*1, 1*2 타일로 채우는 방법의 수
 *  2. 정의한 문제를 작은 문제로 분리
 *     d[n] = 1) 타일의 마지막 부분을 2*1 타일 1개로 채우고 나머지 2*(n-1)을 타일로 채우는(d[n-1]) 방법
 *            2) 타일의 마지막 부분을 1*2 타일 2개로 채우고 나머지 2*(n-2)를 타일로 채우는(d[n-2]) 방법
 *  3. Bottom-up 구현 : 반복문
 */

public class Boj11729 {
    static int[] d; // d[n] = 2*n 직사각형을 2*1, 1*2 타일로 채우는 방법의 수
    
    public static void main(String[] args){
        /**
         * 입력 처리
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d = new int[n+1]; 
        

        /**
         * 다이나믹 프로그래밍 구현
         *     d[n] = d[n-1] + d[n-2]
         * 
         * 문제의 조건에서 10007의 나머지를 요구했기 때문에 모듈러 성질 이용
         *     (a+b)%m = (a%m + b%m)%m     
         */
        d[0] = 1;
        d[1] = 1;
        for(int i=2; i<=n; i++){
            d[i] = (d[i-1] + d[i-2])%10007;
        }

        /**
         * 정답 출력
         */
        System.out.print(d[n]);
    }
}
