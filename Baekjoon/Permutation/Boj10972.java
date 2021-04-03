package Baekjoon.Permutation;

import java.util.Scanner;

/**
 * Title : 다음 순열
 * desc : 
 *  1~n까지의 수로 이루어진 순열이 있다. 이 때, 사전순으로 다음에 오는 순열을 구하시오.
 *  ex) {1, 2, 3} -> {1, 3, 2}
 */

public class Boj10972 {
    
    static int[] current; // 순열
    
    // next : 사전순으로 다음에 오는 순열 결정
    // {7,4,5,9,8,2} -> {7,4,8,9,5,2} -> {7,4,8,2,5,9}
    // 위의 예시에서 (2,8,9)로 만들 수 있는 수 중 가장 큰 (9,8,2)를 만들었기 때문에 더 이상 새로운 순서를 못 만들기 때문에 
    // 5를 (2,8,9) 중 자신보단 큰 수를 골라 그 중 가장 작은 수인 8과 바꾸어야 한다. 
    // 그 후, 8 뒤의 수들은 남아 있는 수(2,5,9)로 만들 수 있는 수 중 가장 작은 수를 만들면 다음 순열이 완성된다.  
    // 위의 과정을 보면 가장 큰 수(9,8,2)는 감소수열을 이루고 있음을 알 수 있고, 
    // 감소수열이 시작되기 전 지점(5)를 감소수열을 이루는 수들 중 자신보다 크고 감소수열 중에서는 작은 수(8)과 교환하는 형식임을 알 수 있다.
    // 교환이 일어난 후, 감소수열을 이루는 수들의 순서를 뒤집으면 뒤의 수를 가장 작은 수로 만들 수 있음을 알 수 있다. 

    static boolean next(int n){
        int pointI = -1; // 감소수열 시작 index
        int pointJ = -1; // 감소수열 시작 전 지점(pointI-1)과 자리를 바꿀 index
        boolean result = false;
        
        // 순열의 오른쪽부터 감소수열이 시작 index를 탐색
        for(int i=n-1; i>0; i--){
            if(current[i-1] < current[i]){
                pointI = i;
                result = true;
                break;
            } 
        }

        // 모든 수가 감소수열 형태를 이루는 경우는 
        // 사전순 마지막 순열(3,2,1)이므로 다음 순열이 존재하지 않으므로 false
        if(pointI == -1) return false;
       
        // 감소수열 구성하는 수들 중 감소수열 시작 전 지점과 위치 바꿀 index 결정
        for(int i=n-1; i>0; i--){
            if(current[pointI-1] < current[i]){
                pointJ = i;
                break;
            }
        }
        
        // 
        swap(pointI-1, pointJ);
        
        // 감소수열을 뒤집어 남은 수들로 가장 작은 수를 구성 
        int start = pointI;
        for(int i=0; start+i < n-1-i; i++){
            swap(start+i, n-1-i);
        }
        
        return result;
    }
    
    // swap : 순열 current에서 index1번째 값과 index2번째 값을 서로 교차하여 저장
    static void swap(int index1, int index2){
        int tmp = current[index1];
        current[index1] = current[index2];
        current[index2] = tmp;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        current = new int[n];
        for(int i=0; i<n; i++){
            current[i] = sc.nextInt();
        }
        
        boolean ok = next(n);
        
        // 다음 순열이 존재할 경우
        if(ok){
            for(int item : current){
                System.out.print(item + " ");
            }
        }
        // 현재 순열이 사전순으로 마지막 순열이어서 다음 순열이 존재할 수 없는 경우
        else{
            System.out.print("-1");
        }
    }    
}
