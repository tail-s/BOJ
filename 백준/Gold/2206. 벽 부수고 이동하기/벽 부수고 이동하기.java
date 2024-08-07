import java.util.*;
import java.io.*;

public class Main {

    static int n, m;

    static class Point {
        int r, c, step, used;
        public Point(int r, int c, int step, int used) {
            this.r = r;
            this.c = c;
            this.step = step;
            this.used = used;
        }
    }

    static boolean is_in(int r, int c) { return r >= 0 && c >= 0 && r < n && c < m; }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        int[][] map = new int[n][m];
        boolean[][][] visited = new boolean[2][n][m];

        char[] tmp;
        for(int i=0; i<n; i++) {
            tmp = br.readLine().toCharArray();
            for(int j=0; j<m; j++)
                map[i][j] = Character.getNumericValue(tmp[j]);
        }

        Queue<Point> q = new ArrayDeque<>();
        visited[0][0][0] = true;
        visited[1][0][0] = true;
        q.offer(new Point(0, 0, 1, 0));

        Point p;
        int tr, tc, ans = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            p = q.poll();
            if(p.r == n-1 && p.c == m-1)
                ans = Math.min(ans, p.step);

            for(int i=0; i<4; i++) {
                tr = p.r + drc[i][0];
                tc = p.c + drc[i][1];
                if(is_in(tr, tc) && !visited[p.used][tr][tc] && map[tr][tc] == 0) {
                    visited[p.used][tr][tc] = true;
                    q.offer(new Point(tr, tc, p.step + 1, p.used));
                }
            }

            if(p.used == 0) {			//벽 뚫는 능력을 사용하지 않은 경우 (능력이 남아있는 경우)
                for(int i=0; i<4; i++) {
                    tr = p.r + drc[i][0];
                    tc = p.c + drc[i][1];
                    if(is_in(tr, tc) && !visited[1][tr][tc] && map[tr][tc] == 1) {
                        visited[1][tr][tc] = true;
                        q.offer(new Point(tr, tc, p.step + 1, 1));
                    }
                }

            }
        }

        ans = ans == Integer.MAX_VALUE ? -1 : ans;

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }

}