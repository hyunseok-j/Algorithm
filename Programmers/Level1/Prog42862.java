package Programmers.Level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Title : 체육복
 * 
 * URI : https://programmers.co.kr/learn/courses/30/lessons/42862
 * Level : 1
 * 
 * Type : 그리디
 *  도난당하지 않고 여벌 옷이 있어서 번호 앞 뒤의 학생에게 빌려줄 수 있는 학생 번호를 구했다. 
 *  체육복을 빌려줄 수 있는 학생은
 *     1) 앞 번호의 학생에게 체육복을 빌려주거나
 *     2) 뒷 번호의 학생에게 체육복을 빌려주는 
 *  2가지 행위 중 하나를 선택해서 할 수 있다.
 * 
 *  문제에서 학생들이 체육복을 다른 학생에게 빌려주어 체육복 가진 학생수의 최대값을 원하기 때문에 
 *   체육복을 도난당한 학생을 기준으로 앞의 학생에게 체육복을 빌려야 한다. 앞의 학생에게 빌리지 못할 경우 뒤의 학생에게 빌려야 한다. 
 * 
 *  왜냐하면 뒤의 학생에게 빌리는 것을 우선으로 하면 
 *  앞에 체육복 빌려줄 수 있는 학생들은 다른 사람한테 옷 빌려주지 못하고 여벌 옷이 낭비되기 때문이다.
 * 
 *  [Input]    [Output]
 *  5          4
 *  2 4
 *  3
 *  
 */

public class Prog42862 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Solution solution = new Solution();

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> lostArr = new ArrayList<>();
        ArrayList<Integer> reserveArr = new ArrayList<>();
        

        String[] line1 = br.readLine().split(" ");
        String[] line2 = br.readLine().split(" ");

        for(String s : line1){
            lostArr.add(Integer.parseInt(s));
        }

        for(String s : line2){
            reserveArr.add(Integer.parseInt(s));
        }        

        int[] lost = Arrays.stream(lostArr.toArray(Integer[] :: new))
                            .mapToInt(Integer::intValue)
                            .toArray();
        int[] reserve = Arrays.stream(reserveArr.toArray(Integer[] :: new))
                                .mapToInt(Integer::intValue)
                                .toArray();

        /**
         * int[] reserve = reserveArr.stream().mapToInt(Integer::intValue).toArray();
         */

        int answer = solution.solution(n, lost, reserve);

        System.out.print(answer);
    }
}

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] clothCount = new int[n+1];
        Arrays.fill(clothCount, 1);
        
        for(int i=0; i<lost.length; i++){
            clothCount[lost[i]] -= 1;
        }
        
        for(int i=0; i<reserve.length; i++){
            clothCount[reserve[i]] += 1;
        }
        
        for(int i=1; i<clothCount.length; i++){
            if(clothCount[i] == 0){
                if(i-1 >=0 && clothCount[i-1] == 2){
                    clothCount[i-1] -= 1;
                    clothCount[i] += 1;
                    answer++;
                } else if (i+1 <=n && clothCount[i+1] == 2){
                    clothCount[i+1] -= 1;
                    clothCount[i] += 1;
                    answer++;
                }
            } else {
                answer++;
            }
        }
        
        return answer;
    }
}