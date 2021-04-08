package Baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Title : 컨베이어 벨트 위의 로봇
 * desc : 
 *  n이 주어질 때 컨베이어 벨트는 길이가 2*n인 벨트가 위아래로 둘러싸고 있으며 위 아래로 1 ~ n, n+1 ~ 2n 의 칸을 가진다.
 *  이 때 1은 올라가는 위치, n은 내려가는 위치라고 지칭한다. 각 칸은 내구도를 가지고 있으며 컨베이어 벨트가 회전할 때 같이 회전한다. 
 *  내구도는 로봇이 스스로 올라가거나 이동할 때 1씩 감소한다.
 * 
 *  컨테이너 벨트에 로봇을 하나씩 올리려고 한 단계마다 아래의 과정을 수행한다. 
 *   1) 벨트를 회전시킨다.
 *   2) 가장 먼저 벨트 위에 올라간 로봇부터 벨트가 회전하는 방향으로 이동가능하면 이동한다. 
 *       - 내구도가 1이상이고 칸에 로봇이 없는 칸인 경우 이동이 가능하다.
 *   3) 올라가는 위치에 로봇이 없으면 로봇을 올리고 1번으로 돌아간다. 
 *   4) 내구도가 0인 칸이 k개 이상이면 작업을 종료한다.
 * 
 *   * 내려가는 위치에 로봇이 도달하면 땅으로 내려가야 한다. 
 * 
 *   종료되는 시점의 단계가 몇단계인지 출력하시오.
 * 
 * Type : 시뮬레이션
 */

public class Boj20055 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[2*n];

        for(int i=0; i<a.length; i++){
            a[i] = sc.nextInt();
        }

        Queue<Integer> robotPos = new LinkedList<>();
        int step = 0;
        while(true){
            // 0. 단계 증가
            step += 1;

            // 1. 한 칸 회전
            // 1.1 로봇 회전
            for(int i=robotPos.size()-1; i>=0; i--){
                int pos = robotPos.poll();
                robotPos.offer((pos+1)%(2*n));
            }
            // 1.2 칸 이동
            int lastVal = a[a.length-1];
            for(int i=a.length-1; i>0; i--){
                a[i] = a[i-1];
            }
            a[0] = lastVal;

            // 1.3 내려가는 칸에 로봇이 있으면 제거
            for(int i=robotPos.size()-1; i>=0; i--){
                int pos = robotPos.poll();
                if(pos!=n-1){
                    robotPos.offer(pos);
                }
            }

            // 2.1 회전으로 이동한 로봇 중 이동가능한 로봇은 앞으로 한 칸 이동, 내구도 감소
            for(int i=robotPos.size()-1; i>=0; i--){
                int pos = robotPos.poll();

                if(robotPos.contains((pos+1)%(2*n)) || a[(pos+1)%(2*n)]==0) {
                    robotPos.offer(pos);

                } else if ( !robotPos.contains((pos+1)%(2*n)) && a[(pos+1)%(2*n)] > 0) {
                    a[(pos+1)%(2*n)] -= 1;
                    robotPos.offer((pos+1)%(2*n));
                }
            }

            // 2.2 내려가는 칸에 로봇이 있으면 제거
            for(int i=robotPos.size()-1; i>=0; i--){
                int pos = robotPos.poll();
                if(pos!=n-1){
                    robotPos.offer(pos);
                }
            }


            // 3. 올라가는 위치에 로봇이 없으면 로봇을 올린다.
            if(!robotPos.contains(0) && a[0] > 0){
                robotPos.offer(0);
                a[0] -= 1;
            }

            // 4. 내구도 검사
            int count = 0;
            for(int i=0; i<a.length; i++){
                if(a[i] == 0){
                    count += 1;
                }
                if(count >= k) break;
            }   
            if(count >= k) break;
        }
        System.out.println(step);
        
    }
    
}
