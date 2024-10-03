import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans = Integer.MAX_VALUE;
    static int[] selected;
    static int[][] map;
    static List<Point> c = new ArrayList<>();
    static List<Point> h = new ArrayList<>();
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int dis(Point h, Point c) {        //집에서 치킨집까지의 거리
        return Math.abs(h.r - c.r) + Math.abs(h.c - c.c);
    }
    static void dfs(int depth, int start) {
        if(depth == m) {
            int total = 0;                      //전체 치킨거리
            for(int i=0; i<h.size(); i++) {     //모든 집을 대상으로 탐색
                int near = Integer.MAX_VALUE;
                for (int j=0; j<m; j++)         //선택된 치킨집들을 대상으로 탐색
                    near = Math.min(near, dis(h.get(i), c.get(selected[j])));       //해당 집에서 가장 가까운 치킨거리 탐색
                total += near;
            }
            ans = Math.min(ans, total);
            return;
        }

        for(int i=start; i<c.size(); i++) {         //전체 치킨집 중 중복없이 m개를 선택해 selected에 담기
            selected[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        selected = new int[m];      //살아남은 치킨집

        int tmp;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if(tmp == 2)
                    c.add(new Point(i, j));
                else if(tmp == 1)
                    h.add(new Point(i, j));
            }
        }

        dfs(0, 0);

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}