package Baekjoon.BFS_DFS;

import java.io.*;

public class Boj14502 {

    private static int[][] originMap;
    private static int[][] testMap;
    private static boolean[][] visited;
    private static int n;
    private static int m;

    private static int[] dx = {0,0,-1,1};
    private static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = -1;
        String[] inputFirstLine = br.readLine().split(" ");
        n = Integer.parseInt(inputFirstLine[0]);
        m = Integer.parseInt(inputFirstLine[1]);

        originMap = new int[n][m];
        testMap = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            
            String[] inputMapByLine = br.readLine().split(" "); 

            for(int j=0; j<m; j++){
                originMap[i][j] = Integer.parseInt(inputMapByLine[j]);
                testMap[i][j] = originMap[i][j];
                visited[i][j] = false;
            }

        }


        for(int i1=0; i1<n; i1++){
            for(int j1=0; j1<m; j1++){
                for(int i2=0; i2<n; i2++){
                    for(int j2=0; j2<m; j2++){
                        for(int i3=0; i3<n; i3++){
                            for(int j3=0; j3<m; j3++){
                                // 선택한 세 좌표가 각각 다른 칸이고
                                if(!( (i1==i2 && j1==j2) || (i1==i3 && j1==j3) || (i2==i3 && j2==j3) )){
                                    // 선택한 세 좌표가 모두 빈 칸이라면 
                                    if(testMap[i1][j1]==0 && testMap[i2][j2]==0 && testMap[i3][j3]==0){
                                        testMap[i1][j1] = 1;
                                        testMap[i2][j2] = 1;
                                        testMap[i3][j3] = 1;

                                        for(int y=0; y<n; y++){
                                            for(int x=0; x<m; x++){

                                                if(testMap[y][x]==2 && !visited[y][x]){
                                                    dfsVirus(testMap, x, y, visited);
                                                }

                                            }
                                        }

                                        int count = 0;
                                        for(int y=0; y<n; y++){
                                            for(int x=0; x<m; x++){

                                                if(testMap[y][x]==0){
                                                    count += 1;
                                                }
                                            }
                                        }

                                        answer = Math.max(answer, count);

                                        // 초기화
                                        for(int y=0; y<n; y++){
                                            for(int x=0; x<m; x++){
                                                testMap[y][x] = originMap[y][x];
                                                visited[y][x] = false;
                                            }
                                        }
                                        
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.print(answer);


    }


    public static void dfsVirus(int[][] map, int x, int y, boolean[][] visited){
        if(map[y][x]==0){
            map[y][x] = 2;
        }
        visited[y][x] = true;

        for(int i=0; i<4; i++){

            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(0<=nextX && nextX <m && 0<=nextY && nextY<n){
                if(!visited[nextY][nextX] && map[nextY][nextX]!=1){
                    dfsVirus(map, nextX, nextY, visited);
                }
            }
        }
    }
}
