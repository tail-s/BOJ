import java.util.*;
import java.io.*;

public class Main {
    static int n, m, ans = Integer.MIN_VALUE;
    static List<Point> virus;

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static boolean is_in(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }

    static void dfs(int r, int c, int depth, int[][] arr) {
        if(depth == 3) {
            bfs(arr);      //벽 3개 세우고 넘기기
            return;
        }

        r += c == m ? 1 : 0;
        c -= c == m ? m : 0;
        if(r>=n) return;

        if(arr[r][c] == 0) {
            arr[r][c] = 1;
            dfs(r, c + 1, depth + 1, arr);
            arr[r][c] = 0;
        }
        dfs(r, c + 1, depth, arr);

    }

    static void bfs(int[][] origin) {

        int[][] arr = new int[n][m];

        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                arr[i][j] = origin[i][j];

        int[][] drc = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        Point tmp;
        int tr, tc, cnt = 0;

        for(Point p : virus) {      //벽 3개 세운 후 바이러스 퍼트려보기
            q.offer(p);
            visited[p.r][p.c] = true;
        }

        while(!q.isEmpty()) {
            tmp = q.poll();

            for(int k=0; k<4; k++) {
                tr = tmp.r + drc[k][0];
                tc = tmp.c + drc[k][1];

                if(is_in(tr, tc) && !visited[tr][tc] && arr[tr][tc] == 0) {
                    visited[tr][tc] = true;
                    arr[tr][tc] = 2;
                    q.offer(new Point(tr, tc));
                }
            }
        }

        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                if(arr[i][j] == 0)
                    cnt++;

        ans = Math.max(ans, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        virus = new ArrayList<>();

        int tmp;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if(tmp == 2)
                    virus.add(new Point(i, j));
            }
        }

        dfs(0, 0, 0, map);

        bw.write(ans + "");

        bw.flush();
        bw.close();
        br.close();

    }
}