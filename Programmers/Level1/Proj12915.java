package Programmers.Level1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Title : 문자 내 마음대로 정렬하기
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12915
 */

public class Proj12915 {

    public String[] solution(String[] strings, int n) {
        String[] answer = strings;
        
        Arrays.sort(answer, new Comparator<String>(){
           public int compare(String s1, String s2){
               char first = s1.charAt(n);
               char second = s2.charAt(n);
               
               if(first == second){
                   return s1.compareTo(s2);
               }
               return (int)(first) - (int)(second);
           } 
        });
        
        return answer;
    }
    
}
