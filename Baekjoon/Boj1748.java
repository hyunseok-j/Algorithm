package Baekjoon;

import java.util.Scanner;


/**
 * Title : 수 이어 쓰기 1
 * desc : 
 *   입력 n이 주어졌을 때, 1부터 n까지의 숫자를 '1234567891011...'와 같은 형태로 이어 붙여 만들어진 숫자의 자릿수를 구하는 문제
 */

class Boj1748{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int tmp = n;   
        int digit = 0;
        int answer = 0;
        
        // 입력 n이 몇자리 수(digit)인지 확인
        while(tmp!=0){
            tmp = tmp/10;
            digit++;
        }

        // 자릿수가 1 ~ digit-1인 수들은 자릿수가 같은 숫자들끼리 한번에 처리
        // ex ) 10-99 : (해당 구간 숫자 자릿수) * (해당 구간에 숫자 갯수) = (2) * (9*(10^1))
        for(int i=1; i<=digit-1; i++){
            answer += 9 * (int)Math.pow(10,i-1) * i;
        }
        
        // 자릿수가 n과 같이 digit인 숫자들을 처리
        // (해당 구간 숫자 자릿수) * (해당 구간에 숫자 갯수 : n에 따라 바뀌므로 위의 과정과 따로 계산)
        // ex ) n = 14001 -> (14001자릿수) * (10000~14001 범위의 숫자 갯수) = (5) * (14001-10000+1) 
        answer += digit * (n - (int)Math.pow(10,digit-1) + 1);
        
        System.out.print(answer);
    }
}
