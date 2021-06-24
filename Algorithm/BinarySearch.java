package Algorithm;

/**
 * 이진 탐색 : 반으로 쪼개면서 탐색하기
 *  탐색 범위를 절반씩 좁혀가며 데이터 탐색
 *  찾으려는 데이터와 중간점 위치에 있는 데이터를 반복적으로 비교해서 원하는 데이터를 찾음
 * 
 * [조건]
 *  미리 정렬이 되어 있어야 됨
 * 
 * 시간 복잡도: O(logN)
 */

public class BinarySearch {

    public static int binarySearch(int[] search_target, int value){
        
        int begin = 0;
        int end = search_target.length-1;

        while (begin <= end) {

           int mid = (begin + end) / 2; 

           if(search_target[mid] == value){
               return mid;
           }
           else if(search_target[mid] > value){
               end = mid-1;
           }
           else{
               begin = mid+1;
           }
           
        }

        return -1;

    }
}
