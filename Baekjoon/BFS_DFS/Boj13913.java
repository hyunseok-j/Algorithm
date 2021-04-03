package Baekjoon.BFS_DFS;

import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Title : 숨바꼭질
 * desc : 
 *  A와 B가 숨바꼭질을 하고 있다. B는 처음 숨어있는 위치 m에 위치변경없이 있는다.
 *  A는 처음 자신의 위치 n에서 걸어서 1초 후, n-1 || n+1 위치로 이동하거나
 *  1초 후, 2*n의 위치로 이동가능하다.
 *  이 때, A가 가장 빠른 시간에 B를 찾는 시간을 구하시오.
 * 
 * Type : BFS
 */

class Boj13913{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] check = new boolean[100001];    // 각 위치의 방문 여부
        int[] minTimeToN = new int[100001];       // n에서 위치 index를 방문하기까지 걸리는 최단 시간
        int[] previousLocation = new int[100001]; // 위치 index를 방문했을 때 index 방문하기 이전에 방문한 위치
        
        // A가 B를 찾는데 걸린 최단 시간 출력
        int answer = calculateMinSeconds(n, m, check, minTimeToN, previousLocation);
        System.out.println(answer);

        // A가 B를 찾는데 걸린 시간이 최단일 때의 A의 이동 경로 출력
        printPath(m, previousLocation);
        
    }
    
    /**
     * calculateMinSeconds : n에서 m에 도달하기 위해 걸리는 최단 시간 계산, 방문한 경로 저장
     * @return n에서 m에 도달하기 위해 걸린 최단 시간
     */
    public static int calculateMinSeconds(int n, int m, boolean[] check, int[] minTimeToN, int[] previousLocation){
        Queue<Integer> q = new LinkedList<>(); // BFS를 위한 Queue
        
        /* BFS 초기값 설정 */
        q.offer(n);
        check[n] = true; 
        minTimeToN[n] = 0;
        previousLocation[n] = -1;
        
        /* BFS : 위치 n에서 이동 가능한 n-1, n+1, 2*n을 방문해보며 이동한 위치가 m이 될 때까지 탐색 */
        while ( !q.isEmpty() ) {
            int now = q.poll();
            int[] nx = {now - 1, now + 1, 2 * now};
            
            for(int i = 0; i < nx.length; i++) {
                if(0<= nx[i] && nx[i] <= 100000) {
                    if(check[nx[i]] == false) {
                        check[nx[i]] = true;
                        q.offer(nx[i]);
                        minTimeToN[nx[i]] = minTimeToN[now] + 1;
                        previousLocation[nx[i]] = now;
                        if(nx[i] == m){
                            return minTimeToN[nx[i]];
                        }
                    }
                }
            }
        }
        
        return 0;
    }
 
    /**
     * printPath : 목표 지점 m부터 역으로 방문한 위치를 추적해서 이동 경로를 출력
     */
    public static void printPath(int m, int[] previousLocation){
        List<Integer> path = new ArrayList<>();

        path.add(m);
        for(int i=m; previousLocation[i] > -1; i=previousLocation[i]) {
            path.add(previousLocation[i]);
        }
        
        for(int i=path.size()-1; i>=0; i--){
            System.out.print(path.get(i)+" ");
        }
        
    }
}