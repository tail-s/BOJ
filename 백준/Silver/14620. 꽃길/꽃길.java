import java.util.Scanner;

public class Main {
    static int N, ANS = Integer.MAX_VALUE;
    static int[][] cost;
    static int[][] drc = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {0, 0} };
    static boolean out(int r, int c) { return r < 0 || c < 0 || r >= N || c >= N; }
    static boolean[][] visited;

    static int flowerCost(int r, int c) {
        int result = 0;
        for (int i = 0; i < 5; i++) result += cost[r + drc[i][0]][c + drc[i][1]];
        return result;
    }

    static void swap(int r, int c) {
        for (int i = 0; i < 5; i++) visited[r + drc[i][0]][c + drc[i][1]] = !visited[r + drc[i][0]][c + drc[i][1]];
    }

    static boolean available(int r, int c) {
        for (int i = 0; i < 5; i++) {
            int tr = r + drc[i][0];
            int tc = c + drc[i][1];
            if (out(tr, tc) || visited[tr][tc]) return false;
        }
        return true;
    }

    static void solve(int selected, int cost, int cur) {
        if (cost >= ANS || cur == N * N) return;
        if (selected == 3) {
            ANS = Math.min(ANS, cost);
            return;
        }

        int r = cur / N;
        int c = cur % N;

        if (available(r, c)) {
            swap(r, c);
            solve(selected + 1, cost + flowerCost(r, c), cur + 1);
            swap(r, c);
        }
        solve(selected, cost, cur + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        cost = new int[N][N];
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) cost[i][j] = sc.nextInt();
        sc.close();
        visited = new boolean[N][N];

        solve(0, 0, 0);
        System.out.println(ANS);

    }
}