import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int N, M, ans = 0;
    static int[][] map;
    static int[][] water;
    static int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static boolean[][] visited;
    static boolean out(int r, int c) { return r < 0 || c < 0 || r >= N || c >= M || visited[r][c]; }

    static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int k = 0; k < 4; k++) {
            int tr = r + drc[k][0];
            int tc = c + drc[k][1];
            if (out(tr, tc) || map[tr][tc] == 0) continue;
            dfs(tr, tc);
        }
    }

    static int count() {
        int result = 0;
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) {
            if (out(i, j) || map[i][j] == 0) continue;
            dfs(i, j);
            result++;
        }

        return result;
    }

    // 억지 BFS 써보기
    static void melting() {
        Queue<int[]> q = new ArrayDeque<>();
        water = new int[N][M];

        visited = new boolean[N][M];
        find_start_point:
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) if (map[i][j] != 0) {
            q.offer(new int[] {i, j});
            visited[i][j] = true;
            break find_start_point;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int degree = 0;

            for (int k = 0; k < 4; k++) {
                int tr = r + drc[k][0];
                int tc = c + drc[k][1];
                if (out(tr, tc)) continue;

                if (map[tr][tc] == 0) {
                    degree++;
                } else {
                    visited[tr][tc] = true;
                    q.offer(new int[] {tr, tc});
                }
            }
            water[r][c] = degree;
        }
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) if (water[i][j] != 0) map[i][j] -= Math.min(water[i][j], map[i][j]);
        
        ans++;
    }

    static void solve() {

        int island;
        while ((island = count()) == 1) melting();
        System.out.println(island != 0 ? ans : 0);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        solve();

    }
}