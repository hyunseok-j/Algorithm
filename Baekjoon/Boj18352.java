package Baekjoon;
import java.util.*;
import java.io.*;

// 2020.10.21

class Node{
    int index;
    int distance;

    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }

    public int getIndex(){
        return this.index;
    }
    public int getDistance(){
        return this.distance;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
}

public class Boj18352 {

    public static boolean[] visited;
    public static ArrayList<Integer> bfs(ArrayList<ArrayList<Node>> graph, int start, boolean[] visited , int k){
        ArrayList<Integer> result = new ArrayList<>();

        Queue<Node> q = new LinkedList<Node>();
        q.offer(new Node(start,0));
        visited[start] = true;

        while(!q.isEmpty()){
            Node now = q.poll();
            int nowIndex = now.getIndex();
            int nowDistance = now.getDistance();
            for(Node child : graph.get(nowIndex)){
                if(!visited[child.getIndex()]){
                    visited[child.getIndex()] = true;
                    int totalDistanceFromX = nowDistance + 1;
                    child.setDistance(totalDistanceFromX);
                    if(totalDistanceFromX == k){
                        result.add(child.getIndex());
                    }
                    q.offer(child);
                }
            }
        }

        return result;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);
        int x = Integer.parseInt(input[3]);
        

        visited = new boolean[n+1];
        for(int i=0; i<visited.length ; i++){
            visited[i] = false;
        }

        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<Node>());
        }

        for(int i=0; i<m; i++){
            String[] inputEdge = br.readLine().split(" ");
            int v1Num = Integer.parseInt(inputEdge[0]);
            int v2Num = Integer.parseInt(inputEdge[1]);
            graph.get(v1Num).add(new Node(v2Num,0));
        }

        ArrayList<Integer> ansArr = bfs(graph,x,visited,k);

        if(ansArr.size()==0){
            System.out.print(-1);
        }
        else{
            Collections.sort(ansArr);
            for(int i : ansArr){
                System.out.println(i);
            }
        }

    }    
}
