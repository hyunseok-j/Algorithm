package Baekjoon.BruteForce_Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Boj14500 {
    private static int[][] map;
	static class Tetromino{
		int currentX;
		int currentY;
		int[] dx;
		int[] dy;
		public Tetromino(int currentX, int currentY, int[] dx, int[] dy){
			this.currentX = currentX;
			this.currentY = currentY;
			this.dx = dx;
			this.dy = dy;
		}
		public void setCurrentX(int x){
			this.currentX = x;
		}
		public void setCurrentY(int y){
			this.currentY = y;
		}
		public int getSum(){
			int sum = 0;
			for(int i=0; i<4; i++){
				int x = currentX+dx[i];
				int y = currentY+dy[i];
				if(1<= x && x< map.length && 1<= y && y < map[currentX].length){
					sum += map[currentX+dx[i]][currentY+dy[i]];
				}
				else{
					sum = 0;
					break;
				}
			}
			return sum;
		}
	}

    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputLen = br.readLine().split(" ");
		int n = Integer.parseInt(inputLen[0]);
		int m = Integer.parseInt(inputLen[1]);
		int max = 0;

		map = new int[n+1][m+1];
		for(int i=0; i<n; i++){
			String[] row = br.readLine().split(" ");
			for(int j=0; j<m; j++){
				map[i+1][j+1] = Integer.parseInt(row[j]);
			}
		}

		int[][] allDx = {{0,0,0,0},
				{0,1,2,3},
				{0,0,0,1},
				{0,0,0,1},
				{0,0,0,1},
				{0,0,0,-1},
				{0,0,0,-1},
				{0,0,0,-1},
				{0,1,2,0},
				{0,1,2,1},
				{0,1,2,2},
				{0,1,2,0},
				{0,1,2,1},
				{0,1,2,2},
				{0,0,1,1},
				{0,1,1,2},
				{0,1,1,2},
				{0,0,1,1},
				{0,0,1,1}};

		int[][] allDy = {{0,1,2,3},
				{0,0,0,0},
				{0,1,2,2},
				{0,1,2,0},
				{0,1,2,1},
				{0,1,2,0},
				{0,1,2,1},
				{0,1,2,2},
				{0,0,0,1},
				{0,0,0,1},
				{0,0,0,1},
				{0,0,0,-1},
				{0,0,0,-1},
				{0,0,0,-1},
				{0,1,0,1},
				{0,0,-1,-1},
				{0,0,1,1},
				{0,1,1,2},
				{0,-1,-1,-2}};

		Tetromino[] arr = new Tetromino[19];

		for(int i=0; i<19; i++){
			arr[i] = new Tetromino(0,0,allDx[i],allDy[i]);
		}


		for(int i=1; i<=n; i++){
			for(int j=1; j<=m; j++){
				for(int k=0; k<19; k++){
					arr[k].setCurrentX(i);
					arr[k].setCurrentY(j);
					max = Math.max(max, arr[k].getSum());
				}
			}
		}

		System.out.print(max);

	}
}