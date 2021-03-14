package Baekjoon;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Title : 부등호
 * desc :
 *  입력할 부등호의 개수 k 가 주어지고 "< < > > <"와 같이 공백을 기준으로 부등호가 입력으로 주어진다.
 *  이 때, 0~9 사이의 정수를 중복없이 k+1개를 선택해 부등호 사이에 집어넣어 부등호 관계가 성립되는 수를 계산한다.
 *  계산한 수를 이어 붙여서 만든 수 중에 최대값과 최소값을 구하시오. 
 *  단, 021과 같이 최대, 최소값의 가장 앞자리에 0이 생략되어선 안된다.
 */

public class Boj2529 {

    static char[] signs; // 입력받은 부등호 
    static boolean[] used = new boolean[10]; // 0~9 사이 숫자 중 선택된 적이 있는지 여부

    static long max = 0; // 최대값
    static long min = 9876543210L; // 최소값


    // chooseNum : pickNums에 index번째로 추가될 숫자를 결정
    static void chooseNum(int index, int k, ArrayList<Integer> pickNums){

        // 종료 조건 : pickNums에 0부터 index까지 총 index+1 개의 숫자를 선택해서 문제에서 요구한 k+1개만큼 선택이 완료된 경우
        if(index > k){
            // 부등호가 성립되는지 확인
            if(isCorrect(pickNums)==true){
                // pickNums에 선택한 숫자들을 이어붙인 수를 계산
                long result = 0;
                for(int i=0; i<pickNums.size(); i++){
                    result *= 10;
                    result += pickNums.get(i);
                }
                // 기존의 최대보다 크면 최대값 갱신
                if(max < result) max = result;
                // 기존의 최소보다 작으면 최소값 갱신
                if(min > result) min = result;
            }
            return;
        }

        // 0부터 9 숫자 중에서 이전에 사용되지 않은 숫자를 하나씩 선택
        for(int i=0; i<10; i++){
            if(used[i]==false){
                // 숫자 i가 선택되어 사용되었음을 표시
                used[i] = true;
                // 숫자 i를 선택된 숫자들 ArrayList에 추가
                pickNums.add(i);
                // 다음 선택할 숫자를 결정하기 위해 재귀 호출
                chooseNum(index+1, k, pickNums);
                // 숫자 i를 사용하지 않는 경우 고려를 위해 앞에서 추가한 pickNums의 item과 used 재설정
                pickNums.remove(pickNums.size()-1);
                used[i] = false;
            }
        }
    } 

    // isCorrect : 선택된 숫자들이 부등호 관계를 만족하는지 판단
    static boolean isCorrect(ArrayList<Integer> pickNums){
        boolean ok = true;

        // 선택된 모든 숫자들과 입력된 부등호를 순차적으로 확인
        for(int i=0; i<pickNums.size()-1; i++){
            int a = pickNums.get(i);
            int b = pickNums.get(i+1);

            // 부등호 관계가 하나라도 성립하지 않으면 false
            if(signs[i]=='>'){

                if(!(a > b)){
                    ok = false;
                    break;
                }
            }
            else{
                if(!(a < b)){
                    ok = false;
                    break;
                }
            }
        }

        // 부등호 관계가 모두 성립해 true
        return ok;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        sc.nextLine();
        signs = sc.nextLine().replaceAll("\\s+","").toCharArray();

        ArrayList<Integer> pickNums = new ArrayList<>();
        chooseNum(0, k, pickNums);

        String maxStr = String.valueOf(max);
        String minStr = String.valueOf(min);

        StringBuilder answer = new StringBuilder();

        // long 정수형으로 처리하면서 사라질 수 있는 앞 자리 '0' 추가
        if(maxStr.length() < k+1){
            int t = k+1 - maxStr.length();
            while(t-- > 0){
                answer.append("0");
            }
        }
        answer.append(maxStr+"\n");

        if(minStr.length() < k+1){
            int t = k+1 - minStr.length();
            while(t-- > 0){
                answer.append("0");
            }
        }
        answer.append(minStr);

        // 정답 출력
        System.out.print(answer);
    }    
}
