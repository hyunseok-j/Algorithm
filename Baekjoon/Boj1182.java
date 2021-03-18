package Baekjoon;

import java.util.Scanner;

/**
 * Title : 부분수열의 합
 * desc : 
 *  N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 
 *  그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하시오.
 */

public class Boj1182 {

    static int[] nums; // 입력된 수열

    // calc : 재귀, 부분수열의 원소로 입력받은 수열의 index번째 수의 선택여부 결정
    static int calc(int index, int selected, int sum, int n,int s){
        if(index==n){
            /* 종료 조건 : 입력받은 수열의 모든 수 선택 여부 결정 완료 */

            if(selected==0){
                return 0;        /* (부분 수열 = 공집합)은 문제 조건에 해당 되지 않아 count없이 종료 */
            }

            if(s==sum){
                return 1;        /* 부분 수열 구성 원소들의 합(sum)이 s와 같을 때 1번 count */
            }

            return 0;            /* 부분 수열 구성 원소들의 합(sum)이 s와 같지 않으면 count없이 종료 */
        }

        int count = 0;

        /* 현재 수 nums[index]를 부분 수열에 추가할 경우 */

        sum += nums[index];        /* 현재 수 nums[index]를 부분 수열에 추가했을 때의 모든 원소들의 합 계산 */

        count += calc(index+1, selected+1, sum, n, s);

        sum -= nums[index];        /* nums[index]를 추가하지 않을 경우를 위해 기존값으로 복원 */

        /* 현재 수 nums[index]를 부분 수열에 추가하지 않을 경우 */
        count += calc(index+1, selected, sum, n, s);

        return count;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        nums = new int[n];

        for(int i=0; i<n; i++){
            nums[i] = sc.nextInt();
        }
        int answer = calc(0,0,0,n,s);
        System.out.print(answer);
    }
}
