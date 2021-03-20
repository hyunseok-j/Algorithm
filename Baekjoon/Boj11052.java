package Baekjoon;

import java.util.Scanner;

/**
 * Title : 카드 구매하기
 * desc : 
 *  자연수 N이 주어지고 카드 1개가 포함된 카드팩 가격(P1), 카드 2개가 포함된 카드팩 가격(P2), ..., 카드 N개가 포함된 카드팩 가격(Pn)이 주어진다.
 *  카드 구매는 카드팩 단위로 구매 가능하고 N개 카드를 살 때 나올 수 있는 비용 중 최대 비용을 구하시오.
 * 
 *  Type : 다이나믹 프로그래밍
 *  1. 문제 정의
 *      d[n] : N개의 카드를 살 때 지불 가능한 최대 비용
 *  2. 작은 문제로 분리
 *      1)   N-1개의 카드를 살 때 지불 가능한 최대 비용(d[n-1]) + 카드 1개 카드팩 가격(P1)
 *      2)   N-2개의 카드를 살 때 지불 가능한 최대 비용(d[n-2]) + 카드 2개 카드팩 가격(P2)
 *      ...
 *      N-1) 1개의 카드를 살 때 지불 가능한 최대 비용(d[n-3]) + 카드 N-1개 카드팩 가격(Pn-1)
 *      N)   0개 카드를 살 때 지불 가능한 최대 비용(d[0]=0) + 카드 N개 카드팩 가격 (Pn)
 * 
 *      d[n]은 1), 2), ..., N-1), N) 중에서 가장 큰 값
 * 
 *  3. 점화식 세우기
 *      i가 1<=i<= n 일 때,
 *      d[n] = d[n-i] + Pi
 */

public class Boj11052 {
     
    static int[] d; // dp 테이블, d[n] : n개의 카드를 살 때 지불 가능한 최대 비용


    /**
     * calcMaxCost : 카드 n개 구매시, 지불 가능한 최대 비용 계산
     */
    static int calcMaxCost(int n,int[] p){

        if (n == 0) {
        /* 종료 조건 : 카드를 구매하지 않는 경우 */

            return d[0];                     /* 구매를 하지 않아 지불할 비용이 없으므로 0원 반환 */
        }
        
        if (d[n] != 0) {
        /* 이미 구한 적이 있는 값인 경우 */

           return d[n];                      /* 별도의 계산없이 이전에 구한 값 반환 */
        }
        

        /* 카드 N개 구매시, 지불 가능한 최대 비용(max) 계산  */
        
        int max = 0;
        for(int i=0; i<n; i++){
            int step = calcMaxCost(i,p) + p[n-i];
            if(max < step) max = step;
        }                                   

        d[n] = max;                         /* dp table에 계산한 값 저장 */
        
        return d[n];
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] p = new int[n+1];
        d = new int[n+1];
        
        for ( int i = 1; i <= n; i++ ) {
            p[i] = sc.nextInt();
        }
        
        
        d[0] = 0;    /* 초기값 설정 */

        int answer = calcMaxCost(n, p);
        
        System.out.println(answer);
    }
}

