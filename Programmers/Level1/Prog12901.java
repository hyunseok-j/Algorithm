package Programmers.Level1;

import java.util.Arrays;
import java.util.ArrayList;
/**
 * Title : 2016년
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12901
 * 
 * Type : 브루트포스
 */

public class Prog12901 {
    public String solution(int a, int b) {
        String answer = "";
        
        int days = 0;
        String[] yoil = {"FRI","SAT","SUN", "MON", "TUE","WED","THU"};
        ArrayList<Integer> day31Mons = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3,5,7,8,10,12}));
        
        for(int mon=1; mon<=12; mon++){
            boolean stop = false;
            int maxDay = -1;
            if(mon == 2){
                maxDay = 29;
            } else if (day31Mons.contains(mon)){
                maxDay = 31;
            } else {
                maxDay = 30;
            }
            for(int day=1; day<=maxDay; day++){
                if(mon == a && day == b){
                    stop = true;
                    break;
                }
                days++;
            }
            if(stop == true) break;
        }
        
        answer = yoil[days%7];
        
        return answer;
    }
}