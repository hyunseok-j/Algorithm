package Baekjoon;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Title : 이모티콘
 * desc : 
 *  처음 화면에 1개의 이모티콘이 있고 클립보드를 통한 아래의 행위들을 통해 화면에 S개의 이모티콘을 출력하려고 한다.
 *  다음의 행위들은 1번 수행마다 1초씩 걸린다.
 *    1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
 *    2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
 *    3. 화면에 있는 이모티콘 중 하나를 삭제한다.
 *  이 때 화면에 S개의 이모티콘을 띄우는데 걸리는 최소 시간을 구하시오.
 * 
 *  Type : BFS 
 */

public class Boj14226{
    
    static class Node{
        private int x; // windowCnt
        private int y; // clipBoardCnt
        private int minTime;
        
        public Node(int x, int y, int minTime){
            this.x = x;
            this.y = y;
            this.minTime = minTime;
        }
        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }
        public void setMinTime(int minTime) { this.minTime = minTime; }
        public int getX() { return this.x; }
        public int getY() { return this.y; }
        public int getMinTime() { return this.minTime; }
    }
    
    static int bfs(Node startState, int wantedEmoCnt, boolean[][] check){
        Queue<Node> q = new LinkedList<>();
        q.offer(startState);
        check[startState.getX()][startState.getY()] = true;
        
        while(!q.isEmpty()){
            Node now = q.poll();
            int x = now.getX();
            int y = now.getY();
            int minTime = now.getMinTime();
            
            
            if(x == wantedEmoCnt){
                return minTime;
            }
            

            if(x > 0){
                if(check[x][x]==false){
                    check[x][x] = true;
                    q.offer(new Node(x, x, minTime+1));
                }
            }
            if(y > 0){
                if(x+y <= 2000){
                    if(check[x+y][y]==false){
                        check[x+y][y] = true;
                        q.offer(new Node(x+y, y, minTime+1));    
                    }
                }
            }
            if(x-1 >= 0){
                if(check[x-1][y]==false){
                    check[x-1][y] = true;
                    q.offer(new Node(x-1, y, minTime+1));
                }
            }
            
        }
        
        return -1;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        boolean[][] check = new boolean[2001][2001];
        
        int windowCnt = 1;
        int clipBoardCnt = 0;
        int firstMinTime = 0;
        
        int answer = bfs(new Node(windowCnt, clipBoardCnt, firstMinTime), s, check);
        System.out.print(answer);
    }
}