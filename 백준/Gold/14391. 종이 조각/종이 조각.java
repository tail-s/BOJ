import java.util.Scanner;

public class Main {
    static int N, M, ANS = Integer.MIN_VALUE;
    static char[][] map;
    static boolean[][] visited;

    static void calculate() {
        int result = 0;
        
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    sum = sum * 10 + (map[i][j] - '0');
                } else {
                    result += sum;
                    sum = 0;
                }
            }
            result += sum;
        }
        
        for (int j = 0; j < M; j++) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (!visited[i][j]) {
                    sum = sum * 10 + (map[i][j] - '0');
                } else {
                    result += sum;
                    sum = 0;
                }
            }
            result += sum;
        }

        ANS = Math.max(ANS, result);
    }

    static void solve(int cur) {
        if (cur == N * M) {
            calculate();
            return;
        }

        int r = cur / M;
        int c = cur % M;

        visited[r][c] = true;
        solve(cur + 1);
        visited[r][c] = false;
        solve(cur + 1);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) map[i] = sc.next().toCharArray();
        sc.close();

        solve(0);

        System.out.println(ANS);

    }
}