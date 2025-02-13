class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for (int i = 1; i <= r2; i++) {
            double a2 = Math.sqrt(Math.pow(r2, 2) - Math.pow(i, 2));
            double a1 = Math.sqrt(Math.pow(r1, 2) - Math.pow(i, 2));
            answer += (long) a2 - (long) Math.ceil(a1) + 1;
        }
        
        return answer * 4;
    }
}