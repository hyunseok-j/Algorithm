package Algorithm;
import java.util.*;

/*
 * BFS : 특정 경로가 주어지면 가까운 노드부터 탐색하는 알고리즘
 * 
 * [순서]
 * 1. 탐색 시작 노드를 큐에 삽입하고 방문 처리
 * 2. 큐에서 노드를 꺼내 해당 노드의 인접 노드 중에서 방문하지 않은 노드를
 *    모두 큐에 삽입하고 방문 처리
 * 3. 2.과정을 수행할 수 없을 때까지 반복
 * 
 */

public class Bfs {

    public static void bfs(int[][] graph, int start, boolean[] visited){

        Queue<Integer> queue = new LinkedList<Integer>();

        // 탐색 시작 노드 처리

        //  큐 삽입
        queue.offer(start);

        // 방문 처리
        visited[start] = true;

        // 큐가 빌 때까지 반복
        while(!queue.isEmpty()){
            // 확인하고자 하는 노드 
            int now = queue.poll();
            
            // 노드의 인접 노드 탐색
            for(int i : graph[now]){
                if(visited[i]!=true){
                    // 방문하지 않았으면 큐에 삽입하고 방문 처리
                    queue.offer(i);
                    visited[i] = true;
                }
            }

        }
        
    }

}
