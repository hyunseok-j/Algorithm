package Baekjoon.BruteForce_Backtracking;

import java.util.Scanner;

/**
 * Title : 배열돌리기 3
 * desc : 
 *  N*M 배열이 주어지고 6가지 종류의 배열내 원소를 이동시키는 연산이 있다. 
 *  1번 연산 : 상하 반전
 *  2번 연산 : 좌우 반전
 *  3번 연산 : 오른쪽 90도 회전
 *  4번 연산 : 왼쪽 90도 회전
 *  5번 연산 : 구역을 n/2, m/2 크기의 4분면으로 나눴을 때, 1->2, 2->3, 3->4, 4->1 로 이동
 *  6번 연산 : 구역을 n/2, m/2 크기의 4분면으로 나눴을 때, 1->4, 2->1, 3->2, 4->3 로 이동
 * 
 *  r개의 연산이 입력되었을 때, 입력된 연산들을 순서대로 수행한 배열의 결과를 출력하시오.
 * 
 *  Type : 시뮬레이션 
 */

public class Boj16935 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();

        int[][] array = new int[n][m];
        int[] operationArray = new int[r];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                array[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<r; i++){
            operationArray[i] = sc.nextInt();
        }

        for(int operationNum : operationArray){
            array = runOperationByNum(array, operationNum);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array[i].length; j++){
                sb.append(array[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static int[][] runOperationByNum(int[][] array, int operationNum){

        int n = array.length;
        int m = array[0].length;

        int[][] resultArray;

        if (operationNum == 1) {
            /* 상하 반전 */
            resultArray = new int[n][m];

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    resultArray[i][j] = array[(n-1)-i][j];
                }
            }

        } else if (operationNum == 2) {
            /* 좌우 반전 */
            resultArray = new int[n][m];

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    resultArray[i][j] = array[i][(m-1)-j];
                }
            }

        } else if (operationNum == 3) {
            /* 오른쪽 90도 회전 */
            resultArray = new int[m][n];

            for(int j=0; j<m; j++){
                for(int i=0; i<n; i++){
                    resultArray[j][(n-1)-i] = array[i][j];
                }
            }
        
        } else if (operationNum == 4) {
            /* 왼쪽 90도 회전 */
            resultArray = new int[m][n];

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    resultArray[(m-1)-j][i] = array[i][j];
                }
            }

        } else if (operationNum == 5) {
            /* 구역 이동 1 */
            resultArray = new int[n][m];

            // 1번 구역 -> 2번 구역
            for(int i=0; i<n/2; i++){
                for(int j=0; j<m/2; j++){
                    resultArray[i][j+m/2] = array[i][j];
                }
            }

            // 2번 구역 -> 3번 구역
            for(int i=0; i<n/2; i++){
                for(int j=m/2; j<m; j++){
                    resultArray[i+n/2][j] = array[i][j];
                }
            }

            // 3번 구역 -> 4번 구역
            for(int i=n/2; i<n; i++){
                for(int j=m/2; j<m; j++){
                    resultArray[i][j-m/2] = array[i][j];
                }
            }

            // 4번 구역 -> 1번 구역
            for(int i=n/2; i<n; i++){
                for(int j=0; j<m/2; j++){
                    resultArray[i-n/2][j] = array[i][j];
                }
            }

        } else {
            /* 구역 이동 2 */
            resultArray = new int[n][m];

            // 1번 구역 -> 4번 구역
            for(int i=0; i<n/2; i++){
                for(int j=0; j<m/2; j++){
                    resultArray[i+n/2][j] = array[i][j];
                }
            }

            // 2번 구역 -> 1번 구역
            for(int i=0; i<n/2; i++){
                for(int j=m/2; j<m; j++){
                    resultArray[i][j-m/2] = array[i][j];
                }
            }

            // 3번 구역 -> 2번 구역
            for(int i=n/2; i<n; i++){
                for(int j=m/2; j<m; j++){
                    resultArray[i-n/2][j] = array[i][j];
                }
            }

            // 4번 구역 -> 3번 구역
            for(int i=n/2; i<n; i++){
                for(int j=0; j<m/2; j++){
                    resultArray[i][j+m/2] = array[i][j];
                }
            }
        }

        return resultArray;
    }
}
