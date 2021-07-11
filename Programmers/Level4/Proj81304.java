package Programmers.Level4;
import java.util.*;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/81304
 * Title : [2021 카카오 인턴] 미로 탈출
 *  다익스트라 알고리즘 응용
 *  [수정 필요] 테스트 3, 5 실패 / 12, 15, 19, 21, 22, 23, 24, 25, 27 시간 초과
 */

public class Proj81304 {
    
    private static final int INF = 987654321;
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        int[][] graph = new int[n+1][n+1];
        
        for(int i=0; i<graph.length; i++){
            Arrays.fill(graph[i], INF);    
        }
        
        for(int[] road : roads){
            int from = road[0];
            int to = road[1];
            int time = road[2];
            if(graph[from][to] > time) graph[from][to] = time; // ?
        }
        
        answer = getShortestTime(graph, n, start, end, traps);
        
        return answer;
    }

    public int getShortestTime(int[][] graph, int n, int start, int end, int[] traps) {
        int ret = INF;
        
        int nGraph = (int)Math.pow(2, traps.length); // 각 trap을 방문한 횟수에 의해 나오는 그래프 상태 수
        int[][] time = new int[n+1][nGraph];
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        for(int i=0; i<traps.length; i++){
            hm.put(traps[i], i);
        }
        
        for(int i=0; i<n+1; i++){
            Arrays.fill(time[i], INF);   
        }
        
        PriorityQueue<PathInfo> pq = new PriorityQueue<>();
        pq.offer(new PathInfo(start, 0, 0));
        Arrays.fill(time[start], 0);
        
        while(!pq.isEmpty()){
            PathInfo now = pq.poll();
            int here = now.num;
            
            if(time[here][now.graphStateNum] < now.time) continue;
            
            for(int there = 1; there < n+1; there++){
                int result = -1;
                
                if (hm.get(here) != null && hm.get(there) != null) {
                    result = ((now.graphStateNum >> hm.get(here)) & 1) ^ ((now.graphStateNum >> hm.get(there)) & 1);
                } else if(hm.get(here) != null) {
                    result = (now.graphStateNum >> hm.get(here)) & 1;
                } else if(hm.get(there) != null) {
                    result = (now.graphStateNum >> hm.get(there)) & 1;
                } else {
                    result = 0;
                }
                
                int dt = (result == 0)? graph[here][there] : graph[there][here];
                if(dt >= 1 && dt != INF){
                    int thereTime = now.time + dt;
                    if(time[there][now.graphStateNum] > thereTime){
                        
                        if(hm.get(there) != null){
                            // there이 트랩이면 -> 10011.. 이진수 형태로 된 수에서 트랩에 해당되는 부분만 1과 XOR 연산
                            pq.offer(new PathInfo(there, thereTime, now.graphStateNum ^ (1 << hm.get(there) )));
                        } else {
                            // there이 트랩이 아니면 -> 그래프 유지
                            pq.offer(new PathInfo(there, thereTime, now.graphStateNum));    
                        }
                        
                        time[there][now.graphStateNum] = thereTime;
                    }
                }
            }
            
        }
        
        for(int graphStateNum = 0; graphStateNum < nGraph; graphStateNum++){
            ret = Math.min(ret, time[end][graphStateNum]);
        }
        
        return ret;
    }
    
    public class PathInfo implements Comparable<PathInfo> {
        int num;
        int time;
        int graphStateNum;
        public PathInfo(int num, int time, int graphStateNum) {
            this.num = num;
            this.time = time;
            this.graphStateNum = graphStateNum;
        }
        public int compareTo(PathInfo other){
            return this.time - other.time;
        }
    }


}