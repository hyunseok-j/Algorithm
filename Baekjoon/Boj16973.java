package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Title : 직사각형 탈출
 * desc : 
 *  n*m 격자칸에 h*w 직사각형이 놓여있다. 이 때, 처음 직사각형의 가장 왼쪽 위 칸을 (sr, sc) 라고 하고 
 *  직사각형의 가장 왼쪽 위 칸이 (fr, fc)가 될 때까지 상하좌우로 이동했을 때 최소 이동 횟수를 구하시오.
 *  단, 격자칸 1*1은 빈 칸(0) 이거나 벽(1)으로 구성되어 있어서 이동하고자 하는 방향에 벽이 있는 경우 직사각형은 이동하지 못한다. 
 * 
 * Type : BFS, 부분합
 *  1) 격자칸을 BFS로 한 칸씩 이동해보는 시간 : O(NM)
 *  2) 직사각형이 이동방향으로 이동 가능한지 판단하는데 걸리는 시간 
 *   2-1) 이중 for문 : O(HW) (직사각형 내 각 칸이 1이 존재하는지 확인)
 *   2-2) 부분합 : O(1) (직사각형 범위의 칸들의 합이 0인지 확인), O(NM) (부분합 구하는 시간)
 * 
 *  1)과 2-1) 방법 -> O(NM*HW)
 *  1)과 2-2) 방법 -> O(NM*1 + NM) -> O(NM) : 크기가 10^6 안의 수이므로 가능
 * 
 * 
 */


public class Boj16973 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);
        int[][] dist = new int[n+1][m+1];

        int[][] map = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            String[] line = br.readLine().split(" ");
            for(int j=1; j<=m; j++){
                map[i][j] = Integer.parseInt(line[j-1]);
                dist[i][j] = -1;
            }
        }

        String[] last = br.readLine().split(" ");
        int h = Integer.parseInt(last[0]);
        int w = Integer.parseInt(last[1]);
        int startRow = Integer.parseInt(last[2]);
        int startCol = Integer.parseInt(last[3]);
        int finishRow = Integer.parseInt(last[4]);
        int finishCol = Integer.parseInt(last[5]);

        int[][] subSum = new int[n+1][m+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                subSum[i][j] = subSum[i][j-1] + subSum[i-1][j] - subSum[i-1][j-1] + map[i][j];
            }
        }
        

        int answer = bfs(map, startRow, startCol, finishRow, finishCol, w, h, dist, subSum);

        System.out.println(answer);

    }

    

    private static int bfs(int[][] map, int startRow, int startCol, int finishRow, int finishCol, int w, int h, int[][] dist, int[][] subSum) {

        int n = map.length-1;
        int m = map[0].length-1;

        Queue<Integer> q = new LinkedList<>();
        q.offer(startRow); q.offer(startCol);
        dist[startRow][startCol] = 0;

        while(!q.isEmpty()){
            
            int nowX = q.poll();
            int nowY = q.poll();

            for(int i=0; i<4; i++){
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];                
                if(isInMap(nextX, nextY, w, h, n, m) && dist[nextX][nextY] == -1){
                    if(subSum[nextX+h-1][nextY+w-1]-subSum[nextX+h-1][nextY-1]-subSum[nextX-1][nextY+w-1] + subSum[nextX-1][nextY-1] == 0){
                        q.offer(nextX); q.offer(nextY);
                        dist[nextX][nextY] = dist[nowX][nowY] + 1;
                    }
                }
            }
        }
        
        return dist[finishRow][finishCol];
    }

    private static boolean isInMap(int nextX, int nextY, int w, int h, int n, int m) {
        boolean ret = false;
        if(1<= nextX && nextX <=n && 1<= nextY && nextY <= m &&
           1<= nextX+h-1 && nextX+h-1 <=n && 1<= nextY+w-1 && nextY+w-1 <=m){
            ret = true;
        }
        return ret;
    }


}
