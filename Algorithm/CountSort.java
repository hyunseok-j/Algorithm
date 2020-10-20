package Algorithm;
import java.util.*;


/**
 * 계수정렬 : 각 값이 몇 번 나왔는지 저장해뒀다가 작은 값부터 나온 횟수만큼 출력하는 형태
 * 
 * 조건 
 *  - 나오는 데이터의 수가 제한되어 있어야 함
 *  - 데이터의 수가 너무 많으면 메모리 문제로 사용 X
 * 
 * 시간 복잡도 : 데이터 개수 N, 최대값 K 이면 O(N+K)를 보장
 *  가장 큰 데이터와 작은 데이터의 차이가 1,000,000 넘지 않을 때 유용 
 *  아니면 N+K 보다 더 지연될 수 있음
 * 
 */
public class CountSort {

    // 횟수를 저장할 배열 생성(데이터로 나올 수 있는 최대값을 길이로 지정)
    // 데이터로 나올 수 있는 최대값(K)을 길이로 지정
    // array[index]는 index라는 값이 몇 회 나옴을 의미
    
    public static ArrayList<Integer> countSort(int[] arr, int K){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int[] count = new int[K+1];

        for(int i=0; i<arr.length; i++){
            count[arr[i]] += 1;
        }

        for(int i=0; i<count.length; i++){
            for(int j=0;j<count[i];j++){
                result.add(i);
            }
        }

        return result;
    }
        

    public static void main(String[] args){
        int[] arr = {4,3,2,4,66,7,8};
        ArrayList<Integer> sortedArr = countSort(arr,66);

        for(int i : sortedArr){
            System.out.print(i+" ");

        }
    }

}
