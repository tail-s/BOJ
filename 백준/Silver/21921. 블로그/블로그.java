import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int X = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) { arr[i] = sc.nextInt(); }
        sc.close();

        int s = 0, e = X, val = 0;
        for (int i = 0; i < e; i++) val += arr[i];
        int ans = val;
        int cnt = 1;

        while (e < N) {
            val += arr[e];
            val -= arr[s];
            e++;
            s++;
            if (val > ans) {
                ans = val;
                cnt = 1;
            } else if (val == ans) cnt++;
        }
        if (ans == 0) sb.append("SAD");
        else sb.append(ans + "\n" + cnt);
        System.out.println(sb.toString());
    }

}