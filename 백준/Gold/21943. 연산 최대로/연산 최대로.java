import java.util.Scanner;

/**
 * 사실 곱셈만 신경쓰면 됐었다!
 */
public class Main {
    static int N, P, Q, ANS = Integer.MIN_VALUE;
    static int[] arr;
    static int[] selected;
    static void solve(int idx) {
        if (idx == N) {
            int tmp = 1;
            for (int i : selected) tmp *= i;
            ANS = Math.max(tmp, ANS);
            return;
        }

        for (int i = 0; i < Q + 1; i++) {
            selected[i] += arr[idx];
            solve(idx + 1);
            selected[i] -= arr[idx];
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
        P = sc.nextInt();
        Q = sc.nextInt();
        sc.close();

        selected = new int[Q + 1];
        solve(0);

        System.out.println(ANS);

    }
}