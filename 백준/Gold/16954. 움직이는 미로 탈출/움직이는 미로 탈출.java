import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static String[] map = new String[8];

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static void mov() {
        for(int i=6; i>=0; i--)
            map[i+1] = map[i];
        map[0] = "........";
//        map[0] = { '.', '.', '.', '.', '.', '.', '.', '.' };
    }

    static boolean in(int r, int c) { return r >= 0 && c >= 0 && r < 8 && c < 8; }

    static boolean reach(int r, int c) {
        for(int i=0; i<r; i++)
            if(map[i].charAt(c) == '#')
                return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<8; i++)
            map[i] = sc.nextLine();
        sc.close();

//        boolean[][] visited = new boolean[8][8];      이게 필요할까?

        int[][] drc = { {0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1} };
        int tr, tc, size, ans = 0;
        Point p;

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(7, 0));

        bfs:
        while(!q.isEmpty()) {

            size = q.size();
            while(size-- > 0) {
                p = q.poll();

                if(reach(p.r, p.c) || p.r == 0) {
                    ans = 1;
                    break bfs;
                }

                for(int i=0; i<9; i++) {
                    tr = p.r + drc[i][0];
                    tc = p.c + drc[i][1];
                    if(in(tr, tc) && map[tr].charAt(tc) == '.' && !(in(tr-1, tc) && map[tr-1].charAt(tc) == '#'))
                        q.offer(new Point(tr, tc));
                }
            }
            mov();
        }

        System.out.print(ans);
    }
}