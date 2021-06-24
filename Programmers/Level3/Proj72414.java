package Programmers.Level3;
import java.util.*;
/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/72414
 * Title : 광고 삽입 (카카오 2021 블라인드)
 *  부분합 + 구현
 */

public class Proj72414 {

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int play_time_sec = getSecByString(play_time);
        int adv_time_sec = getSecByString(adv_time);
        long[] time_sum = new long[100*60*60]; // time_sum[x] : 0 ~ x+1 초 동안 전체 시청자가 재생한 누적 재생 시간

        getTimeSum(time_sum, logs, play_time_sec);

        answer = getTimeForMax(time_sum, play_time_sec, adv_time_sec);

        return answer;
    }

    public void getTimeSum(long[] time_sum, String[] logs, int play_time_sec){
        for(String log : logs){
            String[] startEnd = log.split("-");
            int log_start = getSecByString(startEnd[0]);
            int log_end = getSecByString(startEnd[1]);

            time_sum[log_start] += 1;
            time_sum[log_end] -= 1;
        }

        for(int i=1; i<play_time_sec; i++){
            time_sum[i] = time_sum[i] + time_sum[i-1];
        }

        for(int i=1; i<play_time_sec; i++){
            time_sum[i] = time_sum[i] + time_sum[i-1];
        }
    }

    public String getTimeForMax(long[] time_sum, int play_time_sec, int adv_time_sec){

        int max_time = 0;
        long max_value = 0;

        for(int i=0; i - 1 + adv_time_sec < play_time_sec; i++){
            long result = (i==0)? time_sum[i - 1 + adv_time_sec] : time_sum[i - 1 + adv_time_sec] - time_sum[i-1];
            if(max_value < result) {
                max_value = result;
                max_time = i;
            }
        }

        return getStringTimeBySec(max_time);
    }

    public int getSecByString(String value){
        int ret = 0;

        String[] hms = value.split(":");
        int hour = Integer.parseInt(hms[0]);
        int minute = Integer.parseInt(hms[1]);
        int sec = Integer.parseInt(hms[2]);

        ret = hour * 3600 + minute * 60 + sec;

        return ret;
    }

    public String getStringTimeBySec(int value){
        int hour = value / 3600;
        int minute = (value / 60) % 60;
        int sec = value % 60;

        String ret_hour = (hour < 10)? "0" + String.valueOf(hour) : String.valueOf(hour);
        String ret_minute = (minute < 10)? "0" + String.valueOf(minute) : String.valueOf(minute);
        String ret_sec = (sec < 10)? "0" + String.valueOf(sec) : String.valueOf(sec);

        return ret_hour + ":" + ret_minute + ":" + ret_sec ;
    }
    
}
