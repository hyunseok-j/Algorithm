package Baekjoon.BitMask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Boj11723 {

    static StringBuilder sb = new StringBuilder();

    static int exec(int set, String command, int num){
        if(command.equals("add")){
            set = set | (1<<num);
        }
        else if(command.equals("remove")){
            set = set & ~(1<<num);
        }
        else if(command.equals("check")){
            if((set & (1<<num))!=0){
                sb.append("1\n");
            }
            else{
                sb.append("0\n");
            }
        }
        else if(command.equals("toggle")){
            set = set ^ (1<<num);
        }
        else if(command.equals("all")){
            set = (1<<20) - 1;
        }
        else{ // command == empty
            set = 0;
        }
        return set;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int set = 0;

        while(t-- > 0){
            String[] input = br.readLine().split(" ");

            String command = input[0];
            int num = -1;
            if(input.length == 2){
                num = Integer.parseInt(input[1])-1;
            }
            
            set = exec(set, command, num);
        }
        System.out.print(sb);
    }
}
