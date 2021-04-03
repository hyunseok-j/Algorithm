package Baekjoon.BruteForce_Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;


public class Boj1107 {
    private static HashSet<Integer> getPosibleChannel(int len, ArrayList<Integer> normalNumButtons){

        HashSet<Integer> set = new HashSet<Integer>();
    
            if(len == 1){
                for(int i=0; i<normalNumButtons.size(); i++){
            set.add(normalNumButtons.get(i));
            }
        }
        else{
            HashSet<Integer> preSet = getPosibleChannel(len-1, normalNumButtons);
            set = preSet;
    
    
            int preSize = preSet.size();
                int size = normalNumButtons.size();
        
    
            for(int i=0; i<size; i++){
                Iterator iter = preSet.iterator();
                while(iter.hasNext()){
                    Integer preNum = Integer.parseInt(iter.next().toString());
                    set.add(normalNumButtons.get(i)*(int)Math.pow(10,len-1) + preNum);
                }
            }
        }
        return set;
        }
    
        public static void main(String[] args) throws IOException{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int channel = Integer.parseInt(br.readLine());
        int errorButtonCnt = Integer.parseInt(br.readLine());
    
        String[] errorButtonsStr = null;
    
        ArrayList<Integer> normalNumButtons = new ArrayList<Integer>(Arrays.asList(new Integer[]{0,1,2,3,4,5,6,7,8,9}));
        HashSet<Integer> allPosibleChannel = null;
        if(errorButtonCnt != 0){
            errorButtonsStr = br.readLine().split(" ");
            for(int i=0; i<errorButtonsStr.length; i++){
                normalNumButtons.remove(Integer.parseInt(errorButtonsStr[i]));
            }
        }
    
    
        allPosibleChannel = getPosibleChannel(5, normalNumButtons);
    
    
        System.out.println(allPosibleChannel.size());
    
        }
}
