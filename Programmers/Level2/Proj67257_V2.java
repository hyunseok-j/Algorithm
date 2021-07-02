package Programmers.Level2;
import java.util.*;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/67257
 * Title : [2020 카카오 인턴] 수식 최대화
 *  Deque, DFS 이용
 * 
 *  -> 수식의 우선순위 순열 DFS로 구하기
 *  -> 각 우선순위 경우마다 expression 연산 
 *     (우선순위 높은 수식 계산부터 처리 후, 다시 Deque에 넣어 다음 순위의 수식 처리)
 */

public class Proj67257_V2 {
    public long solution(String expression) {
        long answer = 0L;
        char[] op = {'*', '-', '+'};
        
        ArrayList<String> nums = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();
        ArrayList<ArrayList<Integer>> priority = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        boolean[] visited = new boolean[3];
        
        // expression에서 수식들만 분리
        for(char c: expression.toCharArray()){
            if(c == '*' || c == '-' || c == '+')
                operators.add(String.valueOf(c));
        }
        
        // expression에서 숫자들만 분리
        for(String s: expression.split("\\+|\\-|\\*")){
            nums.add(s);
        }
        
        // 수식에 우선순위를 부여했을 때 나올 수 있는 모든 순열 구해서 priority에 저장
        setPriority(priority, visited, temp);
        
        // 각 우선순위 순열(oneCase)마다의 계산결과를 얻은 후, 여태 나온 값 중 가장 큰 값이면 answer 갱신
        for(ArrayList<Integer> oneCase : priority){
            long result = getResult(nums, operators, oneCase, op);
            answer = Math.max(answer, result);
        }
        
        return answer;
    }
    
    public long getResult(ArrayList<String> nums, ArrayList<String> operators, ArrayList<Integer> oneCase, char[] op){
        long ret = 0L;
        int highOpValue = 2;
        char nowOp = '=';
        Deque<Long> nowNums = new LinkedList<>();
        Deque<String> nowOperators = new LinkedList<>();
        
        for(int i=0; i<nums.size(); i++){
            nowNums.addLast(Long.parseLong(nums.get(i)));
        }
        for(int i=0; i<operators.size(); i++){
            nowOperators.addLast(operators.get(i));
        }
        
        while(!nowOperators.isEmpty()){
            // 처리할 수식 결정
            if(!nowOperators.contains(String.valueOf(nowOp))){
                for(int i=0; i<oneCase.size(); i++){
                    if(oneCase.get(i) == highOpValue){
                        nowOp = op[i];
                        highOpValue--;
                        break;
                    }
                    if(i == oneCase.size()-1){
                        if(highOpValue > 0){
                            highOpValue--;
                            i = -1;    
                        }
                    }
                }
            }
            Deque<Long> tempNums = new LinkedList<>();
            Deque<String> tempOps = new LinkedList<>();
            
            tempNums.addLast(nowNums.removeFirst());
            
            while(!nowOperators.isEmpty()){
                long curNum = nowNums.removeFirst();
                char curOp = nowOperators.removeFirst().charAt(0);
                if (curOp == nowOp) {
                    tempNums.addLast(calculate(tempNums.removeLast(), curNum, curOp));
                } else {
                    tempNums.addLast(curNum);
                    tempOps.addLast(String.valueOf(curOp));
                }
            }
            
            while(!tempNums.isEmpty()){
                nowNums.addLast(tempNums.removeFirst());
            }
            while(!tempOps.isEmpty()){
                nowOperators.addLast(tempOps.removeFirst());
            }
        }    
        
        ret = Math.abs(nowNums.removeFirst());
        
        return ret;
    }
    
    public long calculate(long n1, long n2, char op){
        long ret = -1L;
        switch (op) {
            case '*':
                ret = n1 * n2;
                break;
            case '-':
                ret = n1 - n2;
                break;
            case '+':
                ret = n1 + n2;
                break;
        }
        return ret;
    }
    
    public void setPriority(ArrayList<ArrayList<Integer>> priority, boolean[] visited, ArrayList<Integer> temp){
        if(visited.length == temp.size()){
            priority.add(new ArrayList<Integer>());
            for(int priorityValue : temp){
                priority.get(priority.size()-1).add(priorityValue);  
            }
            return;
        }
        
        for(int i=0; i<3; i++){
            if(visited[i] == true) continue;
            visited[i] = true;
            temp.add(i);
            setPriority(priority, visited, temp);
            temp.remove(temp.size()-1);
            visited[i] = false;          
        }
    }
}
