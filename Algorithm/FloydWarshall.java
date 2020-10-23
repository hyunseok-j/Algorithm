package Algorithm;
import java.util.*;
/**
 * 플로이드 워셜 알고리즘
 * : 모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구해야 하는 경우
 * : '단계마다 거쳐 가는 노드'를 기준으로 알고리즘 수행
 * 
 * D[a][b] : a에서 b로 가는 최단 거리 비용
 * 
 * 동적계획법 응용
 * D[a][b] = min(D[a][b], D[a][k] + D[k][b])
 * 
 * 시간 복잡도 : O(V^3)
 * 
 * 
 */

public class FloydWarshall {
    int v = 100;
    int[][] graph = new int[v+1][v+1];


    public static void floyd(int[][] graph){

        // 모든 간선 비용 INF 로 초기화
        for(int i=0; i<graph.length; i++){
            Arrays.fill(graph[i],(int)1e9);
        }

        // 자기 자신에서 자기 자신으로 가는 비용 0으로 초기화

        // 각 간선 정보 입력받아, 그 값으로 초기화 


        // 점화식에 따라 플로이드 워셜 알고리즘 수행
        for(int k=1; k<graph.length; k++){
            for(int a=1; a<graph.length; a++){
                for(int b=1; b<graph.length; b++){
                    graph[a][b] = Math.min(graph[a][b],graph[a][k]+graph[k][b]);
                }
            }
        }

    }
    
}
