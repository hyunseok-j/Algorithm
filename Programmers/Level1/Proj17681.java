package Programmers.Level1;
import java.util.*;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/17681
 * Title : [2018 카카오 블라인드 공채] 비밀 지도
 *  진법 변환 구현, 문자열 처리
 */

public class Proj17681 {
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        ArrayList<String> result = new ArrayList<>();
        
        for(int i=0; i<arr1.length; i++){
            String res1 = dec2bin(arr1[i], n);
            String res2 = dec2bin(arr2[i], n);
            String resultI = union(res1, res2);
            result.add(resultI);
        }
        
        answer = new String[result.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    public String dec2bin(int dec, int n){
        StringBuilder ret = new StringBuilder();
        int stepNum = dec;
        
        while(stepNum > 0){
            ret.append(String.valueOf(stepNum % 2));
            stepNum = stepNum / 2;
        }
        
        int gap = n - ret.length();
        for(int i=0; i<gap; i++){
            ret.append("0");
        }
        
        return String.valueOf(ret.reverse());
    }
    
    public String union(String s1, String s2){
        StringBuilder ret = new StringBuilder();
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) == '1' || s2.charAt(i) == '1'){
                ret.append("#");
                continue;
            }
            ret.append(" ");
        }
        return String.valueOf(ret);
    }
}
