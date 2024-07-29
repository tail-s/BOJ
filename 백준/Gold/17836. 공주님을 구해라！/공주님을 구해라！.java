import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int N, M, T, ans = Integer.MAX_VALUE;
    static int[][] drc = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    static int[][] map;
    static boolean[][][] visited;
    static boolean out(int r, int c, int gram) { return r < 0 || c < 0 || r >= N || c >= M || visited[r][c][gram] || (gram == 0 && map[r][c] == 1); }

    static class Status {
        int r, c, gram, timer;
        public Status(int r, int c, int gram, int timer) {
            this.r = r;
            this.c = c;
            this.gram = gram;
            this.timer = timer;
        }
    }

    static void bfs() {
        visited[0][0][0] = true;
        Queue<Status> q = new ArrayDeque<>();
        q.offer(new Status(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Status status = q.poll();
            if (status.timer >= ans || status.timer > T) return;
            int r = status.r, c = status.c;
            if (status.gram == 0 && map[r][c] == 2) status.gram = 1;
            if (r == N - 1 && c == M - 1) ans = Math.min(ans, status.timer);

            for (int k = 0; k < 4; k++) {
                int tr = r + drc[k][0], tc = c + drc[k][1];
                if (out(tr, tc, status.gram)) continue;
                visited[tr][tc][status.gram] = true;
                q.offer(new Status(tr, tc, status.gram, status.timer + 1));
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        T = Integer.parseInt(info[2]);
        map = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        bfs();

        System.out.println(ans == Integer.MAX_VALUE ? "Fail" : ans);

    }
}