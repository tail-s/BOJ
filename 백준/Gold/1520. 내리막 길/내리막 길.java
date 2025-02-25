import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] map, dp, drc = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
    static boolean out(int r, int c) { return r < 0 || r >= M || c < 0 || c >= N; }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static void solve() {
        dp = new int[M][N];
        for (int i = 0; i < M; i++) Arrays.fill(dp[i], -1);
        System.out.println(dfs(0, 0));
    }

    public static int dfs(int r, int c) {
        if (r == M - 1 && c == N - 1) return 1;
        if (dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 0;
        for (int k = 0; k < 4; k++) {
            int dr = r + drc[k][0];
            int dc = c + drc[k][1];
            if (out(dr, dc)) continue;
            if (map[r][c] > map[dr][dc]) dp[r][c] += dfs(dr, dc);
        }

        return dp[r][c];
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }
}