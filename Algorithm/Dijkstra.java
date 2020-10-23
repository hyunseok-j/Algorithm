package Algorithm;

import java.util.*;

/**
 * 다익스트라 알고리즘 (최단 경로)
 *  : 특정 노드에서 출발해 다른 노드로 가는 각각의 최단 경로를 구해주는 알고리즘
 * 
 * [순서]
 * 1. 출발 노드를 설정
 * 2. 최단 거리 테이블을 초기화
 * 3. 방문하지 않은 노드 중에서 최단 거리가 가장 짧은 노드를 선택
 * 4. 해당 노드를 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 테이블 갱신
 * 5. 3,4를 반복
 * 
 * [시간 복잡도]
 *  1. Simple : O(V^2)
 *  2. Use PriorityQueue : O(ElogV)
 */
class VertexD implements Comparable<VertexD>{
    int index;
    int distance;

    public VertexD(int index, int distance){
        this.index = index;
        this.distance = distance;
    }

    public int getIndex(){
        return this.index;
    }
    public int getDistance(){
        return this.distance;
    }
    @Override
    public int compareTo(VertexD other){
        return this.distance - other.distance;
    }
}

public class Dijkstra {

    public static final int INF = (int)1e9;

    public static int n; // 정점 vertex 수

    public static int[] distance = new int[10001]; 
    public static boolean[] visited = new boolean[10001];


    // 방문하지 않은 노드 중에서, 가장 최단 거리가 짧은 노드의 번호를 반환
    public static int getSmallestNode(){
        int minValue = INF;
        int index = 0; // 가장 최단 거리가 짧은 노드

        for(int i=1; i<n+1; i++){
            if(distance[i]<minValue && !visited[i]){
                index = i;
                minValue = distance[i];
            }
        }
        return index;
        
    }


    public static void simpleDijkstra(int start, ArrayList<ArrayList<VertexD>> graph){
        // 시작 노드에 대해서 초기화
        distance[start] = 0;
        visited[start] = true;

        for(VertexD v: graph.get(start)){
            distance[v.getIndex()] = v.getDistance();
        }

        // 시작 노드를 제외한 전체 n-1 개의 노드에 대해 반복
        for(int i=0; i<n-1; i++){
            // 현재 최단 거리가 가장 짧은 노드를 꺼내서, 방문 처리
            int now = getSmallestNode();
            visited[now] = true;
            // 현재 노드와 연결된 다른 노드를 확인
            for(VertexD v: graph.get(now)){
                int cost = distance[now] + v.getDistance();
                // 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
                if(cost < distance[v.getIndex()]){
                    distance[v.getIndex()] = cost;
                }
            }

        }
    }

    public static void priorityQueueDijkstra(int start, ArrayList<ArrayList<VertexD>> graph){
        PriorityQueue<VertexD> q = new PriorityQueue<VertexD>();

        // 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
        distance[start] = 0;
        q.offer(new VertexD(start,0));

        while(!q.isEmpty()){ // 큐가 비어있지 않다면
            // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
            VertexD now = q.poll();
            int nowIndex = now.getIndex();
            int nowDistance = now.getDistance();

            // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
            if(distance[nowIndex] < nowDistance){
                continue;
            }

            // 현재 노드와 연결된 다른 인접한 노드들을 확인
            for(VertexD v : graph.get(nowIndex)){
                int cost = distance[nowIndex] + v.getDistance();
                
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if(cost < distance[v.getIndex()]){
                    distance[v.getIndex()] = cost;
                    q.offer(new VertexD(v.getIndex(),cost));
                }  
            }

        }

    }

    public static void main(String[] args){
        n = 10000;
        Arrays.fill(distance,INF);
        Arrays.fill(visited,false);
    }
}
