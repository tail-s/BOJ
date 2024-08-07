import java.io.*;
import java.util.*;

public class Main {

    static int n, m, h, w, sr, sc, fr, fc;
    static int[][] map;

    static class Point {
        int r, c, step;
        public Point(int r, int c, int step) {
            this.r = r;
            this.c = c;
            this.step = step;
        }
    }

    static boolean is_in(int r, int c) { return r >= 0 && c >= 0 && r < n && c < m; }

    static boolean mov(int r, int c, int idx) {

        switch(idx) {
            case 0:
                if(!is_in(r+h-1, c+w-1))
                    return false;

                for(int i=r; i<r+h; i++)
                    if(map[i][c+w-1] == 1)
                        return false;

                return true;
            case 1:		//좌측
                for(int i=r; i<r+h; i++)
                    if(map[i][c] == 1)
                        return false;

                return true;
            case 2:		//상단
                for(int i=c; i<c+w; i++)
                    if(map[r][i] == 1)
                        return false;

                return true;
            case 3:		//하단
                if(!is_in(r+h-1, c+w-1))
                    return false;

                for(int i=c; i<c+w; i++)
                    if(map[r+h-1][i] == 1)
                        return false;

                return true;

            default:
                return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        int[][] drc = { {0, 1}, {0, -1}, {-1, 0}, {1, 0} };

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken())-1;
        sc = Integer.parseInt(st.nextToken())-1;
        fr = Integer.parseInt(st.nextToken())-1;
        fc = Integer.parseInt(st.nextToken())-1;

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sr, sc, 0));
        visited[sr][sc] = true;

        Point p;
        int tr, tc, ans = -1;
        while(!q.isEmpty()) {
            p = q.poll();
            if(p.r == fr && p.c == fc) {
                ans = p.step;
                break;
            }

            for(int i=0; i<4; i++) {
                tr = p.r + drc[i][0];
                tc = p.c + drc[i][1];
                if(is_in(tr, tc) && !visited[tr][tc] && mov(tr, tc, i)) {
                    visited[tr][tc] = true;
                    q.offer(new Point(tr, tc, p.step + 1));
                }
            }
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}