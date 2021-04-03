package Baekjoon.Permutation;

import java.util.*;

/**
 * Title : 로또
 * desc : 
 *  로또 번호를 6개의 수로 구성된다. 로또 번호를 고를 전략으로 {1,2,3..,49} 중 k개를 먼저 고르고 k개 중에 6개 번호를 선택한다고 한다.
 *  k개의 번호가 주어질 때, 만들 수 있는 번호 조합을 모두 사전순으로 출력하시오.
 */

public class Boj6603 {
    // next_permutgetion 배열 a의 다음 순열 결정
    static boolean next_permutation(int[] a) {

        // a[i-1] < a[i] 인 i 찾기
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1;
        }

        // i가 존재하지 않으면 더 이상 다른 순열이 존재 불가
        if (i <= 0) {
            return false;
        }

        // a[i-1] < a[j] 인 j 찾기
        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }

        // swap(i-1,j)
        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        // a의 i부터 n-1까지의 값을 뒤집기
        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;

            int[] a = new int[n];
            for (int i=0; i<n; i++) {
                a[i] = sc.nextInt();
            }
            
            // d[i] : a의 i번째 수의 사용 여부
            int[] d = new int[n];
            for (int i=0; i<n; i++) {
                if (i < n-6) d[i] = 0; // a의 i번째 수를 사용하지 않은 경우는 0
                else d[i] = 1; // a의 i번째 수를 사용한 경우는 1
            }

            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

            // d 내의 값의 순서를 바꿔보며 선택했을 조합을 구해 cur에 추가
            do {
                ArrayList<Integer> cur = new ArrayList<>();
                for (int i=0; i<n; i++) {
                    if (d[i] == 1) {
                        cur.add(a[i]);
                   }
                }
                ans.add(cur);
            } while (next_permutation(d));

            // 답으로 가능한 순열들을 사전순으로 정렬
            Collections.sort(ans, new Comparator<ArrayList<Integer>>() {
                public int compare(ArrayList<Integer> l1, ArrayList<Integer> l2) {
                    int n = l1.size();
                    int m = l2.size();
                    int i = 0;
                    // l1과 l2의 i번째 item을 기준으로 오름차순
                    while (i < n && i < m) {
                        int t1 = l1.get(i);
                        int t2 = l2.get(i);
                        if (t1 < t2) return -1;
                        else if (t1 > t2) return 1;
                        i += 1;
                    }
                    
                    // item 값으로 순서를 정하지 못하면 길이가 짧은 순서로 정렬
                    if (i == n && i != m) return -1;
                    else if (i != n && i == m) return 1;
                    return 0;
                }
            });
        
            // 사전순으로 정렬된 순열 순차적으로 출력
            for (ArrayList<Integer> v : ans) {
                for (int x : v) {
                    System.out.print(x + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }   
}