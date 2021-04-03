package Baekjoon.BFS_DFS;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Title : ABCDE
 * desc : 
 *  알고리즘 캠프에 N명이 참가하고 있다. 사람들은 0번부터 N-1번까지 번호가 매겨져 있고 일부 사람들은 친구이다.
 *  이럴 때 아래의 친구 관계를 가지는 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
 *     - A는 B와 친구이다.
 *     - B는 C와 친구이다.
 *     - C는 D와 친구이다.
 *     - D는 E와 친구이다.
 *  캠프 참가 인원 수 N명과 친구 관계에 대한 정보 M개가 주어질 때, 
 *  위의 관계를 가지는 경우가 존재하면 "1"을 출력하고  그런 경우가 존재하지 않으면 "0"을 출력하시오.
 * 
 * Type : 그래프 탐색 DFS
 *  1. 사람을 Vertex, 친구 관계를 Edge라고 가정하고 Graph로 만든다. 
 *  2. 만든 Graph를 DFS로 탐색하는데 
 *     시작 Vertex 부터 깊이가 5인 Vertex 까지 도달하면 문제에서 말한 ABCDE 관계를 가지는 경우이므로 
 *     1을 출력하게 한다. 
 *  3. 탐색이 종료되도 조건을 만족하는 경우가 없으면 0을 출력하게 한다.
 */


public class Boj13023 {

    static boolean isExistedABCDERelationship(boolean[] visitedVertex, ArrayList<ArrayList<Integer>> list,
            int depth, int n, int vertexIndex){

        /* 종료 조건 : 깊이 5까지 Vertex 가 존재하면 true 반환 */
        if(depth == 5) return true;

        boolean ok = false;

        
        /* vertexIndex번째 Vertex와 Edge로 연결된 Vertex들을 탐색  */
        /* list.get(vertexIndex).get(i) : vertexIndex번째 Vertex와 Edge로 연결된 i번째 Vertex */

        for(int i=0; i<list.get(vertexIndex).size(); i++){
            
            if(visitedVertex[list.get(vertexIndex).get(i)] == true) continue; // 앞서 방문한 Vertex는 중복 방문 불가

            visitedVertex[list.get(vertexIndex).get(i)] = true;

            ok = isExistedABCDERelationship(visitedVertex, list, depth + 1, n, list.get(vertexIndex).get(i)); // 한층 아래 Vertex를 탐색 : depth + 1

            visitedVertex[list.get(vertexIndex).get(i)] = false;

            /* 깊이 5까지 도달한 경우가 발견되면 이후 탐색을 굳이 할 필요없으므로 true 반환하며 함수 종료 */
            if(ok == true){
                return ok;
            }
        }

        /* 깊이 5까지 도달하지 못한 경우 false 반환 */
        return ok;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();


        boolean[] visitedVertex = new boolean[n];

        /* 그래프를 인접리스트로 표현 */
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            list.add(new ArrayList<Integer>());
        }

        for(int i=0; i<m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            list.get(a).add(b);
            list.get(b).add(a);
        }

       /* 시작 정점 vertex 정해서 연결된 정점 하나씩 간선을 타고 dfs로 확인 */
        int answer = 0;
        for(int i=0; i<n; i++){
            boolean existed = false;

            visitedVertex[i] = true;

            existed = isExistedABCDERelationship(visitedVertex, list, 1, n, i);

            visitedVertex[i] = false;

            /* 문제의 ABCDE 조건을 만족하는 vertex들이 있으면 1 출력 */
            if ( existed ) {
                answer = 1;
                break;
            }
        }
        System.out.print(answer);
    }
}
