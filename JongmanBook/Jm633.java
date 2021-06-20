package JongmanBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Title : 짝이 맞지 않는 괄호
 *  Stack
 */
public class Jm633 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            String input = br.readLine();
            String answer = "YES";

            if(isCorrect(input) == false) answer = "NO";

            System.out.println(answer);

        }

    }

    private static boolean isCorrect(String input) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<input.length(); i++){
            
            if (stack.empty()) {
                if (isCloseChar(input.charAt(i))) return false; 
                stack.add(input.charAt(i));
                continue;
            }
            
            char target = stack.peek();
            if(isCloseChar(target)) return false;

            if(isCloseChar(input.charAt(i))) {
                if(isSameChar(target, input.charAt(i)) == false) return false;
                stack.pop();
            } else {
                stack.add(input.charAt(i));
            }
        }

        if(stack.empty() == false) return false;

        return true;
    }

    private static boolean isCloseChar(char c) {
        if(c == ')' || c == '}' || c == ']') return true;
        return false;
    }

    private static boolean isSameChar(char c1, char c2){
        boolean ret = false;

        if ((c1 == ')' && c2 == '(') || (c1 == '(' && c2 == ')')) {
            ret = true;
        }

        if ((c1 == ']' && c2 == '[') || (c1 == '[' && c2 == ']')) {
            ret = true;
        }

        if ((c1 == '}' && c2 == '{') || (c1 == '{' && c2 == '}')) {
            ret = true;
        }

        return ret;
    }

}
