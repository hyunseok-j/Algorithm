package Programmers.Level1;
import java.util.*;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/42889
 * Title : [2019 카카오 블라인드 공채] 실패율
 *  단순 구현 문제 -> 정렬 함수 커스텀만 할 줄 알면 쉽게 풀이 가능 : compareTo
 */

public class Proj42889 {

    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        
        int[] userNotClear = new int[N+1];
        int[] userArriving = new int[N+1];
        ArrayList<FailInfo> failInfoList = new ArrayList<>();
        
        for(int stoppedStageNum : stages){
            if(stoppedStageNum != N+1){
                userNotClear[stoppedStageNum] += 1;
                for(int i=1; i <= stoppedStageNum; i++){
                    userArriving[i] += 1;    
                }
                
            } else {
                for(int i=1; i<= stoppedStageNum - 1; i++){
                    userArriving[i] += 1;
                }
            }
        }
        
        for(int stageNum=1; stageNum<N+1; stageNum++){
            double failRate = (userArriving[stageNum] == 0)? 0 : (double)userNotClear[stageNum]/userArriving[stageNum];
            failInfoList.add(new FailInfo(failRate, stageNum));
        }
        
        Collections.sort(failInfoList);
        
        answer = new int[failInfoList.size()];
        
        for(int i=0; i<failInfoList.size(); i++){
            answer[i] = failInfoList.get(i).getStageNum();
        }
        
        return answer;
    }
    
    class FailInfo implements Comparable<FailInfo> {
        private double failRate;
        private int stageNum;

        public FailInfo(double failRate, int stageNum){
            this.failRate = failRate;
            this.stageNum = stageNum;
        }
        
        public double getFailRate() { return this.failRate; }
        public int getStageNum() { return this.stageNum; }
        
        public int compareTo(FailInfo other){
            if(this.failRate > other.failRate){
                return -1;
            }
            if(this.failRate < other.failRate){
                return 1;
            }
            return this.stageNum - other.stageNum;
        }
        
    }
}
