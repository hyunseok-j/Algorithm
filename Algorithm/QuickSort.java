package Algorithm;

/**
 * 
 * 퀵 정렬 : 기준 데이터(pivot) 설정, 기준보다 큰 데이터와 작은 데이터를 바꾼다
 * 
 * 분할 방식에 따라 퀵 정렬 분류되므로 
 * 여기서는 호어 분할 방식을 따르는 것을 사용
 * 
 * [순서]
 * 1. 리스트에서 첫번째 데이터를 pivot 으로 선정
 * 2. 리스트의 왼쪽에서부터 pivot 보다 큰 데이터 find
 * 3. 리스트의 오른쪽에서부터 pivot 보다 작은 데이터 find
 * 4. [왼쪽에서 부터 찾은 데이터의 인덱스:left]가 [오른쪽에서부터 찾은 데이터의 인덱스:right]보다 크면
 *    right의 값과 pivot의 값을 swap
 * 5. pivot은 정렬 후 위치를 찾은 것이고 나머지 값들도 마저 정렬하기 위해 
 *    pivot 이외에 값들을 다시 분할해서 위의 과정 실행
 * 
 * 
 * 시간 복잡도 
 *  평균 : O(nlog(n))
 *  최악 : O(n^2)
 * 
 */

public class QuickSort {
    
    public static void swap(int[] array, int index1, int index2){
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public static void quickSort(int[] array, int start, int end){
        if(start >= end){
            return;
        }

        int pivot = start;
        int left = start+1;
        int right = end;

        while(left <= right){
            // pivot보다 큰 값 찾기
            while(left <= end && array[left] <= array[pivot]){
                left += 1;
            }

            // pivot보다 작은 값 찾기
            while(right > start && array[right] >= array[pivot])
            {
                right -= 1;
            }

            // 엇갈린 경우, 작은 데이터와 pivot 데이터 swap
            if(left > right){
                swap(array,right,pivot);
            } // 엇갈리지 않은 경우, 큰 데이터와 작은 데이터 swap
            else{
                swap(array,left,right);
            }

        }

        // 나머지 값들도 정렬
        quickSort(array, start, right-1);
        quickSort(array, right+1, end);

    }




    public static void main(String[] args){
        int[] arr = {4,3,2,4,66,7,8};
        quickSort(arr,0,arr.length-1);

        for(int i : arr){
            System.out.print(i+" ");
        }
    }

}
