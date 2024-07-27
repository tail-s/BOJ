import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n, a;
    static int[][] map, ans, d;
    static int[] NA = new int[2];
    static String[] arr = {"+0", "+1", "+2", "+3", "-0", "-1", "-2", "-3", "+0", "+1", "+2", "+3"};
    static int[] reverse(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) result[i] = arr[arr.length - 1 - i];
        return result;
    }

    static int check(int a) {
        int b = Character.getNumericValue(arr[a].charAt(1));
        if (arr[a].charAt(0) == '-') d[b] = reverse(d[b]);
        return b;
    }

    static void solve() {
        int rot = (a / 45) % 8;
        if (rot < 0) rot += 8;

        int idx = check(rot);
        for (int i = 0; i < n; i++) ans[i][i] = d[idx][i];

        idx = check(rot + 1);
        for (int i = 0; i < n; i++) ans[n / 2][i] = d[idx][i];

        idx = check(rot + 2);
        for (int i = 0; i < n; i++) ans[n - i - 1][i] = d[idx][i];

        idx = check(rot + 3);
        for (int i = 0; i < n; i++) ans[n - i - 1][n / 2] = d[idx][i];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) sb.append(ans[i][j] == 0 ? map[i][j] : ans[i][j]).append(" ");
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            NA = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = NA[0];
            a = NA[1];
            map = new int[n][n];
            ans = new int[n][n];
            d = new int[4][n];

            for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < n; i++) d[0][i] = map[i][i];        // 우하향
            for (int i = 0; i < n; i++) d[1][i] = map[n / 2][i];    // 가운데행
            for (int i = 0; i < n; i++) d[2][i] = map[n - i - 1][i];// 우상향
            for (int i = 0; i < n; i++) d[3][i] = map[n - i - 1][n / 2];// 가운데열

            solve();
        }

        System.out.println(sb);
    }
}