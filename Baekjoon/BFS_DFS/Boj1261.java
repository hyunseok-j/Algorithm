package Baekjoon.BFS_DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Title : 알고스팟(미로)
 * desc :
 *  미로의 크기가 N*M 이며 1*1 크기의 방으로 이루어져 있다. 방은 빈 방 또는 벽으로 이루어져 있고
 *  빈 방은 자유롭게 다닐 수 있지만 벽은 부수지 않으면 이동할 수 없다. 
 *  현재 (1,1)에 있는 사람이 (N,M)으로 이동하려면 벽을 최소 몇 개 부수어야 하는지 구하는 프로그램을 작성하시오.
 */

public class Boj1261 {
    
    // 미로 내 한 칸을 기준으로 상하좌우 상대좌표
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    
    // 미로 내의 위치 Class
    static class Location{
        int x;
        int y;
        public Location(int x, int y){
            this.x = x; 
            this.y = y;
        }
    }
    
    // 미로의 각 방을 정점으로 생각해 (startX, startY)를 시작 정점으로 BFS 탐색
    // 일반적인 모든 간선이 1인 BFS 방식과 달리 
    // 이 문제는 간선이 0(빈 방 통과)과 1(벽을 부숴서 통과)이므로
    // Queue 하나만으로는 벽을 부순 횟수를 순차적으로 증가시키는게 불가(= BFS를 최단 비용 알고리즘으로 사용 불가)

    // Queue를 2개(nowQ, nextQ)를 써서 순차적으로 증가하는 형태로 변형시키면 기존 BFS 형태로 구현 가능
    // 벽 0개 허물어서 도착한 위치 Queue만 우선적으로 처리하며 벽 1개 허물어서 도착한 위치 Queue에 대기시킴
    // 이후 벽 0개에 대한 처리가 끝난 후, 큐에 모아놓은 벽 1개에 해당하는 것들 처리하며 벽 2개에 해당하는 것을 다음 Queue에 대기시킴

    // 위의 순서대로 처리하면서 (endX, endY)를 최초로 만난 시점의 뚫고 온 벽의 개수가 
    // 최소로 부숴야 하는 벽의 개수가 된다.

    static int bfs(int startX, int startY, int endX, int endY, int[][] maze, boolean[][] check, 
            int[][] brokenWallCount){
        Queue<Location> nowQ = new LinkedList<>();
        Queue<Location> nextQ = new LinkedList<>();
        
        int m = endX;
        int n = endY;
        
        // 출발 지점 (1,1)에 대한 설정
        check[startX][startY] = true;
        brokenWallCount[startX][startY] = 0;
        nowQ.offer(new Location(startX, startY));
        
        while(!nowQ.isEmpty()){
            Location now = nowQ.poll();
            
            for(int i=0; i<4; i++){
                /* 현재 위치의 상하좌우 좌표 확인 */
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                /* 상하좌우 좌표가 미로 범위 벗어나지 않고 */
                if(1<= nextX && nextX <= m && 1<= nextY && nextY <= n){
                    /* 아직 검사한 적이 없으면 검사 대상으로 추가*/
                    if(check[nextX][nextY] == false){
                        check[nextX][nextY] = true;
                        
                        if(maze[nextX][nextY] == 1){
                            brokenWallCount[nextX][nextY] = brokenWallCount[now.x][now.y] + 1;
                            nextQ.offer(new Location(nextX, nextY));
                            
                        } else {
                            brokenWallCount[nextX][nextY] = brokenWallCount[now.x][now.y];
                            nowQ.offer(new Location(nextX, nextY));
                        }
                        
                        if(nextX == endX && nextY == endY){
                            return brokenWallCount[nextX][nextY];
                        }
                    }
                }
                
            }

            // 현재 벽 x개 부숴 이동한 경우의 처리는 완료됐고
            // 다음 벽 x+1개 부숴 이동한 경우 처리할 것이 남아있는 경우
            if(nowQ.isEmpty()==true && nextQ.isEmpty()==false){
                /* 현재 처리할 대상 갱신 */
                nowQ = nextQ;
                nextQ = new LinkedList<Location>();
            }
        }
        return 0;
    }
    
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int[][] maze = new int[m+1][n+1];
        int[][] brokenWallCount = new int[m+1][n+1];
        boolean[][] check = new boolean[m+1][n+1];
        
        /* 미로 내 빈 방 또는 벽에 대한 정보 입력 처리 */
        for(int i=1; i<=m; i++){
            char[] line = br.readLine().toCharArray();
            for(int j=1; j<=n; j++){
                // 형변환 주의
                // int로 형변환 안해도 compile에서 오류가 발생 X, 그러나 입력으로 아스키코드 값이 들어감
                maze[i][j] = (int)(line[j-1]-'0');
            }
        }
        
        int answer = bfs(1, 1, m, n, maze, check, brokenWallCount);
        System.out.println(answer);
    }
}

