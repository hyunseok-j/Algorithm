package Baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Title : 드래곤 커브
 * desc : 
 *  드래곤 커브는 속성 3개[1) 시작점, 2) 시작 방향, 3) 세대] 로 이뤄져 있으며, 이차원 좌표 평면 위에서 정의된다.
 *  좌표 평면의 x축은 → 방향, y축은 ↓ 방향이다.
 *  드래곤 커브는 세대가 증가할 때마다(K세대일 때) 이전 세대 커브 모양(K-1세대)을 시계 방향 90도 회전시키고
 *  이전 세대의 끝점에 회전시킨 커브를 이어붙인 형태가 된다.
 *  방향은 0, 1, 2, 3 중 하나이고 다음을 의미한다.
 *   0 : x가 증가하는 방향
 *   1 : y가 감소하는 방향
 *   2 : x가 감소하는 방향
 *   3 : y가 증가하는 방향
 *  여러 개의 드래곤 커브 정보가 주어질 때, 100*100 (0<=x<=100, 0<=y<=100) 크기의 격자판의 
 *  각 칸 중 네 꼭지점 모두 드래곤 커브가 지나갔는 칸의 개수를 구하시오.
 * 
 * Type : 시뮬레이션
 *  문제에서 x는 가로좌표, y는 세로좌표로 줬으나 
 *  기존에 풀던대로 익숙한 쪽으로 x를 세로, y를 가로로 설정하고 풀었다.
 *  
 *  드래곤 커브를 1개 입력받으면 
 *  드래곤 커브를 만들고 커브를 이루는 꼭지점 좌표를 저장해뒀다.
 *  모든 드래곤 커브를 만들고 커브를 이루는 꼭지점 좌표를 모두 구한 후,  
 *  map(100*100 격자판의 꼭지점 좌표)의 범위 안에 들어가는 유효한 좌표만 map에 1을 저장하므로써 커브가 지나가는 좌표임을 표시했고
 *  꼭지점 (i,j)를 기준으로  (i,j), (i,j+1), (i+1,j), (i+1,j+1) 4 좌표를 확인하면
 *  정답을 만족하는 칸을 확인할 수 있어서 이를 이중 for문으로 처리했다.
 * 
 *  드래곤 커브를 만드는 과정은
 *  k세대 드래곤 커브를 만든다고 했을 때
 *  k-1세대 드래곤 커브를 원점 기준으로 시계 방향 90도 회전했을 때 좌표를 아래의 형태로 모두 구하고 
 *   [(a, b) -> (-b, a)]
 *  k-1세대 커브의 끝점(endPoint)을 그 끝점을 90회전 시킨 점(rotateEndPoint)에 붙이기 위해 앞서 구한 좌표들을
 *   [endPoint - rotatePoint] 만큼을 평행이동했다.
 *  끝점을 다시 갱신하기 위해 (0,0)이 90도 회전하고 평행이동한 좌표를 끝점으로 저장했다. 
 *  
 */

public class Boj15685{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map  = new int[101][101];

        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        while(n-- > 0){
            int x = sc.nextInt();
            int y= sc.nextInt();
            int d = sc.nextInt();
            int g = sc.nextInt();

            ArrayList<Integer> pointX = new ArrayList<>();
            ArrayList<Integer> pointY = new ArrayList<>();

            ArrayList<Integer> movedPointX = new ArrayList<>();
            ArrayList<Integer> movedPointY = new ArrayList<>();

            // 시작 지점을 원점 기준으로 세대별로 90도씩 돌려가며 지나가는 경로 point 저장
            pointX.add(0);
            pointY.add(0);

            pointX.add(dx[d]);
            pointY.add(dy[d]);

            int endPointX = dx[d];
            int endPointY = dy[d];

            for(int i=1; i<=g; i++){
                ArrayList<Integer> nowX = new ArrayList<>();
                ArrayList<Integer> nowY = new ArrayList<>();

                // 이전 세대에 있던 도형 원점 기준으로 90도 회전
                for(int j=0; j<pointX.size(); j++){
                    // (a,b) -> (-b,a)
                    int rotateX = pointY.get(j);
                    int rotateY = -pointX.get(j);
                    nowX.add(rotateX);
                    nowY.add(rotateY);
                }
                // 이전 세대에 있던 도형의 끝점과 회전한 도형의 끝점을 이어붙였을 때 회전한 도형을 이루는 점들의 좌표 계산
                // 현재 세대에 추가된 점 추가
                int rotateEndPointX = endPointY;
                int rotateEndPointY = -endPointX;

                int diffX = endPointX - rotateEndPointX;
                int diffY = endPointY - rotateEndPointY;

                for(int j=0; j<nowX.size(); j++){
                    pointX.add(nowX.get(j) + diffX);
                    pointY.add(nowY.get(j) + diffY);
                }

                // 도형 끝점 갱신
                endPointX = diffX; // (0,0)이 90도 회전하고 평행이동한 좌표 
                endPointY = diffY;
            }

            // 시작 지점 (x, y)로 맞추기 위해 평행이동
            for(int i=0; i<pointX.size(); i++){
                movedPointX.add(pointX.get(i) + y);
                movedPointY.add(pointY.get(i) + x);
            }

            // map 범위 안에 해당되는 점들만 표시
            for(int i=0; i<movedPointX.size(); i++){
                int validPointX = movedPointX.get(i);
                int validPointY = movedPointY.get(i);
                if( 0<= validPointX && validPointX <=100 && 0<= validPointY && validPointY <= 100){
                    map[validPointX][validPointY] = 1;
                }
            }
        }

        int answer = 0;
        for(int i=0; i < map.length-1; i++){
            for(int j=0; j < map[i].length-1; j++){
                if(map[i][j]==1 && map[i+1][j]==1 && map[i][j+1]==1 && map[i+1][j+1]==1){
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }
}
