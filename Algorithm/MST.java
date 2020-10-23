package Algorithm;
import java.util.*;
/**
 * 최소 신장 트리(MST) : 크루스칼 알고리즘
 * 
 * 신장 트리
 * : 한 개의 graph 내에서 모든 노드 포함하면서 사이클이 존재하지 않는 부분 그래프
 * : 모든 지점을 연결
 * 
 * "최소 신장 트리 알고리즘" (크루스칼)
 * [순서]
 * 1. 간선 비용에 따라 오름차순 정렬
 * 2. 간선 하나씩 확인하며 간선이 cycle 발생시키는지 확인
 *      2-1. 사이클 x -> 최소 신장 트리에 포함 
 *      2-2. 사이클 o -> 최소 신장 트리에 X
 * 3. 모든 간선에 대해 2과정 반복
 * 
 * 
 * 시간 복잡도 : O(ElogE)
 */

class EdgeM implements Comparable<EdgeM>{
    int index1;
    int index2;
    int cost;

    public EdgeM(int index1, int index2, int cost){
        this.index1 = index1;
        this.index2 = index2;
        this.cost = cost;
    }
    
    public int getIndex1(){
        return this.index1;
    }

    public int getIndex2(){
        return this.index2;
    }

    public int getCost(){
        return this.cost;
    }

    @Override
    public int compareTo(EdgeM other){
        return this.cost - other.cost;
    }


}


public class MST {
    
    
    // 최소 신장 트리의 최소 비용 계산
    public static int calCostMst(ArrayList<EdgeM> edges, int[] parent){
        int result = 0;

        // 간선 비용 오름차순으로 정렬
        Collections.sort(edges);

        for(EdgeM e: edges){
            int index1 = e.getIndex1();
            int index2 = e.getIndex2();
            int cost = e.getCost();

            // 사이클이 존재하지 않으면 
            if(find(index1,parent)!=find(index2,parent)){
                union(index1,index2,parent);
                result += cost;
            }
        }
        return result;
    }

    // 특정 원소가 속한 집합 구하기
    public static int find(int index, int[] parent){
        if(parent[index]!=index){
            parent[index] = find(parent[index],parent);
        }
        return parent[index];
    }

    // 두 원소가 속한 집합 합치기 
    public static void union(int index1, int index2, int[] parent){
        int rootIndex1  = find(index1, parent);
        int rootIndex2 = find(index2, parent);

        if( rootIndex1 < rootIndex2 ){
            parent[rootIndex2] = rootIndex1;
        }
        else{
            parent[rootIndex1] = rootIndex2;
        }
    }


}
