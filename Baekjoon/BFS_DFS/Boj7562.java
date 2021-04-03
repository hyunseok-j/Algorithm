package Baekjoon.BFS_DFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Title : 나이트의 이동
 * desc : 
 *  체스판의 크기가 N*N이고 체스판의 한 위치에 나이트가 세워져 있을 때, 
 *  나이트가 현재 위치에서 입력 받은 곳 까지 가기 위한 최소 이동 횟수를 구하시오.
 *  나이트는 한 위치에서 (-2,-1), (-2,1), (-1,-2), (-1,2), (1,-2), (1,2), (2,-1), (2,1) 만큼 
 *  이동 가능하다. 
 * 
 * Type : BFS
 *  체스판 전체가 아닌 체스판 내에서 나이트가 이동할 수 있는 칸만 전부 확인하기 위해서 
 *  나이트가 다음으로 이동 가능한 8칸을 그래프에서 간선으로 이어진 경우라고 생각하고 
 *  BFS 알고리즘을 이용해 탐색을 진행하게 했다.
 * 
 *  또 최소 이동 횟수를 구해야 했는데 
 *  BFS는 알고리즘상 단계적으로 탐색하기 때문에 
 *  가장 먼저 문제에서 주어진 목표 위치에 도달하는 경우의 이동 횟수를 최소로 보장할 수 있고 
 *  이를 통해 정답을 구했다.
 */

public class Boj7562 {

    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2}; // 나이트가 현재 위치에서 세로로 이동가능한 위치
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1}; // 나이트가 현재 위치에서 가로로 이동가능한 위치

    // 체스판 내에서의 위치 Class
    static class Location{
        private int x; // 세로 좌표
        private int y; // 가로 좌표

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }

        /* Getter x, y*/
        public int getX(){ return this.x; }
        public int getY(){ return this.y; }
    }

    /**
     * calcualteMinCount : 나이트가 시작 위치에서 목표 위치까지 이동하는데 필요한 최소 이동 횟수 계산
     * @param start : 시작 위치
     * @param end : 목표 위치
     * @param mapLength : 체스판 크기 
     * @param count : 시작 위치에서 해당 위치까지 도달하기까지 필요한 최소 이동 횟수
     * @param visited : 해당 위치의 방문 여부
     * @return
     */
    static int calculateMinCount(Location start, Location end, int mapLength, int[][] count,
            boolean[][] visited){
        
        Queue<Location> q = new LinkedList<>();
        int startX = start.getX();
        int startY = start.getY();
        int minCount = 0;
        
        /* 시작 위치 처리 */
        visited[startX][startY] = true; // 방문 여부 기록
        q.offer(start);

        while(!q.isEmpty()){
            /* 탐색하려는 대상 위치에 대한 정보 처리 */
            Location now = q.poll();
            int x = now.getX();
            int y = now.getY();

            /* 탐색 대상 위치에서 다음 이동가능 위치들을 판단 후 처리 */
            for(int i=0; i<8; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0<= nx && nx < mapLength && 0<= ny && ny < mapLength){
                    if(visited[nx][ny] == false){
                        
                        visited[nx][ny] = true;          // 방문 여부 기록
                        count[nx][ny] = count[x][y] + 1; // 이동 횟수 기록
                        q.offer(new Location(nx, ny)); 
                        
                        /* 목표 위치 도달한 경우 */
                        if(nx == end.getX() && ny == end.getY()){

                            /* 현재 위치까지의 이동 횟수 return 변수에 저장 후 for문 탈출 */
                            minCount = count[nx][ny];
                            break;
                        }
                    }
                }
            }
        }

        return minCount;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0){
            int mapLength = sc.nextInt();
            int startX = sc.nextInt();
            int startY = sc.nextInt();
            int endX = sc.nextInt();
            int endY = sc.nextInt();
            int[][] count = new int[mapLength][mapLength];
            boolean[][] visited = new boolean[mapLength][mapLength];

            Location start = new Location(startX, startY);
            Location end = new Location(endX, endY);

            System.out.println(calculateMinCount(start, end, mapLength, count, visited));
        }
    }
}
