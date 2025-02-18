import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n, k;
    static int[] arr;

    static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
        sc.close();
    }

    static int solve() {
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) for (int j = arr[i]; j <= k; j++) dp[j] = dp[j] + dp[j - arr[i]];
        return dp[k];
    }

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }
}