package Baekjoon;

import java.util.Scanner;

/**
 * Title : 정육면체 전개도
 * desc : 
 *  입력으로 6개의 정사각형으로 이뤄진 전개도가 주어졌을 때, 
 *  주어진 전개도로 정육면체를 만들 수 있는지 여부를 확인해서 "yes"나 "no"를 출력하시오.
 * 
 * Type : 시뮬레이션
 * 실제 정육면체의 각 면에 0~5까지의 번호를 매기고
 * 각 면의 상하좌우에 올 수 있는 면이 몇 번 면인지 sideNum에 저장해뒀다.
 * 이 때 상하좌우 순서별로 면의 번호를 저장해뒀지만 해당 면을 어디서 바라봤는지에 따라 순서가 바뀔 수 있다. 
 * 그래서 입력받은 전개도의 정사각형에 면 번호를 지정을 할 때,
 *  처음 정사각형은 0번 면으로 지정하고 0번 면의 상하좌우에 오는 면 번호 그대로 다음 정사각형에 면 번호를 부여하고 
 *  면 번호 부여받은 정사각형의 다음 정사각형에 면 번호를 지정할 때 
 *   이전에 방문한 정사각형의 면 번호와 그 정사각형이 현재 정사각형의 상하좌우 중 어디에 위치했는지 확인한 다음 상하좌우에 올 면 번호의 순서를 결정했다.
 * 정사각형에 지정이 된 면 번호는 따로 square[면 번호]에 1로 표시했다.
 * 
 * 전개도 모든 면에 면 번호 지정이 끝났을 때, 0부터 5까지 모든 면 번호가 지정이 됐으면 true, 아니면 false를 반환했다.
 */

public class Boj1917 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = 3;

        while(t-- > 0){
            
            int[][] input = new int[6][6];
            for(int i=0; i<6; i++){
                for(int j=0; j<6; j++){
                    input[i][j] = sc.nextInt();
                }
            }

            // 정육면체 전개도이면 yes, 아니면 no 출력 
            if(isCube(input)){
                System.out.println("yes");
            } else {
                System.out.println("no");
            }

        }
    }

    static boolean isCube(int[][] input){
        boolean result = false;
        boolean[][] check = new boolean[6][6];
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        int[] square = new int[6];
        int[][] sideNum = {
            {3,2,1,4},
            {5,4,0,2},
            {3,5,1,0},
            {4,5,2,0},
            {5,3,0,1},
            {3,4,1,2}};


        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if(input[i][j] == 1){
                    dfs(i,j, 0, -1, -1, -1, check, dx, dy, square, sideNum, input);
                }
            }
        }

        int count = 0;
        for(int i=0; i<6; i++){
            if(square[i] == 1){
                count += 1;
            }
        }
        
        if(count == 6) result = true;

        return result;
    }

    static void dfs(int x, int y, int num, int preX, int preY, int preNum, boolean[][] check, int[] dx, int[] dy, int[] square, int[][] sideNum, int[][] input){


        check[x][y] = true;
        square[num] = 1;

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0<= nx && nx < 6 && 0<= ny && ny < 6){
                if(check[nx][ny] == false && input[nx][ny]==1){ 
                
                    int nextNum = -1;
                    if(num == 0){
                        nextNum = sideNum[num][i];
                    } else {
                        int dir1 = -1;
                        for(int j=0; j<4; j++){
                            int pX = x + dx[j];
                            int pY = y + dy[j];
                            if(0<= pX && pX < 6 && 0<= pY && pY < 6){
                                if(preX == pX && preY == pY){
                                    dir1 = j;
                                    break;
                                }
                            }
                        }
                        int dir2 = -1;
                        for(int j=0; j<4; j++){
                            if(sideNum[num][j]==preNum){
                                dir2 = j;
                                break;
                            }
                        }

                        int diff = dir2 - dir1;
                        nextNum = sideNum[num][(i+4+diff)%4];
                    }
                    dfs(nx, ny, nextNum, x, y, num, check, dx, dy, square, sideNum, input);
                }
            }
        }

    }
    
}
