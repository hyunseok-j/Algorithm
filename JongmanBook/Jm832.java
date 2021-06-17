package JongmanBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 그래프의 indegree = 0 인 정점만 dfs로 탐색해서 알파벳순으로 dfsAll에서 접근할 때, 
 * o -> g -> k -> l-> h 인 그래프여도 
 * 알파벳 순서상 먼저인 정점 g로 dfs를 수행하지 못하게 만들어 정점 o로 dfs 수행한 경우만 동작하게 해 중복으로 방문하지 못하게 했다. 
 * 
 * 일반적인 dfs의 경우는 중복 방문을 막기 위해 visited[i] = false 인 경우만 정점 방문이 가능토록 하면 되지만,
 * 사이클이 존재하는 그래프인지 확인하기 위해 'visited[i] >= 2' 조건을 만족하는지 방문 수를 누적해서 확인해야 하므로 
 * 일반적인 경우처럼 구현하지 않고 상단의 방법으로 구현했다. 
 * 
 * 그런데 indegree = 0인 정점만 방문토록 구현하니 
 *   ->
 * a    b  
 *   <-
 * 위와 같은 그래프에서는 dfs자체를 수행하지 못해, visited[i]가 다 0이 나와서 사이클 존재여부를 판단할 수 없었다. 
 * 좀 더 다른 방법을 강구해보아야 할 것 같다. 
 */

public class Jm832 {

    private static char[] numToAlpha = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            int wordsCount = Integer.parseInt(br.readLine());
            String[] words = new String[wordsCount];
            int maxLenOfWord = -1;

            for(int i=0; i<wordsCount; i++){
                words[i] = br.readLine();
                maxLenOfWord = (maxLenOfWord < words[i].length()) ? words[i].length() : maxLenOfWord;
            }

            System.out.println(getCharOrder(words, maxLenOfWord));
        }

    }

    private static String getCharOrder(String[] words, int maxLenOfWord) {
        int[][] graph = createGraph(words, maxLenOfWord);
        int[] visited = new int[26];
        String result = "";

        if(isDAG(graph)) {
            result = String.valueOf(dfsAll(graph, visited));
        } else {
            result = "INVALID HYPOTHESIS";
        }

        return result;
    }

    private static boolean isDAG(int[][] graph) {
        int[] visited = new int[26];
        dfsAll(graph, visited);

        for(int i=0; i<visited.length; i++){
            if(visited[i] >= 2) return false;
        }

        return true;
    }

    private static StringBuilder dfsAll(int[][] graph, int[] visited) {
        StringBuilder ret = new StringBuilder();
        int[] indegree = getIndegree(graph);

        for(int i=0; i<graph.length; i++){
            if(visited[i] >= 1 || indegree[i] != 0) continue;
            ret.append(dfs(i, graph, visited));
        }

        return ret;
    }

    private static int[] getIndegree(int[][] graph) {
        int[] ret = new int[26];

        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].length; j++){
                if(graph[i][j] == 1) ret[j] += 1;
            }
        }

        return ret;
    }

    private static StringBuilder dfs(int vertex, int[][] graph, int[] visited){
        StringBuilder ret = new StringBuilder(String.valueOf(numToAlpha[vertex]));
        
        visited[vertex] += 1;

        for(int i=0; i<graph[vertex].length; i++){
            if((visited[i]== 0 || visited[i] == 1) && graph[vertex][i] == 1){
                ret.append(dfs(i, graph, visited));
            }
        }

        return ret;
    }

    private static int[][] createGraph(String[] words, int maxLenOfWord) {
        int[][] graph = new int[26][26];

        for(int j = 0; j < maxLenOfWord; j++){
            // 입력되는 단어 중 가장 첫번째 단어(i=0)는 알파벳의 정렬 순서를 결정하는데 영향을 미치지 않으므로 skip
            for(int i=1; i<words.length; i++){

                if(words[i].length() <= j || words[i-1].length() <= j) continue;

                if(j == 0 || words[i-1].substring(0,j).equals(words[i].substring(0,j))) {
                    if(words[i-1].charAt(j) != words[i].charAt(j)) {
                        graph[getNumByChar(words[i-1].charAt(j))][getNumByChar(words[i].charAt(j))] = 1;
                    }
                }
            }
        }

        return graph;
    }

    private static int getNumByChar(char alpha){
        return (int)(alpha - 'a');
    }
}
