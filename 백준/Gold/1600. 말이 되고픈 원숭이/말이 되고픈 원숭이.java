import java.util.*;
import java.io.*;

public class Main {

    static int w, h;

    static boolean in(int r, int c) { return r >= 0 && r < h && c >= 0 && c < w; }

    static class Point {
        int r, c, jump, total;
        public Point(int r, int c, int jump, int total) {
            this.r = r;
            this.c = c;
            this.jump = jump;
            this.total = total;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        int[][] map = new int[h][w];
        boolean[][][] visited = new boolean[h][w][k+1];

        for(int i=0; i<h; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<w; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        int[][] jump = { {-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, 2}, {1, 2}, {-1, -2}, {1, -2} };
        int tr, tc, ans = -1;
        Point p;

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;
        
        while(!q.isEmpty()) {
            p = q.poll();
            if(p.r == h-1 && p.c == w-1) {
                ans = p.total;
                break;
            }

            //점프를 마구 사용해버리면 꼭 필요할 때에 점프할 수 없군요?
            for(int i=0; i<4; i++) {
                tr = p.r + drc[i][0];
                tc = p.c + drc[i][1];
                if(in(tr, tc) && !visited[tr][tc][p.jump] && map[tr][tc] == 0) {
                    visited[tr][tc][p.jump] = true;
                    q.offer(new Point(tr, tc, p.jump, p.total + 1));
                }
            }
            if(p.jump < k) {
                for (int i = 0; i < 8; i++) {
                    tr = p.r + jump[i][0];
                    tc = p.c + jump[i][1];
                    if (in(tr, tc) && !visited[tr][tc][p.jump + 1] && map[tr][tc] == 0) {
                        visited[tr][tc][p.jump + 1] = true;
                        q.offer(new Point(tr, tc, p.jump + 1, p.total + 1));
                    }
                }
            }
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}