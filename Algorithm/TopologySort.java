package Algorithm;
import java.util.*;

/**
 * 위상 정렬 : 방향 그래프의 모든 노드를 방향성에 거스르지 않도록 순서대로 나열하는 것
 * 
 * 진입 차수(indegree)
 * : 특정 노드로 들어오는 간선 개수 
 * 
 * [순서]
 * 1. 진입 차수가 0인 노드를 큐에 넣는다.
 * 2. 큐가 빌 때가지 아래 과정 반복
 *   2-1. 큐에서 원소를 꺼내 해당 노드에서 출발하는 간선을 그래프에서 제거
 *   2-2. 새롭게 진입차수가 0이 된 노드를 큐에 넣는다. 
 * (Cycle이 있는 경우, 전체 다 확인하기 전에 큐가 비어버림)
 * 
 * 시간 복잡도 : O(V+E)
 */

public class TopologySort {

    // 정점 수
    private static int vertexOfSize = 100;

    // 간선 수
    private static int edgeOfSize = 100;

    // 진입 차수
    private static int[] indegree = new int[vertexOfSize+1];




    public static ArrayList<Integer> topologySort(ArrayList<ArrayList<Integer>> graph){

        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();


        // 진입 차수가 0인 노드 큐에 삽입
        for(int i=1; i<= vertexOfSize; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }

        // 큐가 빌 때까지
        while(!q.isEmpty()){
            int now = q.poll();
            result.add(now);

            for(int i: graph.get(now)){
                indegree[i] -= 1;
                if(indegree[i] == 0){
                    q.offer(i);
                }
            }
        }


        return result;
    }







    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> result;

        // 그래프 초기화 : 인접 리스트
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        for(int i=0; i<vertexOfSize+1; i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int i=0; i<edgeOfSize; i++){
            int index1 = sc.nextInt();
            int index2 = sc.nextInt();

            graph.get(index1).add(index2);

            // 진입차수 계산
            indegree[index1] += 1;
        }

        // 위상 정렬 실행
        result = topologySort(graph);

    }


    
}
