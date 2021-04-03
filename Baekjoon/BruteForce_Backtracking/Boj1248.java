package Baekjoon.BruteForce_Backtracking;

import java.util.Scanner;
import java.util.ArrayList;

public class Boj1248 {

    static char[][] s;
    static int[] nums = {-10,-9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10};
    static StringBuilder sb = new StringBuilder();
    static boolean done = false;

    static void chooseOne(int count, int n, int[] prevSum, ArrayList<Integer> selectedNums){
        if(done==true) return;
        if(count == n){
            for(int item : selectedNums){
                sb.append(item + " ");
            }
            done = true;
            return;
        }
        for(int index=0; index<21; index++){

            boolean ok = true;

            for(int i=0; i<=count; i++){
                if(i == count){
                    if(s[i][count] != sumItoJ(0,nums[index])){
                        ok = false;
                        break;
                    }
                }
                else{
                    if(s[i][count] != sumItoJ(prevSum[i],nums[index])){
                        ok = false;
                        break;
                    }
                }
            }
            if(ok){
                for(int i=0; i<=count; i++){
                    prevSum[i] += nums[index];
                }
                selectedNums.add(nums[index]);
                chooseOne(count+1,n, prevSum, selectedNums);
                selectedNums.remove(selectedNums.size()-1);
                for(int i=0; i<=count; i++){
                    prevSum[i] -= nums[index];
                }
            }
        }
    }

    static char sumItoJ(int prevSum, int num){
        char result = ' ';
        if((prevSum + num) > 0){
            result = '+';
        }
        else if((prevSum + num) == 0){
            result = '0';
        }
        else{
            result = '-';
        }
        return result;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        char[] input = sc.nextLine().toCharArray();
        s = new char[n][n];

        int index = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(0<= j && j<i){
                    s[i][j] = 'x';
                    continue;
                }
                s[i][j] = input[index];
                index++;
            }
        }

        ArrayList<Integer> selectedNums = new ArrayList<>();
        int[] prevSum = new int[n];

        chooseOne(0, n, prevSum, selectedNums);

        System.out.print(sb);
    }
}
