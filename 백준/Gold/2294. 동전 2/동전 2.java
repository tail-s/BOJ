import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int n, k;
    static int[] arr;

    public static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
    }

    public static void solve() {
        int[] dp = new int[k + 1];
        Arrays.fill(dp, k + 1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] <= k) dp[arr[i]] = 1;
            for (int j = arr[i]; j <= k; j++) dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
        }
        System.out.println(dp[k] == k + 1 ? -1 : dp[k]);
    }

    public static void main(String[] args) {
        init();
        solve();
    }
}