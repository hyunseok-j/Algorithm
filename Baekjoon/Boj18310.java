package Baekjoon;
import java.util.*;
import java.io.*;

// 2020.10.21


public class Boj18310 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] house = br.readLine().split(" ");

        

        ArrayList<Integer> houseArr = new ArrayList<>();
        for(int i=0; i<house.length; i++){
            houseArr.add(Integer.parseInt(house[i]));    
        }

        

        Collections.sort(houseArr);


        System.out.print(houseArr.get((n-1)/2));


    }    
}
