import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static boolean in(int r, int c) { return r >= 0 && c >= 0 && r < n && c < m; }
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static void airCheck() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        q.offer(new Point(0, 0));
        while(!q.isEmpty()) {
            Point p = q.poll();
            for(int i=0; i<4; i++) {
                int tr = p.r + drc[i][0];
                int tc = p.c + drc[i][1];
                if(in(tr, tc) && !visited[tr][tc] && (map[tr][tc] == 0 || map[tr][tc] == 2)) {
                    map[tr][tc] = 2;
                    visited[tr][tc] = true;
                    q.offer(new Point(tr, tc));
                }
            }
        }
    }
    static int melting() {
        int airCnt;
        int meltCnt = 0;
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                if(map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    q.offer(new Point(i, j));
                    while(!q.isEmpty()) {
                        Point p = q.poll();
                        airCnt = 0;
                        for(int k=0; k<4; k++) {
                            int tr = p.r + drc[k][0];
                            int tc = p.c + drc[k][1];
                            if(in(tr, tc)) {
                                if(map[tr][tc] == 1 && !visited[tr][tc]) {
                                    visited[tr][tc] = true;
                                    q.offer(new Point(tr, tc));
                                }
                                if(map[tr][tc] == 2)
                                    airCnt++;
                            }
                        }
                        if(airCnt >= 2) {
                            map[p.r][p.c] = 0;
                            meltCnt++;
                        }
                    }
                }
        return meltCnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        int cheese = 0, tmp;
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++) {
                tmp = sc.nextInt();
                map[i][j] = tmp;
                cheese += tmp == 1 ? 1 : 0;
            }
        sc.close();
        int hour = 0;
        while(cheese != 0) {
            airCheck();
            cheese -= melting();
            hour++;
        }
        System.out.print(hour);

    }
}