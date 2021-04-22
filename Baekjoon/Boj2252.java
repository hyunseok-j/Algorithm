package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Title : 줄 세우기
 * desc : 
 *  n명의 학생이 있고 키의 오름차순으로 줄을 세워야 한다. 이 때, 일부 학생들간의 키 비교 결과를 토대로 세울 수 있는 줄 결과들 중
 *  비교 결과에 맞는 결과 아무거나 하나를 출력하시오.
 * 
 * Type : 위상 정렬, DAG(Directed Acyclic Graph)
 */

public class Boj2252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");

        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        int[] inDegree = new int[n];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i=0; i<n; i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int i=0; i<m; i++){
            String[] row = br.readLine().split(" ");
            int front = Integer.parseInt(row[0])-1;
            int back = Integer.parseInt(row[1])-1;
            graph.get(front).add(back); 
            inDegree[back] += 1;
        }

        ArrayList<Integer> zeroIndex = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(inDegree[i] == 0){
                zeroIndex.add(i);
            }
        }

        
        String answer = bfs(inDegree, graph, zeroIndex);
        System.out.println(answer);
        
    }

    private static String bfs(int[] inDegree, ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> zeroIndex) {

        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<zeroIndex.size(); i++){
            q.offer(zeroIndex.get(i));
        }

        while(!q.isEmpty()){
            int now = q.poll();
            sb.append(now+1 + " ");
            for(int next : graph.get(now)){
                inDegree[next] -= 1;
                if(inDegree[next] == 0){
                    q.offer(next);
                }
            }
        }
        return sb.toString();
    }
}
