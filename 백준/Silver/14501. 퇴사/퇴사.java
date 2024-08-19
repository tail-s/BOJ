import java.util.Scanner;

public class Main {
    static int N, maxval = 0, ans = 0;
    static int[][] ar;
    static void DFS(int depth, int total) {

        if (depth > N || ans >= total + (N - depth) * maxval) return;

        if (depth == N) {
            ans = total;
            return;
        }

        DFS(depth + ar[depth][0], total + ar[depth][1]);
        DFS(depth + 1, total);

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ar = new int[N][2];

        for(int i=0; i < N; i++) {
            ar[i][0] = sc.nextInt();
            ar[i][1] = sc.nextInt();
            maxval = Math.max(maxval, ar[i][1]);
        }

        sc.close();

        DFS(0, 0);




        System.out.println(ans);
    }
}