package Programmers.Level3;
import java.util.*;
/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/72413
 * 최단 경로 알고리즘 (우선순위 큐를 사용한 다익스트라) -> 다른 해법 (플로이드 워샬)
 */


public class Proj72413 {
    
    private final int INF = 987654321;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        int[][] graph; // graph[i][j] = i에서 j로 이어진 간선의 비용
        int[][] minCost = new int[3][n+1]; // minCost[i][j] = i에서 j로 이동하는데 필요한 최저 택시 비용
        
        graph = getInitGraph(n, fares);
        
        minCost[0] = getMinCost(graph, s, n);
        minCost[1] = getMinCost(graph, a, n);
        minCost[2] = getMinCost(graph, b, n);
        
        // 합승하는 구간 최저 비용 + a 혼자 가는 구간 최저 비용 + b 혼자 가는 구간 최저 비용
        for(int x = 1; x<=n; x++){
            if(minCost[0][x] == INF || minCost[1][x] == INF || minCost[2][x] == INF) continue;
            int candidate = minCost[0][x] + minCost[1][x] + minCost[2][x];
            if(answer > candidate) answer = candidate;
        }
        
        return answer;
    }
    
    public int[][] getInitGraph(int n, int[][] fares) {
        int[][] ret = new int[n+1][n+1];
        
        // 매우 큰 값으로 연결되지 않은 곳 표기
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                ret[i][j] = INF;
            }
        }
        
        // 입력받은 지점 간 예상 택시 비용 표기
        for(int i=0; i<fares.length; i++){
            int v1 = fares[i][0];
            int v2 = fares[i][1];
            int cost = fares[i][2];
            
            ret[v1][v2] = cost;
            ret[v2][v1] = cost;         
        }
        
        return ret;
    }
    
    public int[] getMinCost(int[][] graph, int start, int n){
        int[] ret = new int[n+1];

        Arrays.fill(ret, INF);
        ret[start] = 0;
        
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(new Vertex(start, 0));
        
        while(!pq.isEmpty()){
            Vertex here = pq.poll();
            int hereIndex = here.getIndex();
            int hereDistance = here.getDistance();
            
            if(ret[hereIndex] < hereDistance) continue;
            
            for(int there = 1; there <= n; there++){
                if(graph[hereIndex][there] == INF) continue;
                int dist = hereDistance + graph[hereIndex][there];
                if(ret[there] > dist) {
                    ret[there] = dist;
                    pq.offer(new Vertex(there, dist));
                }
            }
        }
        
        return ret;
    }
    
    public class Vertex implements Comparable<Vertex>{
        private int index;
        private int distance;
        public Vertex(int index, int distance){
            this.index = index;
            this.distance = distance;
        }
        public int getIndex(){ return this.index;}
        public int getDistance(){ return this.distance;}
        
        public int compareTo(Vertex other){
            return this.distance - other.distance;
        }
    }
}
