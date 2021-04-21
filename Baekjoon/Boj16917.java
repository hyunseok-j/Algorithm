package Baekjoon;

import java.util.Scanner;

/**
 * Title : 양념 반 후라이드 반
 * desc : 
 *  양념 1마리, 후라이드 1마리, 반반 1개 구매 비용이 주어지고
 *  최소 양념 x마리, 후라이드 y마리를 구매하는데 필요한 최소 비용을 구하시오.
 * 
 * Type : 브루트 포스
 *  단순히 i, j, k로 양념, 후라이드, 반반을 구매한 것을 삼중 for문으로 돌리면 n의 크기가 10^5 이므로 시간 초과가 난다.
 *  그래서 묶어서 한번에 처리할 수 있는 부분이 없을까 고민하다가 마리수 기준으로 구간을 나눠서 처리했다.
 *  만약 최소 구매양이 x >= y일 때, 
 *   양념 y마리, 후라이드 y마리를 구매하기 위해 
 *     1-1) 반반없이 양념으로 y마리, 후라이드로 y마리 구매하는 방법과 
 *     1-2) 반반상자를 1-1)의 방법에서의 양념과 후라이드 1마리와 대체하는 방법이 있다.
 *   최소양을 만족시키기 위해 남은 양념 x-y마리를 구매하기 위해서
 *     2-1) 반반없이 양념으로 x-y마리를 구매하는 방법과 
 *     2-2) 반반으로 2-1)의 방법에서 후라이드가 있다고 가정하고 양념, 후라이드 1마리와 대체하는 방법이 있다. 
 * 
 *   따라서 시간복잡도는 O(max(x,y)) 이므로 제한 시간 내에 처리가 가능하다. 
 */

public class Boj16917 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int price1 = sc.nextInt(); // 양념 가격
        int price2 = sc.nextInt(); // 후라이드 가격
        int price3 = sc.nextInt(); // 반반 가격

        int x = sc.nextInt();
        int y = sc.nextInt();

        int minCost = 0;
        if (x >= y) {
            // 양념과 후라이드 구매한 마리 수가 각각 0 ~ y 마리까지의 최소 지불 비용
            int cost1 = price1 * y + price2 * y;
            int minCost1 = cost1;
            for(int k = 0; k/2 <= y; k+=2){
                cost1 = price1 * (y- k/2) + price2 * (y - k/2) + price3 * k;
                if(minCost1 > cost1) minCost1 = cost1;
            }

            // 양념과 후라이드 구매한 마리 수가 각각 y+1 ~ x 마리까지의 최소 지불 비용
            int cost2 = price1 * (x-y);
            int minCost2 = cost2;
            for(int k=0; k/2 <= (x-y); k+= 2){
                cost2 = price1 * ((x-y) - k/2) + price3 * k;
                if(minCost2 > cost2) minCost2 = cost2;
            }
            minCost = minCost1 + minCost2;
        } else {
            int cost1 = price1 * x + price2 * x;
            int minCost1 = cost1;
            for(int k = 0; k/2 <= x; k+=2){
                cost1 = price1 * (x- k/2) + price2 * (x - k/2) + price3 * k;
                if(minCost1 > cost1) minCost1 = cost1;
            }

            int cost2 = price2 * (y-x);
            int minCost2 = cost2;
            for(int k=0; k/2 <= (y-x); k+= 2){
                cost2 = price2 * ((y-x) - k/2) + price3 * k;
                if(minCost2 > cost2) minCost2 = cost2;
            }
            minCost = minCost1 + minCost2;

        }
 
        System.out.print(minCost);
    }
    
}
