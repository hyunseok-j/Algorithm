package Programmers.Level3;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Title : 단어 변환
 * URI : https://programmers.co.kr/learn/courses/30/lessons/43163
 * 
 * Type : BFS, 인접행렬
 */

public class Proj43163 {

class Solution {
    public int solution(String begin, String target, String[] words) {
        
        int n = words.length;
        boolean existTarget = false;
        boolean[] visited = new boolean[n+1];

        String[] beginPlusWords = new String[n+1];
        beginPlusWords[0] = begin;
        for(int i=1; i<n+1; i++){
            beginPlusWords[i] = words[i-1];
        }
        
        
        int[][] graph = new int[n+1][n+1];
        
        for(int k=0; k<n+1; k++){
            for(int i=0; i<n; i++){
                int count = 0;
                for(int j=0; j<beginPlusWords[k].length(); j++){
                    if(words[i].charAt(j) != beginPlusWords[k].charAt(j)){
                        count++;
                    }
                }

                if(target.equals(words[i])) existTarget = true;

                if(count == 1){
                    graph[k][i+1] = 1;
                }
            }
        }
        
        // target X : return 0
        if(existTarget == false) return 0;
        
        // target O
        return bfs(graph, 0, visited, beginPlusWords, target, n);
    }
    public int bfs(int[][] graph, int index, boolean[] visited, String[] beginPlusWords, String target, int n){
        Queue<Integer> q = new LinkedList<>();
        q.offer(index); q.offer(0);
        visited[index] = true;
        
        while(!q.isEmpty()){
            int nowIndex = q.poll();
            int nowLevel = q.poll();
            
            if(beginPlusWords[nowIndex].equals(target)){
                return nowLevel;
            }
            
            for(int i=1; i<n+1; i++){
                if(visited[i] == false && graph[nowIndex][i] == 1){
                    q.offer(i);
                    q.offer(nowLevel+1);
                }
            }
        }
        return 0;
    }
}
    
}
