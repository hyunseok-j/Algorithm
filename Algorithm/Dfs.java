package Algorithm;
import java.util.*;

/*
 * DFS : 특정한 경로를 탐색하다가 특정한 상황(조건)에서 최대한 깊숙이 들어가서 노드 방문 후, 다시 돌아가 다른 경로 탐색하는 알고리즘
 * 
 * [순서]
 * 1. 탐색 시작 노드를 스택에 삽입, 방문 처리
 * 2. 스택의 최상단 노드에 방문하지 않은 인접 노드가 있으면 
 *    그 인접 노드를 스택에 넣고 방문 처리
 * 3. 2.과정을 수행할 수 없을 때까지 반복
 * 
 */ 


public class Dfs {

    public static void dfs(int[][] graph, int vertex, boolean[] visited){
        // 현재 노드 방문 처리
        visited[vertex] = true;

        // 현재 방문한 노드 출력
        System.out.print(String.valueOf(vertex) + " ");

        // 현재 방문한 노드와 인접한 노드 중 방문하지 않은 노드 탐색
        for(int i : graph[vertex]){
            if(!visited[i] && i==0){
                dfs(graph, i, visited);

            }

        }
    }
    
}