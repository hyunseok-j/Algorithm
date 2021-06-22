package Programmers.Level2;

import java.util.*;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/12973
 * Title : 짝지어 제거하기
 *  Stack
 */

public class Proj12973 {
    public int solution(String s)
    {
        int answer = 0;
        Stack<Character> stack = new Stack<Character>();
        
        for(int i=0; i<s.length(); i++){
            if(stack.empty()){
                stack.add(s.charAt(i));
                continue;
            }
            
            char target = stack.peek();
            
            if (target == s.charAt(i)) {
                stack.pop();
            } else {
                stack.add(s.charAt(i));
            }
        }
        
        if (stack.empty()) answer = 1;
        
        return answer;
    }
}

/* 처음에 시도한 풀이 : 일치하는 문자쌍 제거할 때마다 substring 으로 문자열 s 갱신 */
/* substring 자체의 연산 시간 때문에 기존에 생각하던 O(n) 시간 내에 끝나지 않았다 */

//         StringBuilder sb = new StringBuilder();
//         int index = 0;
        
//         while(index < s.length()-1){
            
//             // index가 i번째 문자 == i+1번째 문자 비교
//             if (s.charAt(index) == s.charAt(index+1)){
                
//                 // i와 i+1번째 문자 제거
//                 sb.append(s.substring(0, index));
//                 sb.append(s.substring(index+2, s.length()));
//                 s = String.valueOf(sb);
//                 sb.setLength(0);
                
//                 // index = i-1로 돌아감 
//                 // 문자 2개가 제거되기 전, i-1 문자와 i+2문자가 같을 수도 있기 때문에 이전 문자로 돌아가서 확인
//                 index = (index == 0? index : index - 1);
                
//             } else {
                
//                 // index = i+1 다음 문자 확인
//                 index = index + 1;
//             }
//         }
