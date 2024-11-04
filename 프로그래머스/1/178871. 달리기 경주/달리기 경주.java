import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        
        Map<String, Integer> nameToRank = new HashMap<>();
        Map<Integer, String> rankToName = new HashMap<>();
        
        for (int i=0; i<players.length; i++) {
            nameToRank.put(players[i], i);
            rankToName.put(i, players[i]);
        }
        
        for (int i=0; i<callings.length; i++) {
            int idx = nameToRank.get(callings[i]);  // 기존 순위
            String name = rankToName.get(idx - 1);  // 추월당한사람 이름
            rankToName.put(idx - 1, callings[i]);
            rankToName.put(idx, name);
            nameToRank.put(callings[i], idx - 1);
            nameToRank.put(name, idx);
        }
        
        for (int i=0; i<players.length; i++)
            answer[i] = rankToName.get(i);
        
        return answer;
    }
    
}