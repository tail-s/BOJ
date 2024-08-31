import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] dp;
    static void solve() {
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) for (int k = 0; k < N; k++) {
            dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
        }

        int ans1 = -1, ans2 = -1, minLen = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) for (int j = i + 1; j < N; j++) {
            int tmp = 0;
            for (int k = 0; k < N; k++) tmp += Math.min(dp[i][k], dp[j][k]) * 2;
            if (tmp < minLen) {
                ans1 = i + 1;
                ans2 = j + 1;
                minLen = tmp;
            }
        }

        System.out.println(ans1 + " " + ans2 + " " + minLen);


    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 1000000);
            dp[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;
            dp[from][to] = 1;
            dp[to][from] = 1;
        }

        solve();

    }
}