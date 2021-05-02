package Programmers.Level2;

public class Proj42883 {
    static StringBuilder sb= new StringBuilder();
    public static void main(String[] args){
        String number = "111";
        int k = 2;
        System.out.println(solution(number, k));
    }
    

    public static String solution(String number, int k) {
        
        int start = 0;
        int end = k;
        
        cal(number, start, end, k, number.length()-k, 0);

        String answer = sb.toString();
        
        return answer;
    }
    
    public static void cal(String number, int start, int end, int deleteCount, int total, int selected){
        if(deleteCount == 0){
            sb.append(number);
            return;
            
        } else if (selected == total){
            return; 
        }
        
        int maxIndex = 0;
        int max = (int)(number.charAt(0) - '0');
        for(int i=start; i<=end; i++){
            int now = (int)(number.charAt(i) - '0');
            if(max < now){ 
                max = now;
                maxIndex = i;   
            }
        }
        
        String result = number.substring(maxIndex+1, number.length());

        sb.append(number.charAt(maxIndex));
        
        cal(result, start, start+deleteCount-(maxIndex-start) , deleteCount-(maxIndex-start), 
            total, selected+1);
        
    }
}
