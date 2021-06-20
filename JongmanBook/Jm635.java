package JongmanBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Title : 외계 신호 분석
 *  Queue, 선형 합성 난수 생성기 
 */

public class Jm635 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            
            String[] input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);

            int answer = countSubSequence(k, n);

            System.out.println(answer);

        }

    }
    
    private static int countSubSequence(int k, int n) {

        int ret = 0;
        long rangeSum = 0;
        LCG lcg = new LCG();
        Queue<Long> q = new LinkedList<Long>();

        for(int i=0; i<n; i++){
   
            long newSignal = lcg.next();
            rangeSum += newSignal;
            q.offer(newSignal);

            while (rangeSum > k) {
                rangeSum -= q.poll();
            }

            if(rangeSum == k) ret++;
        }

        return ret;
    }

    private static class LCG {
        private long seed;
        
        private LCG(){
            this.seed = 1983;
        }

        private long next(){
            long ret = this.seed;
            this.seed = (this.seed * 214013 + 2531011) % (long)Math.pow(2,32);
            return ret % 10000 + 1;
        }
    }
    
}

/**
 *[ex_input]
 * 3
 * 8791 20
 * 5265 5000
 * 3578452 5000000
 * 
 *[result]
 * 1
 * 4
 * 1049
 */