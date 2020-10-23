package Baekjoon.MST;

import java.util.*;
import java.io.*;

class Planet2887{
    private int index;
    private int x;
    private int y;
    private int z;

    public Planet2887(int index, int x, int y, int z){
        this.index = index;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getIndex(){
        return this.index;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getZ(){
        return this.z;
    }
}

class Edge2887{
    int index1;
    int index2;
    int cost;

    public Edge2887(int index1, int index2, int cost){
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

}

public class Boj2887 {

    private static int[] parent;
    private static int n;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Planet2887> planets = new ArrayList<>();
        ArrayList<Edge2887> edges = new ArrayList<>();
        int result = 0;
        n = Integer.parseInt(br.readLine());
        parent = new int[n+1];

        for(int i=0; i<n+1; i++){
            parent[i] = i;
        }

        for(int i=1; i<n+1; i++){
            String[] input = br.readLine().split(" ");
            int index = i;
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int z = Integer.parseInt(input[2]);

            planets.add(new Planet2887(index, x, y, z));
        }

        // x축 기준 오름차순
        Collections.sort(planets, new Comparator<Planet2887>(){
            @Override
            public int compare(Planet2887 p1, Planet2887 p2){
                return p1.getX() - p2.getX();
            }
        });

        for(int i=1; i<n; i++){
            Planet2887 p1 = planets.get(i);
            Planet2887 p2 = planets.get(i-1);

            edges.add(new Edge2887(p1.getIndex(),p2.getIndex(),calCost(1,p1,p2)));
        }

         // y축 기준 오름차순
         Collections.sort(planets, new Comparator<Planet2887>(){
            @Override
            public int compare(Planet2887 p1, Planet2887 p2){
                return p1.getY() - p2.getY();
            }
        });

        for(int i=1; i<n; i++){
            Planet2887 p1 = planets.get(i);
            Planet2887 p2 = planets.get(i-1);

            edges.add(new Edge2887(p1.getIndex(),p2.getIndex(),calCost(2,p1,p2)));
        }

         // z축 기준 오름차순
         Collections.sort(planets, new Comparator<Planet2887>(){
            @Override
            public int compare(Planet2887 p1, Planet2887 p2){
                return p1.getZ() - p2.getZ();
            }
        });

        for(int i=1; i<n; i++){
            Planet2887 p1 = planets.get(i);
            Planet2887 p2 = planets.get(i-1);

            edges.add(new Edge2887(p1.getIndex(),p2.getIndex(),calCost(3,p1,p2)));
        }

         // edges를 cost기준으로 정렬
         Collections.sort(edges, new Comparator<Edge2887>(){
            @Override
            public int compare(Edge2887 e1, Edge2887 e2){
                return e1.getCost() - e2.getCost();
            }
        });


        // 크루스칼 
        for(Edge2887 e : edges){
            if(find(parent,e.getIndex1()) != find(parent,e.getIndex2())){
                union(parent,e.getIndex1(),e.getIndex2());
                result += e.getCost();
            }
        }

        System.out.print(result);

    }


    public static int find(int[] parent, int x){
        if(parent[x]!=x){
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public static void union(int[] parent, int a, int b){
        int rootA = find(parent, a);
        int rootB = find(parent, b);

        if( rootA < rootB){
            parent[rootB] = rootA;
        }
        else{
            parent[rootA] = rootB;
        }
    }

    public static int calCost(int mode, Planet2887 p1, Planet2887 p2){
        int cost = 0;

        if(mode == 1){
            cost = Math.abs(p1.getX()-p2.getX());
        }
        else if(mode == 2){
            cost = Math.abs(p1.getY()-p2.getY());
        }
        else{
            cost = Math.abs(p1.getZ()-p2.getZ());
        }

        return cost;
    }
}
