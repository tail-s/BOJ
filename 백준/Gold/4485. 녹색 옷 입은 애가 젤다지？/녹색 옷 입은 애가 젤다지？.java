import java.io.*;
import java.util.*;

public class Main {

    static int n;

    static class Point implements Comparable<Point> {
        int r, c, lost;
        public Point(int r, int c, int lost) {
            this.r = r;
            this.c = c;
            this.lost = lost;
        }

        @Override
        public int compareTo(Point o) {
            return this.lost - o.lost;
        }
    }

    static boolean in(int r, int c) { return r >= 0 && r < n && c >= 0 && c < n; }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        int idx = 1, tr, tc;
        int[][] map;
        int[][] record;
        boolean[][] visited;
        Queue<Point> q;
        Point p;

        while(true) {

            n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            map = new int[n][n];
            record = new int[n][n];
            visited = new boolean[n][n];
            for(int i=0; i<n; i++)
                Arrays.fill(record[i], Integer.MAX_VALUE);

            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            q = new PriorityQueue<>();
            q.offer(new Point(0, 0, map[0][0]));
            visited[0][0] = true;

            while(!q.isEmpty()) {
                p = q.poll();

                for(int i=0; i<4; i++) {
                    tr = p.r + drc[i][0];
                    tc = p.c + drc[i][1];
                    if(in(tr, tc) && !visited[tr][tc] && record[tr][tc] > p.lost + map[tr][tc]) {
                        visited[tr][tc] = true;
                        record[tr][tc] = p.lost + map[tr][tc];
                        q.offer(new Point(tr, tc, record[tr][tc]));
                    }
                }
            }
            sb.append("Problem " + idx + ": " + record[n-1][n-1] + "\n");
            idx++;
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}