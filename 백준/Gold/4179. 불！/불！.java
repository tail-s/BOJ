import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static boolean in(int r, int c) { return r >= 0 && r < n && c >= 0 && c < m; }
    static boolean escape(int r, int c) { return r == 0 || r == n-1 || c == 0 || c == m-1; }
    static int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static class Point {
        int r, c, step;
        public Point(int r, int c, int step) {
            this.r = r;
            this.c = c;
            this.step = step;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        Queue<Point> q = new ArrayDeque<>();
        Queue<Point> fire = new ArrayDeque<>();

        Point start = new Point(0, 0, 0);
        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<m; j++) {
                if(map[i][j] == 'F')
                    fire.offer(new Point(i, j, 0));
                else if(map[i][j] == 'J')
                    start = new Point(i, j, 1);
            }
        }
        q.offer(start);
        while(!fire.isEmpty())
            q.offer(fire.poll());

        String ans = "IMPOSSIBLE";
        while(!q.isEmpty()) {
            Point p = q.poll();
            if(map[p.r][p.c] == 'J' && escape(p.r, p.c)) {
                ans = String.valueOf(p.step);
                break;
            }

            for(int i=0; i<4; i++) {
                int tr = p.r + drc[i][0];
                int tc = p.c + drc[i][1];
                if(in(tr, tc) && map[tr][tc] != '#' && map[tr][tc] != 'F') {
                    if(map[p.r][p.c] == 'F') {
                        map[tr][tc] = 'F';
                        q.offer(new Point(tr, tc, 0));
                    }
                    else if(map[p.r][p.c] == 'J' && map[tr][tc] != 'J') {
                        map[tr][tc] = 'J';
                        q.offer(new Point(tr, tc, p.step + 1));
                    }
                }
            }
        }

        bw.write(ans);
        bw.flush();
        bw.close();
        br.close();

    }
}