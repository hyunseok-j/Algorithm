package Programmers.Level1;

import java.util.*;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12917
 * Title : 문자열 내림차순으로 배치하기
 *  내림차순 우선순위 큐에 문자열을 구성하는 첫문자부터 끝문자까지 추가해가며 추가할 때마다 정렬되게 구현
 *  약 O(nlg(n)) : n은 문자열 길이
 * 
 *  조금 더 오래 걸리더라도 다른 정렬 알고리즘으로도 구현 가능
 */

public class Proj12917 {
    public String solution(String s) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Character> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(char c : s.toCharArray()){
            pq.offer(c);
        }
        
        while(!pq.isEmpty()){
            sb.append(String.valueOf(pq.poll()));
        }
        
        answer = String.valueOf(sb);
        
        return answer;
    }
}
