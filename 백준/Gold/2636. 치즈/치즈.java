import java.util.*;
import java.io.*;

public class Main {

    static int n, m;

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static boolean in(int r, int c) { return r >= 0 && r < n && c >= 0 && c < m; }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        int last = 0;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if(tmp == 1)
                    last++;
            }
        }

        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited;
        int tr, tc, now = last, hour = 0;
        Point p;

        boolean melted = now == 0;
        while(!melted) {

            hour++;
            last = now;
            now = 0;
            visited = new boolean[n][m];

            q.offer(new Point(0, 0));
            visited[0][0] = true;

            while(!q.isEmpty()) {
                p = q.poll();

                for(int k=0; k<4; k++) {
                    tr = p.r + drc[k][0];
                    tc = p.c + drc[k][1];
                    if(in(tr, tc) && !visited[tr][tc]) {
                        if(map[tr][tc] == 0) {
                            visited[tr][tc] = true;
                            q.offer(new Point(tr, tc));
                        }
                        else if(map[tr][tc] == 1) {
                            visited[tr][tc] = true;
                            map[tr][tc] = 0;
                        }
                    }
                }
            }

            for(int i=0; i<n; i++)
                for(int j=0; j<m; j++)
                    if(map[i][j] == 1)
                        now++;

            melted = now == 0;
        }

        bw.write(hour + "\n" + last);
        bw.flush();
        bw.close();
        br.close();

    }
}