package Baekjoon;
import java.util.*;
import java.io.*;


// 2020.10.21


public class Boj2110 {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputFirst = br.readLine().split(" ");
        
        int n = Integer.parseInt(inputFirst[0]);
        int c = Integer.parseInt(inputFirst[1]);

        ArrayList<Integer> houses = new ArrayList<>();

        for(int i=0; i<n; i++){
            houses.add(Integer.parseInt(br.readLine()));
        }


        Collections.sort(houses);

        int start = houses.get(1) - houses.get(0);
        int end = houses.get(houses.size()-1) - houses.get(0);
        int result = 0;

        while(start<= end){
            int mid = (start+end)/2;
            int value = houses.get(0);
            int count = 1;

            for(int i=1;i<houses.size();i++){
                if(houses.get(i) >= value + mid){
                    value = houses.get(i);
                    count++;
                }
            }

            if(count>=c){
                start = mid + 1;
                result = mid;
            }
            else{
                end = mid-1;
            }
        }

        System.out.print(result);

    }


}
