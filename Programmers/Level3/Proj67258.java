package Programmers.Level3;

import java.util.*;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/67258
 * Title : [카카오 인턴] 보석 쇼핑
 *  투 포인터 알고리즘, HashMap, HashSet 이용
 */

public class Proj67258 {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int start = 0;
        int end = 0;
        int answerStart = 0;
        int answerEnd = 0;
        int sectionLen = gems.length;
        
        HashSet<String> gemsCategories = new HashSet<>();
        HashMap<String, Integer> gemsCountBySection = new HashMap<>();
        
        for(String gem : gems){
            gemsCategories.add(gem);
        }
        
        while(start < gems.length && end < gems.length && start <= end){
            
            addGem(gemsCountBySection, gems[end]);
            
            if(gemsCategories.size() == gemsCountBySection.size()) {
                int result = end - start;
                if(result < sectionLen) {
                    sectionLen = result;
                    answerStart = start;
                    answerEnd = end;
                }
                removeGem(gemsCountBySection, gems[start]);
                removeGem(gemsCountBySection, gems[end]);
                start++;
                
            } else {
                end++;
            }
        }
        
        answer[0] = answerStart + 1;
        answer[1] = answerEnd + 1;
        
        return answer;
    }
    
    public void removeGem(HashMap<String, Integer> hm, String gem){
        Integer currentThisGemCount = hm.get(gem);
        if(currentThisGemCount == null) return;
        
        int result = currentThisGemCount - 1;
        if (result == 0){
            hm.remove(gem);
        } else {
            hm.put(gem, result);
        }
    }
    public void addGem(HashMap<String, Integer> hm, String gem){
        Integer currentThisGemCount = hm.get(gem);
            
        if(currentThisGemCount == null){
            hm.put(gem, 1);
        } else {
            hm.put(gem, currentThisGemCount + 1);
        }
    }
}
