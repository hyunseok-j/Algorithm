package Algorithm;

/**
 * Union-Find
 * : 서로소 집합(Disjoint-set)을 표현할 때 사용하는 알고리즘
 * 
 * 1. Union
 *    2개의 원소가 포함된 집합을 하나의 집합으로 합치는 연산
 * 
 * 2. Find
 *    특정 원소가 속한 집합이 어떤 집합인지 찾아주는 연산
 *    특정 집합을 루트 노드가 상징함 
 * 
 * [순서]
 * Union
 * 1. find로 a 와 b의 루트 노드 a'와 b'를 찾는다
 * 2. 더 작은 루트 노드 값을 가지는 노드가 a'일 때 
 *    b'의 부모노드로 a'을 설정한다. 
 * 
 * 모든 Union 연산 처리까지 과정 반복
 * 
 * [용도]
 *  연결된 그래프가 몇 개인지 확인 가능
 *  무방향 그래프 내에서 사이클 판별
 *    i. 각 간선마다 연결된 정점들의 루트 노드가 같은지를 확인
 *      i-1. 같으면 cycle 발생
 *      i-2. 다르면 큰 노드 -> 작은 노드로 연결 (union)   
 * 
 *  방향 그래프의 cycle은 DFS를 통해 이미 방문한 곳에 또 방문하려고 하면 cycle로 판별 가능
 */

public class UnionFind {

    int v = 100;

    // parent[x]: x 노드의 부모 노드 
    int[] parent = new int[v+1]; 


    // find1 : 특정 원소가 속한 집합(루트 노드) 찾기
    public static int find(int x, int[] parent){

        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출 
        if(parent[x] != x){
            return find(parent[x], parent);
        }
        return x;
    }    

    // find2 : parent에 바로 루트 노드 값을 알 수 있게 경로 압축과정이 포함된 find
    public static int findPathCompression(int x, int[] parent){
        if(parent[x] != x){
            parent[x] = findPathCompression(parent[x],parent);
        }
        return parent[x];
    }

    // 두 원소가 속한 집합 합치기
    public static void union(int[] parent, int a, int b){
        int rootA = find(a, parent);
        int rootB = find(b, parent);

        if(rootA < rootB){
            parent[rootB] = rootA;
        }
        else{
            parent[rootA] = rootB;
        }
    }
}
