import java.util.*;

class Solution {
    static class Target implements Comparable<Target> {
        int s, e;
        Target(int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo(Target O) {
            if (this.s == O.s) return this.e - O.e;
            return this.s - O.s;
        }
    }
    
    public int solution(int[][] targets) {
        int answer = 0;
        PriorityQueue<Target> pq = new PriorityQueue<>();
        for (int[] t : targets) pq.offer(new Target(t[0], t[1]));
        
        Target now = pq.poll();
        int s = now.s;
        int e = now.e;
        answer++;
        
        while(!pq.isEmpty()) {
            now = pq.poll();
            if (now.s >= e) {
                answer++;
                s = now.s;
                e = now.e;
            } else e = Math.min(e, now.e);
            
        }
        
        return answer;
    }
}