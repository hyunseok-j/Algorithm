package Programmers.Level3;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Title : 네트워크
 * URI : https://programmers.co.kr/learn/courses/30/lessons/43162
 * 
 * Type : DFS/BFS
 */

public class Proj43162 {

    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for(int i=0; i<n; i++){
            if(visited[i] == false){
                bfs(computers, i, visited, n);
                answer++;   
            }                
        }
        
        return answer;
    }
    
    public void bfs(int[][] computers, int rootIndex, boolean[] visited,int n){
        Queue<Integer> queue  = new LinkedList<>();
        queue.offer(rootIndex);
        visited[rootIndex] = true;
        
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int i=0; i<n; i++){
                if(now!=i){
                    if(visited[i] == false && computers[now][i]==1){
                        queue.offer(i);
                        visited[i] = true;
                    }
                }
            }
        }
    }
}
