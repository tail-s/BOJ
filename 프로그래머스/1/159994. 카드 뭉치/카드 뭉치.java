class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        int i = 0, j = 0, k = 0;
        for (k = 0; k < goal.length; k++) {
            if (i < cards1.length && cards1[i].equals(goal[k])) i++;
            else if (j < cards2.length && cards2[j].equals(goal[k])) j++;
            else {
                answer = "No";
                break;
            }
        }
        
        return answer;
    }
}