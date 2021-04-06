package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.InputStreamReader;

/**
 * Title : 톱니바퀴 2
 * desc : 
 *  8개의 톱니를 가지고 있는 톱니바퀴가 T개 일렬로 놓여져 있다. 
 *  톱니는 각각 N, S극을 가지고 있으며 톱니바퀴를 시계 방향 또는 반시계 방향으로 돌리는 행위를 
 *  입력받은 순서대로 수행했을 때 결과를 알아내려고 한다.
 *  맞물리는 톱니의 극이 서로 같으면 한 톱니바퀴가 회전해도 이웃한 톱니바퀴는 영향을 받지 않는다.
 *  반대로 톱니의 극이 서로 다르면 한 톱니바퀴가 시계 방향으로 회전하면 이웃한 톱니바퀴는 반대 방향인 반시계 방향으로 회전한다.
 * 
 * Type : 시뮬레이션
 *  i번째 톱니 바퀴를 어디 방향으로 회전하라는 명령 1개를 처리할 때 
 *  i의 좌우로 인접한 톱니 바퀴를 한 개씩 조사하는 동시에 톱니 바퀴 회전을 처리했다.
 * 
 *  다른 풀이로 i의 좌우로 인접한 톱니 바퀴를 한 개씩 조사해가며 회전하기 위해 필요한 값인 
 *   1. 각 톱니바퀴의 회전 방향을 전부 알아낸 다음 
 *   2. 얻은 각 톱니바퀴의 회전 방향을 모든 톱니바퀴에 적용하는 식으로 단계를 나누어 처리를 할 수 있다. 
 *  이렇게 조건 판단에 필요한 값만 얻는 단계와 얻은 값으로 조건 판단해 행위 결과를 알아내는 단계로 구분하면
 *  구현이 보다 단순해지기 때문에(예외를 찾기 쉬워짐) 
 *  탐색과 동시에 처리하는 것이 시간적인 이득이 없다면 2번째 풀이처럼 단계를 분리하게 구현하도록 하는게 좋을 것 같다.
 */

public class Boj15662 {

    static class Command{
        private int cogWheelIndex;
        private int rotationState;

        public Command(int cogWheelIndex, int rotationState){
            this.cogWheelIndex = cogWheelIndex;
            this.rotationState = rotationState;
        }

        public int getCogWheelIndex(){
            return cogWheelIndex;
        }
        public int getRotationState(){
            return rotationState;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        int[][] cogWheel;
        int k;
        ArrayList<Command> commands = new ArrayList<>();

        t = Integer.parseInt(br.readLine());

        cogWheel = new int[t][8];
        for(int i=0; i<t; i++){
            char[] one = br.readLine().toCharArray();
            for(int j=0; j<8; j++){
                cogWheel[i][j] = (int)(one[j]-'0');
            }
        }

        k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++){
            String[] input = br.readLine().split(" ");
            int cogWheelIndex = Integer.parseInt(input[0])-1;
            int rotationState = Integer.parseInt(input[1]);
            commands.add(new Command(cogWheelIndex, rotationState));
        }


        for(int i=0; i<commands.size(); i++){
            Command cmd = commands.get(i);
            cogWheel = runRotate(cmd, cogWheel);
        }

        int answer = calculatePoleCount(0, 1, cogWheel);
        System.out.println(answer);

        
    }    

    static int[][] runRotate(Command cmd, int[][] cogWheel){
        int cogWheelIndex = cmd.getCogWheelIndex();
        int rotationState = cmd.getRotationState();
        int left = 6;
        int right = 2;
        int[][] result = new int[cogWheel.length][8];

        for(int i=0; i<cogWheel.length; i++){
            for(int j=0; j<cogWheel[i].length; j++){
                result[i][j] = cogWheel[i][j];
            }
        }

        int nowIndex1 = cogWheelIndex;
        int nowRotationState1 = rotationState;
        int nextLeftIndex = nowIndex1 - 1;

        while(nextLeftIndex >= 0){
            if(nowIndex1!=cogWheelIndex) {
                changeCogWheel(new Command(nowIndex1,nowRotationState1), cogWheel, result);
            }
            if(cogWheel[nowIndex1][left] != cogWheel[nextLeftIndex][right]){
                nowIndex1 = nextLeftIndex;
                nextLeftIndex = nextLeftIndex - 1;
                nowRotationState1 = -nowRotationState1;
                if(nowIndex1 == 0) {
                    changeCogWheel(new Command(nowIndex1,nowRotationState1), cogWheel, result);
                }
            } else {
                break;
            }
        }

        int nowIndex2 = cogWheelIndex;
        int nowRotationState2 = rotationState;
        int nextRightIndex = nowIndex2 + 1;

        while(nextRightIndex < cogWheel.length){
            changeCogWheel(new Command(nowIndex2,nowRotationState2), cogWheel, result);

            if(cogWheel[nowIndex2][right] != cogWheel[nextRightIndex][left]){
                nowIndex2 = nextRightIndex;
                nextRightIndex = nextRightIndex + 1;
                nowRotationState2 = -nowRotationState2;
                if(nowIndex2 == cogWheel.length-1) {
                    changeCogWheel(new Command(nowIndex2, nowRotationState2), cogWheel, result);
                }
            } else {
                break;
            }
        }
        if(nowIndex2 == cogWheel.length-1 && nowIndex2 == cogWheelIndex){
            changeCogWheel(new Command(nowIndex2, nowRotationState2), cogWheel, result);
        }

        return result;
    }

    static int calculatePoleCount(int location, int pole, int[][] cogWheel){
        int count = 0;
        for(int i=0; i<cogWheel.length; i++){
            if(cogWheel[i][location] == pole){
                count++;
            }
        }
        return count;
    }

    static void changeCogWheel(Command cmd,int[][] cogWheel, int[][] out){
        int index = cmd.getCogWheelIndex();
        int rotation = cmd.getRotationState();

        if(rotation == 1){
            int lastVal = cogWheel[index][7];
            for(int i=cogWheel[index].length-1; i>0; i--){
                out[index][i] = cogWheel[index][i-1];
            }
            out[index][0] = lastVal;

        } else if (rotation == -1) {
            int firstVal = cogWheel[index][0];
            for(int i=0; i<cogWheel[index].length-1; i++){
                out[index][i] = cogWheel[index][i+1];
            }
            out[index][7] =firstVal;
        }
    }
}
