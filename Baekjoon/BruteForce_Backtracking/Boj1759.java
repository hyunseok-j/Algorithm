package Baekjoon.BruteForce_Backtracking;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Title : 암호 만들기
 * desc :
 *  암호로 사용했을 법한 문자가 C개 주어지고, 암호는 아래의 조건을 만족한다.
 *  1. 암호는 최소 한개의 모음(a,e,i,o,u)와 2개의 자음을 가지고 있다.
 *  2. 암호는 알파벳 오름차순으로 정렬되어 있다.
 *  3. 암호는 알파벳 소문자로 구성되어 있다.
 *  4. 암호는 중복된 알파벳을 사용하지 않는다.
 *  5. 암호의 길이는 L이다.
 *  이 때, 암호가 될만한 후보들을 모두 구하는 프로그램을 작성하시오.
 */

public class Boj1759 {
    static char[] input; // 암호로 사용했을 법한 문자 array
    static boolean[] check; // 암호 후보를 만들 때 i번째 알파벳이 사용되었는지 여부
    static char[] vowels = {'a','e','i','o','u'}; // 모음 array
    static StringBuilder sb = new StringBuilder();

    // getCandidates : 암호 후보 계산
    // input의 inputIndex번째 알파벳을 사용할 것인지, 안 할 것인지 결정
    static void getCandidates(int inputIndex, int l, int c, int count){

        // 종료 조건 : 암호로 사용할 알파벳 개수(count)가 문제에서 원하는 암호 길이(l)와 같게 되었을 때
        if(count == l){
            int vowelCount = 0;
            int consonauntCount = 0;

            // 암호를 구성하는 모음과 자음의 개수 계산
            for(int i=1; i<inputIndex; i++){
                boolean isVowel = false;
                for(int j=0; j<vowels.length; j++){
                    if(check[i]==true){
                        if(input[i]==vowels[j]){
                            vowelCount++;
                            isVowel = true;
                        }
                    }
                }
                if(check[i]==true && isVowel == false) consonauntCount++;
            }

            // 모음과 자음의 수가 문제의 조건을 만족할 때, 정답에 append
            if(vowelCount >=1 && consonauntCount>=2){
                 for(int i=1; i<inputIndex; i++){
                     if(check[i] == true){
                         sb.append(input[i]);
                     }
                 }
                 sb.append("\n");
                 return;
            }
            else{
                return;
            }
        }

        // 암호에 사용할 알파벳 개수(count)가 실제 암호 길이(l)에 못 미치지만 이미 input의 모든 알파벳을 다 확인한 경우, 유효하지 않은 경우이므로 바로 종료
        if(inputIndex > c) return;

        // input의 inputIndex번째 알파벳을 사용한 경우, inputIndex+1번째를 확인
        check[inputIndex]=true;
        getCandidates(inputIndex+1, l, c, count+1);

        // input의 inputIndex번째 알파벳을 사용하지 않은 경우, inputIndex+1번째를 똑같이 확인
        check[inputIndex]=false;
        getCandidates(inputIndex+1, l, c, count);
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int c = sc.nextInt();
        input = new char[c+1];
        check = new boolean[c+1];

        sc.nextLine();
        String inputLine = sc.nextLine().replaceAll("\\s+","");
        for(int i=1; i<=c; i++){
            input[i] = inputLine.charAt(i-1);
        }

        // 암호를 오름차순으로 처리하기 위해 미리 정렬
        Arrays.sort(input);

        // 암호 후보 구한 후, 후보들을 StringBuilder에 append
        getCandidates(1,l,c,0);

        // 정답 출력
        System.out.print(sb);
        
    }
}
