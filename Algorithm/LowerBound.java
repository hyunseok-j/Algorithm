package Algorithm;

/**
 * lowerBound : 특정 값 value 보다 최초로 크거나 같은 index 위치 탐색
 *              조건 'value <= search_target[i]'를 만족하는 최초 index 탐색
 */

public class LowerBound {

    public int lowerBound(int[] search_target, int value){
        int begin = 0;
        int end = search_target.length;

        while (begin < end) {
            int mid = (begin + end) / 2;

            if (search_target[mid] < value) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }

        return end;
    }
    
}
