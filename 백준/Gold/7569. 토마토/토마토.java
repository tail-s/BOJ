import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, H;
    static int[][][] box;
    static int[][] drc = { {1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1} };
    static boolean out(int h, int r, int c) { return r < 0 || c < 0 || r >= N || c >= M || h < 0 || h >= H || visited[h][r][c] || box[h][r][c] == -1; }
    static boolean[][][] visited;
    static Queue<int[]> q = new ArrayDeque<>();

    static void bfs() {

        int ans = 0;
        while (!q.isEmpty()) {
            int layer = q.size();
            ans++;
            for (int i = 0; i < layer; i++) {
                int[] cur = q.poll();

                for (int j = 0; j < 6; j++) {
                    int th = cur[0] + drc[j][0];
                    int tr = cur[1] + drc[j][1];
                    int tc = cur[2] + drc[j][2];
                    if (out(th, tr, tc)) continue;
                    box[th][tr][tc] = ans;
                    visited[th][tr][tc] = true;
                    q.offer(new int[] {th, tr, tc});
                }
            }

        }

        verify:
        for (int k = 0; k < H; k++) for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) if (box[k][i][j] == 0) {
            ans = -1;
            break verify;
        }

        System.out.println(ans != -1 ? ans - 1 : ans);


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);
        box = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int k = 0; k < H; k++) for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[k][i][j] = Integer.parseInt(st.nextToken());
                if (box[k][i][j] == 1) {
                    q.offer(new int[] {k, i, j});
                    visited[k][i][j] = true;
                }
            }
        }
        br.close();

        bfs();

    }
}