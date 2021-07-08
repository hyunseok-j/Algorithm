package Programmers.Level1;

/**
 * URI : https://programmers.co.kr/learn/courses/30/lessons/17682
 * Title : [카카오 신입 공채 2018] 다트 게임
 *  문자열 처리
 */

public class Proj17682 {

    public int solution(String dartResult) {

        int answer = 0;
        int[] gameScores = new int[3];
        
        int index = 0;
        int shootCount = 0;
        
        while(shootCount < 3){
            int bonusIndex = index + 1;
            int optionIndex = index + 2;
            
            // 점수가 0-9인 경우
            gameScores[shootCount] = Integer.parseInt(String.valueOf(dartResult.charAt(index)));
            
            // 점수가 10인 경우
            if(dartResult.charAt(index) == '1'){
                if(index + 1 < dartResult.length() && dartResult.charAt(index + 1) == '0'){
                    gameScores[shootCount] = Integer.parseInt(dartResult.substring(index, index+2));
                    bonusIndex = index + 2;
                    optionIndex = index + 3;
                }
            }
            
            // 두번째 보너스 문자 (#, *)에 대한 처리
            gameScores[shootCount] = calBonus(gameScores[shootCount],dartResult.charAt(bonusIndex));
            
            // 세번째 옵션 문자 유무 확인과 문자별 점수 처리
            if(optionIndex < dartResult.length()){
                if (dartResult.charAt(optionIndex) == '*') {
                    if(shootCount-1 >= 0){
                        gameScores[shootCount-1] *= 2;
                    }
                    gameScores[shootCount] *= 2;
                    index = optionIndex + 1;
                    
                } else if (dartResult.charAt(optionIndex) == '#'){
                    gameScores[shootCount] = -gameScores[shootCount];
                    index = optionIndex + 1;
                    
                } else {
                    index = optionIndex;
                }
            }
            
            shootCount++;
        }
        
        // 다트 1, 2, 3 게임 점수 합 계산
        for(int score : gameScores){
            answer += score;
        }       
               
        return answer;
    }
    
    public int calBonus(int score, char bonus){
        switch(bonus){
            case 'S':
                return score;
            case 'D':
                return (int)Math.pow(score, 2);
            case 'T':
                return (int)Math.pow(score, 3);
        }
        return -1;
    }
}
