import java.util.*;

class Solution {
    public static class Target implements Comparable<Target> {
        int s, e;
        
        Target(int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo(Target o) {
            if (this.s == o.s) return o.e - this.e;
            return this.s - o.s;
        }
    }
    
    public int solution(int[][] targets) {
        int answer = 0;
        
        PriorityQueue<Target> pq = new PriorityQueue<>();
        for (int i = 0; i < targets.length; i++) pq.offer(new Target(targets[i][0], targets[i][1]));
        
        int cur_s = 0, cur_e = 0;
        while (!pq.isEmpty()) {
            Target t = pq.poll();
            if (t.s < cur_e) {
                cur_s = t.s;
                if (t.e < cur_e) cur_e = t.e;                
            }
            else {
                cur_s = t.s;
                cur_e = t.e;
                answer++;
            }
        }
        
        
        return answer;
    }
}