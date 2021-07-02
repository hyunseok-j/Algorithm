package Programmers.Level2;
import java.util.*;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/67257
 * Title : [2020 카카오 인턴] 수식 최대화
 *  Stack, DFS, Collections.reverse() 이용
 */

public class Proj67257_V1 {
    public long solution(String expression) {
        long answer = 0;
        String[] priorityForOperation = {"+","-","*"};
        ArrayList<String> priority = new ArrayList<>(); 
        boolean[] used = new boolean[3];
        
        answer = dfs(used, priorityForOperation,expression, 0, 3, priority);
        
        return answer;
    }
    
    public long dfs(boolean[] used, String[] priorityForOperation, String expression, int count, int total, ArrayList<String> priority){
        
        long result = 0L;
        if(count == total) {
            String[] stepPriority = new String[priority.size()];
            for(int i=0; i<stepPriority.length; i++){
                stepPriority[i] = priority.get(i);
            }
            result = calculateExpression(expression, stepPriority);
            return result;
        }
        
        for(int i=0; i<3; i++){
            if(used[i] == true) continue;
            
            used[i] = true;
            priority.add(priorityForOperation[i]);
            
            result = Math.max(result,dfs(used, priorityForOperation, expression, count+1, total, priority));
            
            priority.remove(priorityForOperation[i]);
            used[i] = false;
        }
        
        return result;
    }
    
    public long calculateExpression(String expression, String[] priorityForOperation){
        long result = 0;
        
        ArrayList<String> parsedExpression = parseExpression(expression);
        
        for(int i=0; i<3; i++){
            parsedExpression = calculateOneOperator(parsedExpression, priorityForOperation[i]);
        }
        
        result = Long.parseLong(parsedExpression.get(0));
        
        return Math.abs(result);
    }
    
    public ArrayList<String> calculateOneOperator(ArrayList<String> parsedExpression, 
                                                  String oper) {
        ArrayList<String> result = new ArrayList<>();
        Stack<String> numbers = new Stack<>();
        Stack<String> operator = new Stack<>();
        
        for(int i=0; i<parsedExpression.size(); i++){
                if(parsedExpression.get(i).equals(oper)){
                    long left = Long.parseLong(numbers.pop());
                    long right = Long.parseLong(parsedExpression.get(i+1));
                    String operatorStr = oper;
                    long tempResult = operateNumber(left, right, operatorStr);
                    
                    numbers.push(String.valueOf(tempResult));
                    i += 1;
                } else {
                    if(parsedExpression.get(i).length() == 1 
                       && isOperator(parsedExpression.get(i).charAt(0))){
                        operator.push(parsedExpression.get(i));  
                    } else {
                        numbers.push(parsedExpression.get(i));
                    }
                }   
        }
        
        for(int i=numbers.size()-1; i>=0; i--){
            result.add(numbers.pop());
            if(!operator.empty()){
                result.add(operator.pop());   
            }
        }
        
        Collections.reverse(result);
        
        return result;
    }
    
    public long operateNumber(long left, long right, String operator){
        long result = 0L;
        if(operator.equals("+")){
            result = left + right;
        } else if (operator.equals("-")){
            result = left - right;
        } else if (operator.equals("*")){
            result = left * right;
        }
        return result;
    }
    
    public ArrayList<String> parseExpression(String expression){
        ArrayList<String> result = new ArrayList<>();
        int numberStartIndex = 0;
        for(int i=0; i<expression.length(); i++){
            if(isOperator(expression.charAt(i))){
                result.add(expression.substring(numberStartIndex,i));
                result.add(String.valueOf(expression.charAt(i)));
                numberStartIndex = i+1;
            }
            if(i==expression.length()-1) {
                result.add(expression.substring(numberStartIndex,expression.length()));  
            } 
        }
        return result;
    }
    
    public boolean isOperator(char c){
        boolean result = false;
        if(c == '+' || c == '-' || c == '*'){
            result = true;
        }
        return result;
    }
}
