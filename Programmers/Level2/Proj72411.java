package Programmers.Level2;

import java.util.*;

/**
 * Title : 메뉴 리뉴얼
 * URI : https://programmers.co.kr/learn/courses/30/lessons/72411
 * 
 * Type : 해시, 조합
 */

public class Proj72411 {
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        
        HashMap<String, Integer> orderCount = new HashMap<>(); // 단품조합, 주문횟수
        HashMap<Integer, List<String>> descOrder = new HashMap<>(); // 조합에서 단품 개수, 내림차순 단품조합 리스트
        StringBuilder sb= new StringBuilder();
        
        for(String order : orders){
            for(int i=2; i<=order.length(); i++){
                calCombination(orderCount, i, 0, -1, sb, order);
            }
        }
        
        for(String order : orderCount.keySet()){
            int cnt = orderCount.get(order);
            if(cnt < 2) continue;
            
            List<String> list = null;
            
            if(descOrder.get(order.length()) == null){
                list = new ArrayList<String>();
            } else {
                list = descOrder.get(order.length());   
            }
             
            
            int i = list.size()-1;
            while(list.size() != 0 && cnt > orderCount.get(list.get(i))){
                list.remove(i);
                i--;
            }
            
            if(list.size() == 0){
                list.add(order);    
            } else {
                if(orderCount.get(list.get(list.size()-1)) == cnt){
                    list.add(order);
                }
            }
                        
            descOrder.put(order.length(), list);
        }
        
        
        for(int courseCnt : course){
            List<String> partOfAnswer = null;
            if(descOrder.get(courseCnt) != null){
                partOfAnswer = descOrder.get(courseCnt);
                for(String order : partOfAnswer){
                    answer.add(order);
                }
            }
        }
               
        
        return answer.stream().sorted().toArray(String[]::new);
    }
    
    public void calCombination(HashMap<String, Integer> orderCount, int allCnt, int selected, int index, StringBuilder sb, String order){
        if(allCnt == selected){
            char[] arr = sb.toString().toCharArray();
            Arrays.sort(arr);
            
            String newCombination = String.valueOf(arr);
            
            int cnt = orderCount.getOrDefault(newCombination,0);
            orderCount.put(newCombination, cnt+1);
            return;
        }
        
        for(int i=index+1; i<order.length(); i++){
            sb.append(order.charAt(i));
            calCombination(orderCount, allCnt, selected+1, i, sb, order);
            sb.setLength(sb.length()-1);
        }
    }
}
