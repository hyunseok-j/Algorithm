package Programmers.Level2;

import java.util.Stack;

/**
 * Title : 괄호 변환
 * URI : https://programmers.co.kr/learn/courses/30/lessons/60058
 * 
 * Type : 그리디(균형있는괄호배열로 split 될 수 없는 조건), 완전탐색
 */

public class Proj60058 {

    public static void main(String[] args){
        String p = ")(";

        System.out.println(solution(p));        
    }


    public static String solution(String p) {
        String answer = "";
            
        answer = cal(p);
        
        return answer;
    }
    
    public static String cal(String p){
        if(p.equals("")) return "";
        if(isRightSameCountStr(p)) return p;
        
        String answer = "";
        String u = "";
        String v = "";
        
        int mid = splitUV(p);
        
        u = p.substring(0,mid+1);
        v = p.substring(mid+1, p.length());


        if(isRightSameCountStr(u)){
            answer = u + cal(v);
        } else {
            answer = "(" + cal(v) + ")" + reverse(u.substring(1,u.length()-1));
        }
        
        return answer;
    }
    
    public static int splitUV(String p){
        int left = 0;
        int right = 0;
        int mid = 0;
        
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) == '('){
                left++;
            } else {
                right++;
            }
            
            if(left == right){
                mid = i;
                if(isCapableSplit(p.substring(0,mid+1)) == false){
                    break;
                }
            }
            
        }
        
       return mid;
    }
    
    public static String reverse(String p){
        StringBuilder newStr = new StringBuilder();
        
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) =='('){
                newStr.append(")");
            } else {
                newStr.append("(");
            }
        }
        return newStr.toString();
    }
    
    public static boolean isCapableSplit(String p){
        boolean ret = false;
        int left = 0;
        int right = 0;
        
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) == '('){
                left++;
            } else {
                right++;
            }
            
            if(left == right && i<p.length()-1){
                ret = true;
            }
        }
        
        return ret;
    }
    
    public static boolean isSameCountStr(String p){
        int left = 0;
        int right = 0;
        
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) == '('){
                left++;
            } else {
                right++;
            }
        }
        
        return (left==right)? true : false;
    }
    
    public static boolean isRightSameCountStr(String p){
        boolean ret = false;
        Stack<Character> st = new Stack<>();
        if( isSameCountStr(p) == true ){
            for(int i=0; i<p.length(); i++){
                if(st.size() == 0){
                    st.push(p.charAt(i));
                } else {
                    char last = st.peek();    
                    if(p.charAt(i) == ')' && last == '('){
                        st.pop();
                    }else {
                        st.push(p.charAt(i));
                    }           
                }
            }
            if(st.empty()) ret = true;
        }
        return ret;
    }
}
