package Algorithm;
import java.util.*;

/*
 * 선택 정렬 
 * 
 * [순서]
 * 1. 정렬 되지 않은 데이터 중 가장 작은 데이터를 선택
 * 2. 맨 앞 데이터와 swap
 * 3. 그 다음 작은 데이터 선택해 두 번째 데이터와 바꾸는 과정
 * 
 * 시간 복잡도 : O(n^2)
 * 
 */
public class SelectionSort {

    public static void selectionSort(int[] array){
        // array의 맨 앞부터 하나씩 확인
        for(int i=0; i<array.length; i++){
            int minIndex = i;
            // 현재 확인하는 index인 i 뒤의 값들 중 가장 작은 데이터 찾기
            for(int j=i+1; j<array.length; j++){
                if(array[minIndex] > array[j]){
                    minIndex = j;
                }
            }

            // i번째 값과 가장 작은 값 swap
            int tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;

        }

    }

}
