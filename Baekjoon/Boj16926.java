package Baekjoon;

import java.util.Scanner;

/**
 * Title : 배열 돌리기 1
 * desc : 
 *  n*m 배열을 모양은 그대로 두고 배열 안의 숫자만 반시계 방향으로 이동시킨 결과를 출력하시오. 
 *  [Ex]
 *  A[1][1] <- A[1][2] <- A[1][3]
 *     ↓                     ↑
 *  A[2][1]    A[2][2]    A[2][3]
 *     ↓                     ↑
 *  A[1][2] -> A[2][2] -> A[2][3]
 * 
 *  Type : 시뮬레이션
 */

public class Boj16926 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();

        int[][] array = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                array[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<r; i++){
            array = reverseRotateArray(array);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                sb.append(array[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int[][] reverseRotateArray(int[][] array){
        int n = array.length;
        int m = array[0].length;
        int[][] resultArray = new int[n][m];

        for(int k=0; k<(Math.min(n,m)/2); k++){
            
            int i = k;
            int j = k;
            while(i < (n-1)-k){
                resultArray[i+1][j] = array[i][j];
                i = i+1;
            }

            while(j < (m-1)-k){
                resultArray[i][j+1] = array[i][j];
                j = j+1;
            }

            while(i > k){
                resultArray[i-1][j] = array[i][j];
                i = i-1;
            }

            while(j > k){
                resultArray[i][j-1] = array[i][j];
                j = j-1;
            }
        }

        return resultArray;
    }
}
