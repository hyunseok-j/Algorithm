package Algorithm;

import java.util.ArrayList;

public class MyMergeSort {
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> list, int start, int end){
        ArrayList<Integer> ret = new ArrayList<Integer>();

        if (start == end){
            ret.add(list.get(start));
            return ret;
        } 

        int mid = (start+end)/2;
        ArrayList<Integer> p1 = mergeSort(list, start, mid);
        ArrayList<Integer> p2 = mergeSort(list, mid+1, end);

        int p1Index = 0;
        int p2Index = 0;
        
        while(p1Index < p1.size() && p2Index < p2.size()){
            int p1Value = p1.get(p1Index);
            int p2Value = p2.get(p2Index);

            if (p1Value < p2Value) {
                ret.add(p1Value);
                p1Index++;
            } else if ((p1Value) > p2Value) {
                ret.add(p2Value);
                p2Index++;
            } else {
                ret.add(p1Value);
                ret.add(p2Value);
                p1Index++;
                p2Index++;
            }
        }

        if (p1Index >= p1.size()) {
            for(int i=p2Index; i<p2.size(); i++){
                ret.add(p2.get(i));
            }
        } else if (p2Index >= p2.size()){
            for(int i=p1Index; i<p1.size(); i++){
                ret.add(p1.get(i));
            }
        }

        return ret;
    }

    public static void main(String[] args){
        ArrayList<Integer> sample = new ArrayList<>();
        int[] target = {21, 10, 12, 20, 25, 13, 15, 22};

        for(int i=0; i<target.length; i++){
            sample.add(target[i]);
        }

        sample = mergeSort(sample, 0, sample.size()-1);
        for(int i=0; i<sample.size(); i++){
            System.out.print(sample.get(i)+" ");
        }
        System.out.println();
    }
}
