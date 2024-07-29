import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] box;
    static int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static boolean out(int r, int c) { return r < 0 || c < 0 || r >= N || c >= M || visited[r][c] || box[r][c] == -1; }
    static boolean[][] visited;
    static Queue<int[]> q = new ArrayDeque<>();

    static void bfs() {

        int ans = 0;
        while (!q.isEmpty()) {
            int layer = q.size();
            ans++;
            for (int i = 0; i < layer; i++) {
                int[] cur = q.poll();

                for (int j = 0; j < 4; j++) {
                    int tr = cur[0] + drc[j][0];
                    int tc = cur[1] + drc[j][1];
                    if (out(tr, tc)) continue;
                    box[tr][tc] = ans;
                    visited[tr][tc] = true;
                    q.offer(new int[] {tr, tc});
                }
            }

        }

        verify:
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) if (box[i][j] == 0) {
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
        box = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    q.offer(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }
        br.close();

        bfs();

    }
}