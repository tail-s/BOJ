import java.util.*;

class Solution {
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0, tmp;

        boolean flag = picks[0] + picks[1] + picks[2] >= minerals.length / 5 + 1;
        
        if (flag) {
            for (int i=0; i<minerals.length / 5; i++) {
            tmp = 0;
            for (int j=0; j<5; j++) {
                switch(minerals[idx++]) {
                    case "diamond":
                        tmp += 100;
                        break;
                    case "iron":
                        tmp += 10;
                        break;
                    default:
                        tmp += 1;
                    }
                }
                pq.offer(tmp);
            }

            tmp = 0;
            while (idx < minerals.length) {
                switch(minerals[idx++]) {
                    case "diamond":
                        tmp += 100;
                        break;
                    case "iron":
                        tmp += 10;
                        break;
                    default:
                        tmp += 1;
                }
            }
            pq.offer(tmp);
        }
        else {
            for (int i=0; i<picks[0] + picks[1] + picks[2]; i++) {
            tmp = 0;
            for (int j=0; j<5; j++) {
                switch(minerals[idx++]) {
                    case "diamond":
                        tmp += 100;
                        break;
                    case "iron":
                        tmp += 10;
                        break;
                    default:
                        tmp += 1;
                    }
                }
                pq.offer(tmp);
            }
            
        }
        
        
        while (!pq.isEmpty()) {
            int mineral = pq.poll();
            int d = mineral / 100;
            mineral %= 100;
            int i = mineral / 10;
            int s = mineral % 10;
            if (picks[0] > 0) {
                picks[0]--;
                answer += d + i + s;
            }
            else if (picks[1] > 0) {
                picks[1]--;
                answer += d * 5 + i + s;
            }
            else if (picks[2] > 0) {
                picks[2]--;
                answer += d * 25 + i * 5 + s;
            }
        }
        
        return answer;
    }
}