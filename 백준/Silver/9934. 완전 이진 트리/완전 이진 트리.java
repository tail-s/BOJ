import java.util.Scanner;

public class Main {
    static int K;
    static int[] arr;
    static StringBuffer[] ans;

    static void dfs(int s, int e, int floor) {

        if (floor == K) return;

        int m = (s + e) / 2;
        ans[floor].append(arr[m] + " ");

        dfs(s, m - 1, floor + 1);
        dfs(m + 1, e, floor + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        arr = new int[(int) Math.pow(2, K) - 1];
        for (int i = 0; i < arr.length; i++) arr[i] = sc.nextInt();
        sc.close();

        ans = new StringBuffer[K];
        for (int i = 0; i < K; i++) ans[i] = new StringBuffer();
        dfs (0, arr.length - 1, 0);

        for (int i = 0; i < K; i++) System.out.println(ans[i].toString());

    }
}