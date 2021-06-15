package JongmanBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jm160 {

    private static int[][][] dxdy = {{{1,0},{0,1}},
                             {{1,0},{1,1}},
                             {{0,1},{1,1}},
                             {{1,-1},{1,0}}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            String[] sizeInput = br.readLine().split(" ");
            int height = Integer.parseInt(sizeInput[0]);
            int width = Integer.parseInt(sizeInput[1]);
            char[][] map = new char[height][width];

            for(int i=0; i<height; i++){
                map[i] = br.readLine().toCharArray();
            }

            System.out.println(countToFillMapWithL(map));
        }

    }

    private static int countToFillMapWithL(char[][] map) {
        int ret = 0;
        int x = -1;
        int y = -1;

        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if(map[i][j] == '.'){
                    x = i;
                    y = j;
                    break;
                }
            }
            if(y != -1) break;
        }

        if(y == -1) return 1;

        
        for(int caseNum = 0; caseNum < 4; caseNum++){
            if(isCapableFillMap(x, y, map, caseNum)){
                fillMapWithL(x, y, map, caseNum, 1);            
                ret += countToFillMapWithL(map);
                fillMapWithL(x, y, map, caseNum, 0);
            }
        }                    


        return ret;
    }

    private static void fillMapWithL(int x, int y, char[][] map, int caseNum, int mode) {
        char color = (mode==1)? '#' : '.';

        map[x][y] = color;

        for(int i=0; i<dxdy[caseNum].length; i++){
            int dx = dxdy[caseNum][i][0];
            int dy = dxdy[caseNum][i][1];
            int nx = x + dx;
            int ny = y + dy;
            map[nx][ny] = color;
        }
    }

    private static boolean isCapableFillMap(int x, int y, char[][] map, int caseNum) {
        boolean ret = true;

        for(int i=0; i<dxdy[caseNum].length; i++){
            int dx = dxdy[caseNum][i][0];
            int dy = dxdy[caseNum][i][1];
            int nx = x + dx;
            int ny = y + dy;

            if(0<= nx && nx < map.length && 0<= ny && ny < map[nx].length && map[nx][ny] == '.'){
                ret = true;
            } else {
                return ret = false;
            }
        }
        
        return ret;
    }
    
}
