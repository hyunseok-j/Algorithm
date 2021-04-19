package Baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Title : 0과 1
 * desc : 
 *  자연수 n이 주어졌을 때, n의 배수 중 아래의 조건을 만족하는 숫자 아무거나 하나를 출력하시오.
 *  - 0과 1로만 이루어진 숫자여야 한다.
 *  - 맨 앞자리는 1이어야 한다.
 *  - 숫자의 길이는 최대 100 이다.
 *  - 1이 적어도 하나 있어야 한다.
 * 
 * Type : BFS && 나머지
 *  1) 길이가 100이고 위의 조건을 만족하는 숫자를 모두 고려하려면 2^100 개의 수를 고려해야 한다. 
 * 
 *    1-1) 결과가 중복되는 패턴을 찾아 한 번만 탐색하게 구현한다. (백트래킹)
 *        - 이 방법을 채택, 'n의 배수' 라는 말은 'n으로 나눴을 때 나머지가 0이 되는 수' 라고 해석해서 
 *          n의 배수인지 확인하기 위해 '나머지' 만 있으면 되므로 
 *          1과 0으로 이뤄진 수들을 나머지 형태로 저장하는 방법을 생각했다. 
 *          결과적으로 1과 0으로 이뤄진 수들의 n으로 나눴을 때, 나머지는 0 ~ n-1 에서 반복해서 나올 것이므로 
 *          동일한 나머지가 나오는 수는 중복해 방문하지 않도록 check[나머지] 로 관리했다.
 *          
 *          1, 10, 11, 100, 101, 110, 111, ... 의 숫자들을 BFS로 탐색하며 
 *            - 나머지==0 인 경우, 조건을 만족하는 수가 있는 경우이므로 그 수를 아래의 2-1) 방법으로 출력         
 *            - check[나머지]의 모든 값이 true면 조건을 만족하는 수가 없는 경우이므로 "BRAK" 출력
 * 
 *    1-2) 작은 문제로 큰 문제를 해결할 수 있는지 확인한다. (DP)
 * 
 *  2) 길이가 100자리인 수는 int나 long으로 저장하지 못하므로 수를 관리할 방법을 생각해야 한다. 
 * 
 *    2-1) 1의 자리수, 10의 자리수, ... , 10^99 자리의 수 (총100개) 를 하나씩 상태를 기록해 처리한다. 
 *        - num : 현재 자리수의 숫자 ( 0 or 1 )
 *        - remainderOfNum : 현재까지 선택한 숫자들로 만들어진 수를 n으로 나눈 나머지 ([ex] (101)%n )
 *        - level : BFS의 그래프에서의 높이
 *        - previousIndex : BFS 그래프 상에서 부모 노드가 위치할 Index 
 *          -> 이 값을 통해 루트 노드까지 찾아가서 조건에 해당되는 숫자를 출력할 수 있다. 
 * 
 */

public class Boj8111 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            boolean[] check = new boolean[n];

            // 문제 조건 만족하는 숫자 or 만족하는 숫자가 없는 경우 "BRAK" 반환
            String answer = bfs(n, check); 

            System.out.println(answer);
        }

    }

    // 각 자릿수 상태 class
    private static class DigitState{
        private int num;
        private int remainderOfNum;
        private int level;
        private int previousIndex;
        public DigitState(int num, int remainderOfNum, int level, int previousIndex){
            this.num = num;
            this.remainderOfNum = remainderOfNum;
            this.level = level;
            this.previousIndex = previousIndex;
        }
        public int getNum(){
            return this.num;
        }
        public int getRemainderOfNum(){
            return this.remainderOfNum;
        }
        public int getLevel(){
            return this.level;
        }
        public int getPreviousIndex(){
            return this.previousIndex;
        }
    }

    private static String bfs(int n, boolean[] check){
        String errorMsg = "BRAK";
        ArrayList<DigitState> ret = new ArrayList<>();
        int startNum = 1;
        int startRemainder = 1%n;
        int currentLevel = 1;
        Queue<DigitState> q = new LinkedList<>();
        boolean existed = false;

        q.offer(new DigitState(startNum, startRemainder, 1,-1));

        while(currentLevel<=100 && !q.isEmpty() && !isAllTrue(check)){
            DigitState nowDigitState = q.poll();
            int nowNum = nowDigitState.getNum();
            int nowRemainderOfNum = nowDigitState.getRemainderOfNum();
            int nowLevel = nowDigitState.getLevel();
            int nowIndex = ret.size();

            if(nowLevel > currentLevel) {
                currentLevel = nowLevel;
            }

            if(check[nowRemainderOfNum] == false){
                if(nowRemainderOfNum%n == 0){
                    ret.add(nowDigitState);
                    existed = true;
                    break;
                } else {
                    ret.add(nowDigitState);
                    q.offer(new DigitState(0, ((nowRemainderOfNum*(10%n))%n + 0)%n, nowLevel+1, nowIndex));
                    q.offer(new DigitState(1, ((nowRemainderOfNum*(10%n))%n + 1)%n, nowLevel+1, nowIndex));
                }
                check[nowRemainderOfNum] = true;
            }

        }

        if(existed){
            StringBuilder sb = new StringBuilder();
            for(int i=ret.size()-1; i>=0; i=ret.get(i).getPreviousIndex()){
                sb.append(String.valueOf(ret.get(i).getNum()));
            }
            return sb.reverse().toString();
        }

        return errorMsg;
    }

    private static boolean isAllTrue(boolean[] check){
        boolean ret = true;
        for(int i=0; i<check.length; i++){
            if(check[i] == false){
                ret = false;
                break;
            }
        }
        return ret;
    }
}
