import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        for (int i = 0; i < photo.length; i++) {
            int total = 0;
            for (String target : photo[i]) {
                int idx = Arrays.asList(name).indexOf(target);
                total += idx != -1 ? yearning[idx] : 0;
            }
            answer[i] = total;
        }
        
        return answer;
    }
}