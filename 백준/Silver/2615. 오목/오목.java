import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][] map = new int[21][21];
    static int[][] drc = { {1, 0}, {0, 1}, {-1, 1}, {1, 1} };
    static boolean out(int r, int c) { return r < 1 || r > 19 || c < 1 || c > 19; }

    static int verify(int r, int c) {
        int result = 0;
        int target = map[r][c];

        for (int i = 0; i < 4; i++) {
            int tr = r;
            int tc = c;
            int cnt = 1;
            for (int j = 0; j < 4; j++) {
                tr += drc[i][0];
                tc += drc[i][1];
                if (out(tr, tc)) break;
                if (map[tr][tc] == target) {
                    cnt++;
                    if (cnt == 5 && map[r-drc[i][0]][c-drc[i][1]] != target && map[tr+drc[i][0]][tc+drc[i][1]] != target) {
                        result = target;
                        return result;
                    };
                }
            }
        }
        return result;
    }

    static void solve() {
        int winner;
        for (int i = 1; i < 20; i++) for (int j = 1; j < 20; j++) {
            if (map[i][j] != 0 && (winner = verify(i, j)) != 0) {
                System.out.print(winner + "\n" + i + " " + j);
                return;
            }
        };

        System.out.print(0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i < 20; i++) for (int j = 1; j < 20; j++) map[i][j] = sc.nextInt();
        sc.close();

        solve();

    }
}