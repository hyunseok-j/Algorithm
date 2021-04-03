package Baekjoon.BruteForce_Backtracking;

import java.util.Scanner;


/**
 * Title : NM과 K (1)
 * desc : 
 *  세로의 길이가 n이고 가로의 길이가 m인 격자판이 주어지고 각 칸에 정수가 존재한다. 
 *  이 때 격자판에서 k칸을 골라서 k칸 내 정수들의 합을 구할 때, 그 합이 최대일 때의 값을 구하시오.
 *  단, 선택한 k칸은 인접하면 안된다. 
 */

class Boj18290{

    static int[][] map; // n*m 격자판 
    static boolean[][] chooseCheck; // 선택한 칸
    static boolean[][] directCheck;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int ans = -2147483647;

    // chooseOne : map[x][y] 칸을 선택한 후, 그 이후의 칸들을 선택할지 결정
    // 선택된 적 없는 칸이고 인접한 칸에 선택된 칸이 없는 경우, map[x][y]를 선택했다고 가정하고 다른 칸 확인
    static void chooseOne(int x, int y, int n, int m, int k, int cnt, int sum){

        // 종료 조건 : 선택한 칸 수(cnt)가 k칸이 된 경우
        if(cnt==k){

            // k칸의 정수들을 모두 합한 값(sum)이 여태 나온 것 중 가장 큰 합(ans)보다 클 때, 최대값 갱신
            if( ans < sum ) ans = sum;
            return;
        }
        
        // map[x][y] 보다 이후의 칸 모두 확인
        for(int i=x; i<=n; i++){  
            for(int j=(i==x?y+1:1); j<=m; j++){
                if(chooseCheck[i][j] == false){
                    boolean ok = true;

                    // 인접한 칸 중 선택된 칸이 있는지 확인
                    for(int z=0; z<4; z++){
                        int nx = i+dx[z];
                        int ny = i+dy[z];
                        if(1<= nx && nx <= n && 1<= ny && ny <= m){
                            if(chooseCheck[nx][ny]==true){
                                ok = false;
                                break;
                            }
                        }
                    }

                    // 인접한 칸이 모두 선택되지 않은 칸일 때, map[i][j] 칸을 선택
                    if(ok){
                        chooseCheck[i][j] = true;
                        chooseOne(i,j,n,m,k,cnt+1,map[i][j]+sum);
                        chooseCheck[i][j] = false;
                    }
                }
            }
        }
        
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        sc.nextLine();
        map = new int[n+1][m+1];
        chooseCheck = new boolean[n+1][m+1];
        directCheck = new boolean[n+1][m+1];

        for(int i=1; i<=n; i++){
            String[] input = sc.nextLine().split(" ");
            for(int j=1; j<=m; j++){
                map[i][j] = Integer.parseInt(input[j-1]);
            }
        }
  
        chooseOne(1,0,n,m,k,0,0);
        System.out.print(ans);
    }
}