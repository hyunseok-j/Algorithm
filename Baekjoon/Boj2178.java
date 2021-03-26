package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;

/**
 * Title : 미로 탐색
 * desc : 
 *  n*m 미로에 대한 정보가 1과 0으로 주어지고 (1,1)을 시작점으로 (n,m)까지 도달하는 최단 거리를 계산하시오.
 *  (0 : 통과 불가능 칸, 1 : 통과 가능한 칸)
 * 
 * Type : BFS
 * 
 * 현재 풀이는 Graph의 탐색 시작 Node(1,1)를 높이1로 잡고 
 * BFS에서 다음 높이에 있는 Node의 개수를 nextDepthVertexCount로 세면서 
 * 높이가 증가하는 것에 초점을 맞춰서 minDepth 계산을 구현했다.
 * 
 * 다른 방법으로 각 (x, y) 위치에 초점을 맞추면, (1, 1)부터 (x, y)까지 도달하는 최소 거리를 dist[x][y]로 정의하고
 * BFS는 가장 짧은 경로로 한 번 Node를 거치므로 
 *  dist[nextX][nextY] = dist[x][y] + 1
 * 의 형태로 최소값 계산이 가능하다.
 */

public class Boj2178{

    // Map의 위치 Class
    static class Location{
        private int x;
        private int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }
    }

    // 인접 칸 좌표 계산을 위한 배열
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};


    // findMinCountBfs : BFS로 Graph인 map을 탐색하며 (startX, startY)에서 (endX, endY)에 도달하는데 필요한 최소 이동 거리 계산
    static int findMinCountBfs(int startX, int startY, int endX, int endY, boolean[][] check, int n, int m, int[][] map){
        Queue<Location> q = new LinkedList<>();
        int minDepth = 1;

        q.offer(new Location(startX, startY));
        check[startX][startY] = true;

        int thisDepthVertexCount = 1;
        int nextDepthVertexCount = 0;

        while(!q.isEmpty()){
            /* 현재 확인할 칸의 x, y 좌표 */
            Location location = q.poll();
            int x = location.getX();
            int y = location.getY();

            /* (x, y)의 4방향으로 인접한 칸 중 이동 가능 칸이 있으면 queue에 추가 */
            for(int i=0; i<4; i++){
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if(1 <= nextX && nextX <= n && 1<= nextY && nextY <= m){
                    if(check[nextX][nextY] == false && map[nextX][nextY] == 1){
                        if(nextX == endX && nextY == endY){
                            return minDepth + 1;
                        }
                        check[nextX][nextY]  = true;
                        q.offer(new Location(nextX, nextY));
                        nextDepthVertexCount += 1;
                    }
                }
            }

            /* 현재 층에 확인할 Node 개수 Count */
            thisDepthVertexCount -= 1;

            /* 현재 층의 모든 Node 탐색이 종료 */
            if(thisDepthVertexCount == 0){

                /* 다음 층 탐색 전 처리 */
                thisDepthVertexCount = nextDepthVertexCount; // 다음에 확인할 층 Node 개수로 값 갱신
                nextDepthVertexCount = 0;        // 다시 다음 층에서 확인할 Node
                minDepth += 1;                   // (1, 1)에서의 Depth 계산
            }

        }

        return minDepth;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        int startX = 1;
        int startY = 1;
        int endX = n;
        int endY = m;

        int[][] map = new int[n+1][m+1];
        boolean[][] check = new boolean[n+1][m+1];

        /* 미로 통과 가능 여부에 대한 입력 데이터 처리 */
        for(int i=1; i<=n; i++){
            char[] row = br.readLine().toCharArray();
            for(int j=1; j<=m; j++){
                map[i][j] = (int)(row[j-1]-'0');
            }
        }

        System.out.print(findMinCountBfs(startX, startY, endX, endY, check, n, m, map));

    }

}


 