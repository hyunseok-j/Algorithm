package Baekjoon.Floyd;
import java.util.*;
import java.io.*;



public class Boj11404 {

    public static final int INF = (int)1e9;
    public static int n;
    public static int m;
    public static int[][] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new int[n+1][n+1];

        // graph 초기화 : INF
        for(int i=0; i<n+1; i++){
            Arrays.fill(graph[i],INF);
        }

        // 자기 자신에서 자기 자신으로 가는 경로 비용 0으로 초기화
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(i==j){
                    graph[i][j] = 0;
                }
            }
        }

        // 그래프 간선 값 입력
        for(int i=0; i<m; i++){
            String[] input = br.readLine().split(" ");
            int index1 = Integer.parseInt(input[0]);
            int index2 = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);
            if( cost < graph[index1][index2]){
                graph[index1][index2] = cost;
            }
        }


        // 점화식 기반 플로이드 수행
        for(int k=1; k<n+1; k++){
            for(int a=1; a<n+1; a++){
                for(int b=1; b<n+1; b++){
                    graph[a][b] = Math.min(graph[a][b],graph[a][k]+graph[k][b]);
                }
            }
        }


        // 갈 수 없는 곳 비용 0 처리 및 출력
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(graph[i][j]==INF){
                    graph[i][j] = 0;
                }         
                sb.append(graph[i][j]+" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);

    }
    
}
