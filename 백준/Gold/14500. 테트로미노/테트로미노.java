import java.util.Scanner;

public class Main {
    static int N, M, Max = Integer.MIN_VALUE, ANS = Integer.MIN_VALUE;
    static int[][] map;
    static int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static boolean[][] visited;
    static boolean out(int r, int c) { return r < 0 || c < 0 || r >= N || c >= M; }
    static void dfs(int depth, int sum, int r, int c) {
        if (ANS >= sum + (4 - depth) * Max) return;

        if (depth == 4) {
            ANS = Math.max(ANS, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int tr = r + drc[i][0];
            int tc = c + drc[i][1];
            if (out(tr, tc) || visited[tr][tc]) continue;
            visited[tr][tc] = true;
            dfs(depth + 1, sum + map[tr][tc], tr, tc);
            visited[tr][tc] = false;

            if (depth == 2) {
                visited[tr][tc] = true;
                dfs(depth + 1, sum + map[tr][tc], r, c);
                visited[tr][tc] = false;
            }
        }

    }

    static void solve() {
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) {
            visited[i][j] = true;
            dfs(1, map[i][j], i, j);
            visited[i][j] = false;
        }
        System.out.println(ANS);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) {
            map[i][j] = sc.nextInt();
            Max = Math.max(Max, map[i][j]);
        }
        sc.close();

        solve();


    }
}