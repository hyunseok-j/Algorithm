package Algorithm;
import java.util.*;

/**
 * 삽입 정렬 : 데이터를 하나씩 확인하며 각 데이터를 적절한 위치에 삽입하는 정렬
 * 
 * [순서]
 * 
 * 1. 맨 앞의 값은 정렬이 되어 있다고 가정
 * 2. 두 번째 값부터 끝 값까지 확인
 * 3. j번째 값을 확인할 때, j-1번째 값과 값을 비교해서 i번째가 작으면 i와 i-1의 값을 swap  (오름차순 기준)
 * 4. j-1과 j-2의 값을 비교해 j-1이 작으면 위와 같이 swap
 * 5. 0번째 까지 값을 위의 과정 반복하며 비교
 * 6. 3,4,5 과정이 끝나면 한 값에 대한 정렬이 완료됨
 * 7. 모든 값에 대한 정렬이 완료될 때까지 반복
 * 
 * 시간 복잡도 : O(n^2)
 * 
 */


public class InsertionSort {

    public static void swap(int[] array, int index1, int index2){
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
    
    // 오름차순 삽입 정렬
    public static void insertSort(int[] array){

        // array[0]은 이미 정렬이 됐다고 가정하고 array[1]부터 정렬 시작
        for(int i=1; i<array.length; i++){

            // 현재 확인하는 값보다 앞에 위치하는 값들을 확인하며 array[i]의 위치를 결정
            for(int j=i; j>0; j--){
                if(array[j] < array[j-1]){
                    swap(array, j, j-1);
                }
            }

        }


    }

    public static void main(String[] args){
        int[] arr = {4,3,2,4,66,7,8};
        insertSort(arr);

        for(int i : arr){
            System.out.print(i+" ");
        }
    }

}
