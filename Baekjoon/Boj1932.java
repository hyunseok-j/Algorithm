package Baekjoon;
import java.util.*;
import java.io.*;

public class Boj1932 {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] d = new int[n+1][n+1];

        int[][] triangle = new int[n+1][n+1];
        // triangle[i][j] : i 번째 층의 j번째 수
        
        for(int i=1; i<triangle.length; i++){
            String[] input = br.readLine().split(" ");
            for(int j=1; j<i+1; j++){
                triangle[i][j] = Integer.parseInt(input[j-1]);
                
            }
        }

        d[1][1] = triangle[1][1];

        for(int i=2; i<triangle.length; i++){
            
            for(int j=1; j<i+1; j++){
                if(j-1 >= 1){
                    d[i][j] = Math.max(d[i-1][j],d[i-1][j-1]) + triangle[i][j];
                }
                else{
                    d[i][j] = d[i-1][j] + triangle[i][j];
                }
                
            }
            
        }

        int max = -1;
        for(int i=1; i< d[n].length; i++){
            if(d[n][i]>max){
                max = d[n][i];
            }
            
        }
        System.out.println(max);

    }

}
