package Baekjoon.BruteForce_Backtracking;

import java.util.Scanner;

public class Boj15649 {
    static boolean[] check;
    
    static String getMSet(int cIndex, int m){
        StringBuilder ret = new StringBuilder();
        if(m==1){
	        check[cIndex] = true;
            for(int i=1; i<check.length; i++){
                if(check[i]==true){
                    ret.append(i + " ");
                }
            }
            check[cIndex] = false;
            return ret.toString();
        }
        
        check[cIndex] = true;
        for(int i=cIndex+1; i<check.length; i++){
            String s = getMSet(i,m-1);
            if(!s.equals("")){
                ret.append(s);
            }
        }
        check[cIndex] = false;
        return ret.toString();
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        StringBuilder answer = new StringBuilder();
        
        check = new boolean[n+1];
        for(int i=1; i+m-1<=n ; i++){
            answer.append(getMSet(i,m));
        }
	    String[] s = answer.toString().split(" ");

        answer.setLength(0);
        for(int i=0; i<s.length; i+=m){
            for(int j=i; j<=i+m-1; j++){
                answer.append(s[j]+" ");
            }
            answer.append("\n");
        }

        System.out.print(answer);
    }
}
