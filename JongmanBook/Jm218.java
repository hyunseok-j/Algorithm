package JongmanBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jm218 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(br.readLine());

        while(testCaseCount-- > 0){
            String pattern = br.readLine();
            int sampleCount = Integer.parseInt(br.readLine());
            String[] samples = new String[sampleCount];
            int[][] cached = new int[101][101];

            initCache(cached);

            for(int i=0; i<sampleCount; i++){
                samples[i] = br.readLine();
            }

            for(int i=0; i<sampleCount; i++){
                if(isMatchingSampleWithPattern(pattern, samples[i], 0, 0, cached) == 1){
                    System.out.println(samples[i]);
                }
                initCache(cached);
            }
        }

    }

    private static void initCache(int[][] cached){
        for(int i=0; i<101; i++){
            for(int j=0; j<101; j++){
                cached[i][j] = -1;
            }
        }
    }

    private static int isMatchingSampleWithPattern(String pattern, String sample, int posPattern, int posSample, int[][] cached) {
        int ret = cached[posPattern][posSample];
        if(ret != -1){
            return ret;
        }

        while(posPattern < pattern.length() && posSample < sample.length() && 
            (pattern.charAt(posPattern) == sample.charAt(posSample) || pattern.charAt(posPattern) == '?')){
                // posPattern++;
                // posSample++;

                return cached[posPattern][posSample] = isMatchingSampleWithPattern(pattern, sample, posPattern+1, posSample+1, cached);
        }

        if(posPattern == pattern.length()){
            if(posSample == sample.length()){
                return cached[posPattern][posSample] = 1;
            }
            return cached[posPattern][posSample] = 0;
        }

        if(pattern.charAt(posPattern) == '*'){

            for(int skip=0; posSample+skip <= sample.length(); skip++){

                // if(isMatchingSampleWithPattern(pattern, sample, posPattern+1, posSample+skip, cached) == 1){
                //     return cached[posPattern][posSample] = 1;
                // }

                if(isMatchingSampleWithPattern(pattern, sample, posPattern+1, posSample, cached) == 1 || 
                    (posSample < sample.length() && isMatchingSampleWithPattern(pattern, sample, posPattern, posSample+1, cached) == 1)){
                        return cached[posPattern][posSample] = 1;
                }
            }
        }

        return cached[posPattern][posSample] = 0;
    }
}


/* Case 

3
he?p
3
help
heap
helpp
*p*
3
help
papa
hello
*bb*
1
babbbc

*/