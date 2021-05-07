package Programmers.Level2;

import java.util.*;

/**
 * Title : 순위 검색
 * URI : https://programmers.co.kr/learn/courses/30/lessons/72412
 * 
 * Type : lowerBound
 */

public class Proj72412 {

    public static void main(String[] args){
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        int[] answer = solution(info, query);

        for(int num : answer){
            System.out.println(num);
        }
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        HashMap<Integer, List<Integer>> category = new HashMap<>();
        
        for(int i=0; i<3; i++){
            for(int j=0; j<2; j++){
                for(int k=0; k<2; k++){
                    for(int l=0; l<2; l++){
                        category.put(i*2*2*2 + j*2*2 + k*2 + l,new ArrayList<Integer>()); 
                    }
                }
            }
        }
        
        for(int i=0; i<info.length; i++){
            String[] infoDetail = info[i].split(" ");
            int[] indexs = {0,0,0,0};
            
            decideIndex(infoDetail, indexs);
            int key = indexs[0]*2*2*2 + indexs[1]*2*2 + indexs[2]*2 + indexs[3];

            List<Integer> t = category.get(key);
            t.add(Integer.parseInt(infoDetail[4]));
            category.put(key,t);
        }
        
        for(int i=0; i<3; i++){
            for(int j=0; j<2; j++){
                for(int k=0; k<2; k++){
                    for(int l=0; l<2; l++){

                        Collections.sort(category.get(i*2*2*2 + j*2*2 + k*2 + l));

                    }
                }
            }
        }
        
        for(int i=0; i<query.length; i++){            
            String[] queryDetail = query[i].split(" ");
            String[] temp = {queryDetail[0],queryDetail[2],queryDetail[4],queryDetail[6]};
            int[] indexs = {3,2,2,2};
            
            decideIndex(temp, indexs);

            int count = 0;
            int xStart = indexs[0]==3? 0 : indexs[0];
            int xEnd = indexs[0]==3? 2: indexs[0];

            int jStart = indexs[1]==2? 0: indexs[1];
            int jEnd = indexs[1]==2? 1: indexs[1];

            int kStart = indexs[2]==2? 0: indexs[2];
            int kEnd = indexs[2]==2? 1:indexs[2];

            int lStart = indexs[3]==2? 0: indexs[3];
            int lEnd = indexs[3]==2? 1: indexs[3];

            for(int x=xStart; x<=xEnd; x++){
                for(int j=jStart; j<=jEnd; j++){
                    for(int k=kStart; k<=kEnd; k++){
                        for(int l=lStart; l<=lEnd; l++){

                            int key = x*2*2*2 + j*2*2 + k*2 + l;

                            count += category.get(key).size()
                                - lowerBound(category.get(key), Integer.parseInt(queryDetail[7]));
                        }
                    }
                }
            }
            
            answer[i] = count;
        }
        
        return answer;
    }
    
    public static int lowerBound(List<Integer> list, int num){
        int left = 0;
        int right = list.size()-1;
        
        if (list.size() == 0) return 0;
        if (num > list.get(list.size()-1)) return list.size();

        while(left<right){
            int mid = (left+right)/2;

            if(list.get(mid) >= num){
                right = mid;
            } else {
                left = mid+1;
            }
        }

        return right;
    }
    
    public static void decideIndex(String[] infoDetail, int[] indexs){
        if (infoDetail[0].equals("cpp")) {
            indexs[0] = 0;
        } else if(infoDetail[0].equals("java")) {
            indexs[0] = 1;
        } else if(infoDetail[0].equals("python")) {
            indexs[0] = 2;
        }

        if (infoDetail[1].equals("backend")) {
            indexs[1] = 0;
        } else if(infoDetail[1].equals("frontend")) {
            indexs[1] = 1;
        }

        if (infoDetail[2].equals("junior")) {
            indexs[2] = 0;
        } else if(infoDetail[2].equals("senior")) {
            indexs[2] = 1;
        }

        if (infoDetail[3].equals("chicken")) {
            indexs[3] = 0;
        } else if(infoDetail[3].equals("pizza")) {
            indexs[3] = 1;
        }
    }
}
