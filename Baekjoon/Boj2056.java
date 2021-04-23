package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** 
 * Title : 작업
 * desc : 
 *  1번부터 n번까지의 작업이 있고 각 작업이 완료되는데 소요되는 시간이 주어진다. 
 *  일부 작업들은 선행 관계가 있어서 먼저 수행되어야 할 작업이 완료되어야 수행이 가능해진다. 
 *  이 때, 선행 관계를 모두 지키면서 모든 작업이 완수 되기까지 걸리는 최소 시간을 구하시오. 
 *  (선행 작업들이 끝난 작업은 다른 작업 수행 중에 동시에 진행할 수 있다 - 멀티스레드)
 * 
 * Type : 위상정렬 응용
 */

public class Boj2056 {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] inDegree = new int[n+1];
        int[] time = new int[n+1];
        int[] totalTime = new int[n+1];
        List<Integer>[] graph = new List[n+1];

        for(int i=0; i<n+1; i++){
            graph[i] = new ArrayList<Integer>();
        }

        Queue<Job> q = new LinkedList<>();

        for(int i=1; i<=n; i++){
            String[] row = br.readLine().split(" ");

            time[i] = Integer.parseInt(row[0]);
            totalTime[i] = time[i];
            inDegree[i] = Integer.parseInt(row[1]);

            for(int j=2; j<2+inDegree[i]; j++){
                graph[Integer.parseInt(row[j])].add(i);
            }

            if(inDegree[i] == 0){
                q.offer(new Job(i, time[i], time[i]));
            }
        }

        while(!q.isEmpty()){
            Job job = q.poll();
            int jobIndex = job.getIndex();
            int jobTime = job.getTime();
            int jobTotalTime = job.getTotalTime();

            for(int nextJobIndex : graph[jobIndex]){
                inDegree[nextJobIndex] -= 1;
                if (totalTime[nextJobIndex] < jobTotalTime + time[nextJobIndex]) {
                    totalTime[nextJobIndex] = jobTotalTime + time[nextJobIndex];
                }
                if(inDegree[nextJobIndex] == 0){
                    q.offer(new Job(nextJobIndex, time[nextJobIndex], totalTime[nextJobIndex]));
                }
            }
        }
        
        int answer = Integer.MIN_VALUE;
        for(int i=1; i<=n; i++){
            if(answer < totalTime[i]){
                answer = totalTime[i];
            }
        }

        System.out.print(answer);
    }
    
    private static class Job {
        int index;
        int time;
        int totalTime;
        public Job(int index, int time, int totalTime){
            this.index=index; this.time = time; this.totalTime=totalTime;
        }
        public int getIndex(){ return this.index; }
        public int getTime(){ return this.time; }
        public int getTotalTime(){ return this.totalTime; }

    }
}
