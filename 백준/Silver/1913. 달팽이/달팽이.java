import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, target, x, y;
    static int[][] map;
    static int[][] drc = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
    static boolean canGo(int r, int c) { return (r >= 0 && r < N && c >= 0 && c < N && map[r][c] == -1); }
    static StringBuilder sb = new StringBuilder();

    static void execute() {
        map = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(map[i], -1);

        int cnt = N * N;
        int r = 0, c = 0, dir = 0;
        while (cnt > 1) {
            map[r][c] = cnt;
            if (cnt == target) {
                x = r + 1;
                y = c + 1;
            }
            int tr = r + drc[dir][0];
            int tc = c + drc[dir][1];
            if (canGo(tr, tc)) {
                r = tr;
                c = tc;
                cnt--;
            } else dir = (dir + 1) % 4;
        }
        map[r][c] = 1;
        if (target == 1) {
            x = r + 1;
            y = c + 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) sb.append(map[i][j] + " ");
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        sb.append(x + " " + y);

        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        target = sc.nextInt();
        sc.close();

        execute();
    }
}